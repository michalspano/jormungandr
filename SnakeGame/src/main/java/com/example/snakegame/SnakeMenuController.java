package com.example.snakegame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    InstantiateScenes instantiateScenes = new InstantiateScenes(); // composition

    @FXML
    protected void startGame() throws IOException {
        Stage stage = (Stage) menuGameScene.getScene().getWindow();
        instantiateScenes.instantiateGameScene(stage);
    }

    @FXML
    protected void exitGame() {
        SnakeGameUtils.exitGameAlert(this.menuGameScene);
    }
}