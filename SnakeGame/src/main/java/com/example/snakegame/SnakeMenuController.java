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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

 /**
 *Controller class for the menu scene of the Snake game.
 *Contains UI elements and logic for the start and exit buttons, as well as the logo and background image.
 */
public class SnakeMenuController {
    @FXML
    private Button startButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label snakeHeading; // Heading label for the game
    @FXML
    public ImageView logo; // Logo image for the game
    public ImageView menuBackground; // Background image for the menu scene
    @FXML
    private AnchorPane menuGameScene;

    InstantiateScenes instantiateScenes = new InstantiateScenes(); // composition

     /**
     *Method for launching the game when the start button is clicked.
     */
    @FXML
    protected void startGame() {
        Stage stage = (Stage) menuGameScene.getScene().getWindow();
        instantiateScenes.instantiateGameScene(stage);
    }
     /**
     *Method for exiting the game when the exit button is clicked.
     *Displays an alert to confirm the user's decision to exit.
     */
    @FXML
    protected void exitGame() {
        SnakeGameUtils.exitGameAlert(menuGameScene);
    }
}