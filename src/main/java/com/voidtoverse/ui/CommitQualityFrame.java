package com.voidtoverse.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Sets up the main window showing commit quality information and controls.
 *
 * <p>The initial sprint of this project implements a very simple UI: a label
 * displaying a hard-coded commit quality score, a text area for entering a
 * commit message, and a row of buttons for common actions. Future sprints will
 * replace this placeholder with a fully featured interface that connects to a
 * real Git repository and computes quality scores based on actual changes.</p>
 */
public final class CommitQualityFrame {
    private CommitQualityFrame() {}

    /**
     * Configure the provided stage with the UI components.
     *
     * @param stage JavaFX stage to populate
     */
    public static void setup(Stage stage, java.io.File repo) {
        // Clear existing scene content before rebuilding
        // Determine the window title based on whether a repository is selected
        String title = "GitCodeQuality";
        if (repo != null) {
            title += " - " + repo.getAbsolutePath();
        }
        stage.setTitle(title);

        // Define UI components
        Label qualityLabel;
        Label msgLabel = new Label("Commit Message:");
        TextArea msgBox = new TextArea();
        Button commitBtn = new Button("Commit");
        Button advancedBtn = new Button("Advanced");
        Button switchBtn = new Button("Switch");

        // If a repository is selected, compute the current commit quality
        if (repo != null) {
            com.voidtoverse.engine.QualityEngine.Result result = com.voidtoverse.engine.QualityEngine.calculateQuality(repo);
            String[] desc = com.voidtoverse.engine.QualityDescriptor.describe(result.quality);
            qualityLabel = new Label(desc[0] + " Commit Quality: " + result.quality + "% — \"" + desc[1] + "\"");
            commitBtn.setDisable(false);
            advancedBtn.setDisable(false);
        } else {
            // No repository selected: show a placeholder message and disable commit/history features
            qualityLabel = new Label("No git repository selected");
            commitBtn.setDisable(true);
            advancedBtn.setDisable(true);
        }

        HBox controls = new HBox(10, commitBtn, advancedBtn, switchBtn);
        VBox root = new VBox(10, qualityLabel, msgLabel, msgBox, controls);
        stage.setScene(new Scene(root, 500, 300));

        // Handler for the switch button: prompt the user to select from recents or browse
        switchBtn.setOnAction(evt -> {
            java.io.File chosen = com.voidtoverse.ui.RepoPicker.chooseFromRecentList(stage);
            if (chosen != null) {
                // Persist the last selected repository
                com.voidtoverse.persistence.Persistence.saveLastRepository(chosen.getAbsolutePath());
                // Rebuild the UI with the new repository
                setup(stage, chosen);
            }
        });

        // Show commit history when advanced button is clicked
        advancedBtn.setOnAction(evt -> {
            if (repo != null) {
                com.voidtoverse.ui.HistoryViewer.showHistory(stage, repo);
            }
        });

        // Commit action: stage and commit all changes then refresh quality
        commitBtn.setOnAction(evt -> {
            // Guard against null repo (should be disabled anyway)
            if (repo == null) {
                return;
            }
            String message = msgBox.getText().trim();
            if (message.isEmpty()) {
                return;
            }
            try {
                // Use JGit API to stage and commit all changes
                org.eclipse.jgit.api.Git git = org.eclipse.jgit.api.Git.open(repo);
                // Add all changes
                git.add().addFilepattern(".").call();
                // Perform commit
                git.commit().setMessage(message).call();
                git.close();
            } catch (Exception e) {
                // ignore commit errors silently
            }
            // Recalculate quality after commit
            com.voidtoverse.engine.QualityEngine.Result r = com.voidtoverse.engine.QualityEngine.calculateQuality(repo);
            int qualityValue = r.quality;
            // if the commit message contains one of the exception keywords, force 100 quality
            String lower = message.toLowerCase();
            if (lower.startsWith("refactor:") || lower.contains("bulk rename") || lower.contains("initial commit")) {
                qualityValue = 100;
            }
            String[] newDesc = com.voidtoverse.engine.QualityDescriptor.describe(qualityValue);
            qualityLabel.setText(newDesc[0] + " Commit Quality: " + qualityValue + "% — \"" + newDesc[1] + "\"");
            msgBox.clear();
        });
    }
}