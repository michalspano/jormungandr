package com.example.snakegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeMainController {
    @FXML
    private Button startButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button dieButton;

    /* Explanation:
    * TODO: add a comment here to explain the point and the use of the loadFXML method
    * */

    public void loadFXML(ActionEvent event, String fxmlPath, String title) throws IOException {
        // load the snakeGame.fxml file
        // set the scene to the snakeGame.fxml file
        // set the title to "Snake Game"

        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));

        // get the stage from the event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public void startGame(ActionEvent event) throws IOException {
        System.out.println("Start game");
        loadFXML(event, "snakeGame.fxml", "Snake Game Menu");
    }

    public void exitGame() {
        // abort the application process
        System.exit(0);
    }

    public void die() {
        System.out.println("You died!");
    }
}