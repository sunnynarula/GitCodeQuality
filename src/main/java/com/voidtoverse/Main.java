package com.voidtoverse;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button; // button class for interactive controls
import javafx.scene.control.Label; // displays text such as headings
import javafx.scene.control.TextArea; // multi-line commit message box
import javafx.scene.layout.HBox; // horizontal layout for buttons
import javafx.scene.layout.VBox; // vertical layout for all controls
import javafx.stage.Stage;

/**
 * Entry point showing a basic JavaFX window.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        // create a banner showing the quality information
        Label quality = new Label("\uD83D\uDE04 Commit Quality: 92% \u2014 \"Perfect size, clean commit\"");

        // create label for the commit message box
        Label msgLabel = new Label("Commit Message:");

        // text area lets the user enter a commit message
        TextArea msgBox = new TextArea();

        // buttons for commit action, advanced mode and switch toggle
        Button commitBtn = new Button("Commit");
        Button advancedBtn = new Button("Advanced");
        Button switchBtn = new Button("Switch");

        // arrange buttons in a row with spacing
        HBox controls = new HBox(10, commitBtn, advancedBtn, switchBtn);

        // vertical layout puts everything in order
        VBox root = new VBox(10, quality, msgLabel, msgBox, controls);

        // create the window scene with all controls
        stage.setScene(new Scene(root, 500, 300));

        // give the window a simple title
        stage.setTitle("GitCodeQuality");

        // show the window on screen
        stage.show();
    }

    public static void main(String[] args) {
        // start the JavaFX application
        launch(args);
    }
}
