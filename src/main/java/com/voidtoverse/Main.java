package com.voidtoverse;

import com.voidtoverse.ui.CommitQualityFrame;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point launching the GitCodeQuality application.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        // delegate UI creation to separate class
        CommitQualityFrame.setup(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
