package com.example.snakegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeMenuController {
    @FXML
    private Button startButton;

    @FXML
    private Button exitButton;

    @FXML
    private AnchorPane menuGameScene;


    // method to determine the current scene loaded to the stage
    protected String currentScene(ActionEvent event) {
        // get the stage from the event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        return stage.getScene().toString();
    }

    /* Explanation:
     * This is a method that will be called when navigation to a new scene is required.
     * It will load the FXML file, create a new scene, and then set the stage to use that scene.
     * */

    protected void loadFXML(ActionEvent event, String fxmlPath, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath)); // load the fxml file

        // get the stage from the event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        // set the title of the stage
        stage.setTitle(title);

        // set the scene to the desired fxml file
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void startGame(ActionEvent event) throws IOException {
        System.out.println("Start game");

        Parent root = FXMLLoader.load(getClass().getResource("snakeGame.fxml")); // load the fxml file

        // get the stage from the event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        // read user input from the keyboard
        // FIXME: this should be revisited
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W, UP -> System.out.println("UP");
                case S, DOWN -> System.out.println("DOWN");
                case A, LEFT -> System.out.println("LEFT");
                case D, RIGHT -> System.out.println("RIGHT");
                default -> System.out.println("OTHER");
            }
        });

        // set the title of the stage
        stage.setTitle("Snake Game");

        // set the scene to the desired fxml file
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void exitGame() {
        // abort the application process
        // TODO: replace by the alert and a proper exit (not aborting the process)

        // System.out.println("Exit game.");
        // System.exit(0);

        // Task: alert the user before exiting the game
        // ensure that the user wants to exit the game by clicking on the OK button

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Game");
        alert.setHeaderText("You are about to exit our awesome game");
        alert.setContentText("Press OK to exit, or cancel to stay on this page.");
        alert.setGraphic(null);

        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) menuGameScene.getScene().getWindow();
            stage.close();
        }
    }
}