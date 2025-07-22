package com.voidtoverse;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Entry point showing a basic JavaFX window.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("GitCodeQuality - Sprint 1");
        stage.setScene(new Scene(label, 300, 200));
        stage.setTitle("GitCodeQuality");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
