package com.voidtoverse;

import com.voidtoverse.ui.CommitQualityFrame;
import com.voidtoverse.persistence.Persistence;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

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

        // Apply a saved window layout or fall back to a default top-right placement
        double[] layout = Persistence.loadWindowLayout();
        if (layout != null && layout.length == 4) {
            stage.setX(layout[0]);
            stage.setY(layout[1]);
            stage.setWidth(layout[2]);
            stage.setHeight(layout[3]);
        } else {
            // Position the window in the top-right corner with default dimensions
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            double defaultWidth = 500;
            double defaultHeight = 300;
            stage.setWidth(defaultWidth);
            stage.setHeight(defaultHeight);
            stage.setX(bounds.getMaxX() - defaultWidth);
            stage.setY(0);
        }

        stage.show();

        // Persist the window layout whenever it changes so that it can be restored on next launch
        stage.xProperty().addListener((obs, oldVal, newVal) ->
                Persistence.saveWindowLayout(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight()));
        stage.yProperty().addListener((obs, oldVal, newVal) ->
                Persistence.saveWindowLayout(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight()));
        stage.widthProperty().addListener((obs, oldVal, newVal) ->
                Persistence.saveWindowLayout(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight()));
        stage.heightProperty().addListener((obs, oldVal, newVal) ->
                Persistence.saveWindowLayout(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}