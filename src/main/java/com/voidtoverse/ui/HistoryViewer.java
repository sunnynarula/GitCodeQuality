package com.voidtoverse.ui;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Displays a simple list of recent commits for the selected repository.
 */
public final class HistoryViewer {
    private HistoryViewer() {}

    /**
     * Show a modal window containing the output of `git log --oneline`.
     *
     * @param owner the parent stage to block
     * @param repo the repository directory
     */
    public static void showHistory(Stage owner, File repo) {
        List<String> commits = new ArrayList<>();
        try {
            ProcessBuilder pb = new ProcessBuilder("git", "log", "--oneline", "-n", "50");
            pb.directory(repo);
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    commits.add(line);
                }
            }
        } catch (IOException e) {
            // ignore errors
        }
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(commits);
        Stage dialog = new Stage();
        dialog.initOwner(owner);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setTitle("Commit History");
        dialog.setScene(new Scene(listView, 600, 400));
        dialog.show();
    }
}