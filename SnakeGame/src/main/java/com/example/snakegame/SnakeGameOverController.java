package com.example.snakegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Snake game over controller.
 */
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

    // TODO: actually display the score to the finalScoreCounter label

    /**
     * Sets user score.
     *
     * @param score the score
     */
    @FXML
    protected void setUserScore(int score) {
        System.out.println("Your score is: " + score);
        finalScoreCounter.setText("Score: " + score);
    }

    /**
     * Start game.
     *
     * @throws IOException the io exception
     */
    @FXML
    protected void startGame() throws IOException {
        Stage stage = getCurrentStage();
        instantiateScenes.instantiateGameScene(stage);
    }

    /**
     * Go to menu.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    protected void goToMenu(ActionEvent event) throws IOException {
        Stage stage = getCurrentStage();
        instantiateScenes.instantiateMenuScene(stage);
    }
}