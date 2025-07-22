package com.voidtoverse.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Sets up the main window showing commit quality info and controls.
 */
public final class CommitQualityFrame {
    private CommitQualityFrame() {}

    /**
     * Configure the provided stage with the UI components.
     *
     * @param stage JavaFX stage to populate
     */
    public static void setup(Stage stage) {
        Label quality = new Label("\uD83D\uDE04 Commit Quality: 92% \u2014 \"Perfect size, clean commit\"");
        Label msgLabel = new Label("Commit Message:");
        TextArea msgBox = new TextArea();
        Button commitBtn = new Button("Commit");
        Button advancedBtn = new Button("Advanced");
        Button switchBtn = new Button("Switch");
        HBox controls = new HBox(10, commitBtn, advancedBtn, switchBtn);
        VBox root = new VBox(10, quality, msgLabel, msgBox, controls);
        stage.setScene(new Scene(root, 500, 300));
        stage.setTitle("GitCodeQuality");
    }
}
