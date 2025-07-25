package com.voidtoverse.ui;

import com.voidtoverse.persistence.Persistence;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a simple repository picker using a {@link DirectoryChooser}.
 *
 * <p>This initial implementation displays the native directory chooser to let
 * the user select a Git repository folder. Once a directory is selected, the
 * path is added to the recent list stored in {@code ~/.gitgui/recent_repos.json}
 * via {@link Persistence}. Duplicate entries are removed and the list is
 * limited to a maximum of five paths.</p>
 */
public final class RepoPicker {
    private RepoPicker() {}

    /**
     * Show a system directory chooser and return the selected repository.
     *
     * @param owner the parent window for modality
     * @return the chosen repository folder, or {@code null} if cancelled
     */
    public static File chooseRepository(Stage owner) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select Git Repository");
        File dir = chooser.showDialog(owner);
        if (dir != null) {
            List<String> recents = Persistence.loadRecentRepositories();
            List<String> updated = new ArrayList<>();
            String path = dir.getAbsolutePath();
            updated.add(path);
            for (String existing : recents) {
                if (!existing.equals(path) && updated.size() < 5) {
                    updated.add(existing);
                }
            }
            Persistence.saveRecentRepositories(updated);
        }
        return dir;
    }

    /**
     * Display a dialog listing the user's recently selected repositories and allow
     * choosing one. A simple "Open" button confirms the selection. Browsing for
     * a new directory is implemented separately.
     *
     * @param owner parent window for modality
     * @return the selected repository, or {@code null} if the dialog is cancelled
     */
    public static File chooseFromRecentList(Stage owner) {
        // Load previously selected repositories
        List<String> recents = Persistence.loadRecentRepositories();
        javafx.scene.control.ListView<String> listView = new javafx.scene.control.ListView<>();
        listView.getItems().addAll(recents);
        javafx.scene.control.Button openBtn = new javafx.scene.control.Button("Open");
        javafx.scene.control.Button browseBtn = new javafx.scene.control.Button("Browse…");
        // Disable the Open button until the user selects an entry
        openBtn.setDisable(true);
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            openBtn.setDisable(newVal == null);
        });
        // This array will capture the user's selection from either the list or browse dialog
        final File[] selected = new File[1];
        // Handler for choosing an existing recent repository
        openBtn.setOnAction(e -> {
            String path = listView.getSelectionModel().getSelectedItem();
            if (path != null) {
                selected[0] = new File(path);
            }
            ((Stage) openBtn.getScene().getWindow()).close();
        });
        // Handler for browsing a new repository via a DirectoryChooser
        browseBtn.setOnAction(e -> {
            File dir = chooseRepository(owner);
            if (dir != null) {
                selected[0] = dir;
                ((Stage) browseBtn.getScene().getWindow()).close();
            }
        });
        // Layout: list view in the center, buttons at the bottom
        javafx.scene.layout.BorderPane root = new javafx.scene.layout.BorderPane(listView);
        javafx.scene.layout.HBox buttonBox = new javafx.scene.layout.HBox(10, openBtn, browseBtn);
        buttonBox.setStyle("-fx-padding: 10; -fx-alignment: center;");
        root.setBottom(buttonBox);
        // Build and show the dialog
        javafx.stage.Stage dialog = new javafx.stage.Stage();
        dialog.initOwner(owner);
        dialog.initModality(javafx.stage.Modality.WINDOW_MODAL);
        dialog.setScene(new javafx.scene.Scene(root, 400, 300));
        dialog.setTitle("Select Repository");
        dialog.showAndWait();
        File chosen = selected[0];
        if (chosen != null) {
            // Update the recent list with the chosen path; keep at most five entries
            String path = chosen.getAbsolutePath();
            List<String> updated = new ArrayList<>();
            updated.add(path);
            for (String existing : recents) {
                if (!existing.equals(path) && updated.size() < 5) {
                    updated.add(existing);
                }
            }
            Persistence.saveRecentRepositories(updated);
        }
        return chosen;
    }
}