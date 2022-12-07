package com.example.snakegame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeGameController {

    @FXML
    private Button gameOverButton;

    @FXML
    private AnchorPane gameScene;

    InstantiateScenes instantiateScenes = new InstantiateScenes();

    @FXML
    protected void gameOver() throws IOException {
        Stage stage = (Stage) gameScene.getScene().getWindow();
        instantiateScenes.instantiateGameOverScene(stage);
    }
}