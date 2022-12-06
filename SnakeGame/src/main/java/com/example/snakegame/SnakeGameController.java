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

public class SnakeGameController {

    @FXML
    private Button gameOverButton;

    @FXML
    protected void gameOver(ActionEvent event) throws IOException {
        System.out.println("You died!");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("snakeGameOver.fxml"));
        Parent root = loader.load();

        SnakeGameOverController snakeGameOverController = loader.getController();
        snakeGameOverController.updateRandomQuoteLabel();

        // snakeGameOverController.updateUserScore(10);

        // get the stage from the event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        // set the title of the stage
        stage.setTitle("Game Over");

        // set the scene to the desired fxml file
        stage.setScene(scene);
        stage.show();
    }
}