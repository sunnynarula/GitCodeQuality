package com.voidtoverse.ui;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
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
        if (repo != null) {
            try {
                // Use JGit to retrieve the last 50 commits in abbreviated form
                org.eclipse.jgit.api.Git git = org.eclipse.jgit.api.Git.open(repo);
                Iterable<org.eclipse.jgit.revwalk.RevCommit> logs = git.log().setMaxCount(50).call();
                for (org.eclipse.jgit.revwalk.RevCommit commit : logs) {
                    String abbrev = commit.getId().abbreviate(7).name();
                    commits.add(abbrev + " " + commit.getShortMessage());
                }
                git.close();
            } catch (Exception e) {
                // ignore errors; fall back to empty history
            }
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