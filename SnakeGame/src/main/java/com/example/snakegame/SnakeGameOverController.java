/***************************************************************************************************
 * The Snake Game - Jörmungandr
 * File: {@code SnakeGameOverController.java}
 * Members: Michal Spano, Malte Bengtsson, Simone Graziosi, Feride Hansson, Anna Mäkinen, Katinka Romanus
 * For DIT094 Mini Project: Team Programming; SEM@GU.
 ***************************************************************************************************/

package com.example.snakegame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeGameOverController {

    @FXML
    private Button homeButton;

    @FXML
    private Button restartButton;

    @FXML
    private Label randomQuote;

    @FXML
    private Label finalScoreCounter;

    @FXML
    private AnchorPane gameOverScene;

    InstantiateScenes instantiateScenes = new InstantiateScenes();

    private Stage getCurrentStage() {
        return (Stage) gameOverScene.getScene().getWindow();
    }

    /**
     * Sets random quote label.
     */
    @FXML
    protected void setRandomQuoteLabel() {
        QuoteGenerator quoteGenerator = new QuoteGenerator();
        randomQuote.setText(quoteGenerator.getRandomQuote());
    }

    /**
     * Sets user score.
     *
     * @param score the score
     */
    @FXML
    protected void setUserScore(int score) {
        finalScoreCounter.setText("Score: " + score);
    }

    /**
     * Start game.
     */
    @FXML
    protected void startGame() {
        Stage stage = getCurrentStage();
        instantiateScenes.instantiateGameScene(stage);
    }

    /**
     * Go to menu.
     *
     * @throws IOException the io exception
     */
    @FXML
    protected void goToMenu() throws IOException {
        Stage stage = getCurrentStage();
        instantiateScenes.instantiateMenuScene(stage);
    }
}