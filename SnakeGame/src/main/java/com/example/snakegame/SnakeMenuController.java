/***************************************************************************************************
 * The Snake Game - Jörmungandr
 * File: {@code SnakeMenuController.java}
 * Members: Michal Spano, Malte Bengtsson, Simone Graziosi, Feride Hansson, Anna Mäkinen, Katinka Romanus
 * For DIT094 Mini Project: Team Programming; SEM@GU.
 ***************************************************************************************************/

package com.example.snakegame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SnakeMenuController {
    @FXML
    private Button startButton;

    @FXML
    private Button exitButton;

    @FXML
    private AnchorPane menuGameScene;

    InstantiateScenes instantiateScenes = new InstantiateScenes(); // composition

    @FXML
    protected void startGame() {
        Stage stage = (Stage) menuGameScene.getScene().getWindow();
        instantiateScenes.instantiateGameScene(stage);
    }

    @FXML
    protected void exitGame() {
        SnakeGameUtils.exitGameAlert(menuGameScene);
    }
}