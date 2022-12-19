/***************************************************************************************************
 * The Snake Game - Jörmungandr
 * File: {@code SnakeMenuController.java}
 * Members: Michal Spano, Malte Bengtsson, Simone Graziosi, Feride Hansson, Anna Mäkinen, Katinka Romanus
 * For DIT094 Mini Project: Team Programming; SEM@GU.
 ***************************************************************************************************/

package com.example.snakegame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class SnakeMenuController {
    @FXML
    private Button startButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label theSnake;

    @FXML
    public ImageView logo;
    public ImageView menuBackground;

    @FXML
    private AnchorPane menuGameScene;

    Image gameLogo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/logo.png")));
    Image background = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/menu_background_image.jpg")));

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