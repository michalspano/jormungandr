package com.example.snakegame;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * This is a static class that contains utility methods for the Snake Game.
 * The point is reusage of code.
 */
public class SnakeGameUtils {
    /**
     * Exit game alert.
     * @param currentScene the current scene
     */
    public static void exitGameAlert(Parent currentScene) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        // description of the alert
        alert.setTitle("Exit Game");
        alert.setHeaderText("You are about to exit our awesome game");
        alert.setContentText("Press OK to exit, or cancel to stay on this page.");
        alert.setGraphic(null); // TODO: we may later replace this with an image (e.g., a snake)

        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) currentScene.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Current scene string.
     *
     * @param event the event
     * @return the string
     */
    public String currentScene(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        return stage.getScene().toString();
    }
}
