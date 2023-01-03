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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The {@code SnakeGameOverController} class is the controller class for the "Game Over" scene in the Snake game.
 * It contains various GUI elements and their respective event handlers.
 **/

public class SnakeGameOverController {
    @FXML
    private Button homeButton;
    @FXML
    private Button restartButton;
    @FXML
    private Label randomQuote;
    @FXML
    public ImageView gameOverAnimation;
    @FXML
    public Label gameOverHeading;
    @FXML
    private Label finalScoreCounter;
    @FXML
    private AnchorPane gameOverScene;

    InstantiateScenes instantiateScenes = new InstantiateScenes();

    /**
     * This method returns the current Stage object for the game.
     * @return the current Stage object.
     */
    private Stage getCurrentStage() {
        return (Stage) gameOverScene.getScene().getWindow();
    }

    /**
     * Sets random quote label.
     */
    @FXML
    protected void setRandomQuoteLabel() {
        QuoteGenerator quoteGenerator = null;
        try {
            quoteGenerator = new QuoteGenerator("quotes.txt"); // static file with quotes
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        assert quoteGenerator != null; // detect an error if the quote generator is not initialized
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
     * Start game (from the restart button).
     */
    @FXML
    protected void startGame() {
        Stage stage = getCurrentStage();
        instantiateScenes.instantiateGameScene(stage);
    }

    /**
     * Go to menu (from the menu button).
     *
     * @throws IOException the io exception
     */
    @FXML
    protected void goToMenu() throws IOException {
        Stage stage = getCurrentStage();
        instantiateScenes.instantiateMenuScene(stage);
    }
}