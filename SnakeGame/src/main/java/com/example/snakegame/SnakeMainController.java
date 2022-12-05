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
    private Button gameOverButton;

    @FXML
    private Button homeButton;


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
    protected void gameOver(ActionEvent event) throws IOException {
        System.out.println("You died!");
        loadFXML(event, "snakeGameOver.fxml", "Game Over");
    }

    @FXML
    protected void goToMenu(ActionEvent event) throws IOException {
        System.out.println("Go to menu");
        loadFXML(event, "snakeMenu.fxml", "Snake Game - Menu");
    }

    @FXML
    protected void exitGame() {
        // abort the application process
        System.out.println("Exit game.");
        System.exit(0);
    }
}