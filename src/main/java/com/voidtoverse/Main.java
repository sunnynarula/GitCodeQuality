package com.voidtoverse;

import com.voidtoverse.ui.CommitQualityFrame;
import com.voidtoverse.ui.RepoPicker;
import com.voidtoverse.persistence.Persistence;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point launching the GitCodeQuality application.
 *
 * <p>This class delegates all UI construction to {@link CommitQualityFrame}. The
 * {@code start()} method is invoked by the JavaFX runtime, which then calls
 * into our helper class to build the window and show it. Keeping the UI
 * initialization in a separate class improves readability and makes the main
 * entry point easier to follow.</p>
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        // Attempt to restore the last opened repository
        String lastRepoPath = Persistence.loadLastRepository();
        java.io.File repo = null;
        if (lastRepoPath != null) {
            java.io.File candidate = new java.io.File(lastRepoPath);
            if (candidate.exists() && candidate.isDirectory()) {
                repo = candidate;
            }
        }
        // Configure the UI regardless of whether a repository is known. When repo is null
        // the frame will display a placeholder and disable commit controls.
        CommitQualityFrame.setup(stage, repo);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}