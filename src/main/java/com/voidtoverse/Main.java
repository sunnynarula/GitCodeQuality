package com.voidtoverse;

import com.voidtoverse.ui.CommitQualityFrame;
import com.voidtoverse.ui.RepoPicker;
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
        // Present repository selection dialog before launching main UI
        java.io.File repo = RepoPicker.chooseRepository(stage);
        if (repo == null) {
            // user cancelled; do not open the app
            stage.close();
            return;
        }
        // pass selected repo into commit quality frame
        CommitQualityFrame.setup(stage, repo);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}