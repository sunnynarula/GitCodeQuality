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
        // compute quality for the current staged changes
        com.voidtoverse.engine.QualityEngine.Result result = com.voidtoverse.engine.QualityEngine.calculateQuality(repo);
        String[] desc = com.voidtoverse.engine.QualityDescriptor.describe(result.quality);
        Label quality = new Label(desc[0] + " Commit Quality: " + result.quality + "% — \"" + desc[1] + "\"");
        Label msgLabel = new Label("Commit Message:");
        TextArea msgBox = new TextArea();
        Button commitBtn = new Button("Commit");
        Button advancedBtn = new Button("Advanced");
        Button switchBtn = new Button("Switch");
        HBox controls = new HBox(10, commitBtn, advancedBtn, switchBtn);
        VBox root = new VBox(10, quality, msgLabel, msgBox, controls);
        stage.setScene(new Scene(root, 500, 300));
        stage.setTitle("GitCodeQuality");

        // Show commit history when advanced button is clicked
        advancedBtn.setOnAction(evt -> {
            com.voidtoverse.ui.HistoryViewer.showHistory(stage, repo);
        });

        // Commit action: stage and commit all changes then refresh quality
        commitBtn.setOnAction(evt -> {
            String message = msgBox.getText().trim();
            if (message.isEmpty()) {
                return;
            }
            try {
                ProcessBuilder pbAdd = new ProcessBuilder("git", "add", "-A");
                pbAdd.directory(repo);
                pbAdd.start().waitFor();
                ProcessBuilder pbCommit = new ProcessBuilder("git", "commit", "-m", message);
                pbCommit.directory(repo);
                pbCommit.start().waitFor();
            } catch (Exception e) {
                // ignore commit errors
            }
            com.voidtoverse.engine.QualityEngine.Result r = com.voidtoverse.engine.QualityEngine.calculateQuality(repo);
            int qualityValue = r.quality;
            // if the commit message contains one of the exception keywords, force 100 quality
            String lower = message.toLowerCase();
            if (lower.startsWith("refactor:") || lower.contains("bulk rename") || lower.contains("initial commit")) {
                qualityValue = 100;
            }
            String[] newDesc = com.voidtoverse.engine.QualityDescriptor.describe(qualityValue);
            quality.setText(newDesc[0] + " Commit Quality: " + qualityValue + "% — \"" + newDesc[1] + "\"");
            msgBox.clear();
        });
    }
}