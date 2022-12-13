/***************************************************************************************************
 * The Snake Game - Jörmungandr
 * File: {@code InstantiateScenes.java}
 * Members: Michal Spano, Malte Bengtsson, Simone Graziosi, Feride Hansson, Anna Mäkinen, Katinka Romanus
 * For DIT094 Mini Project: Team Programming; SEM@GU.
 ***************************************************************************************************/

package com.example.snakegame;

import com.example.snakegame.snake.SnakeGame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;

/** Modulate the instantiation of scenes.
 * {@link #initialiseFXMLLoader(String)} is used to instantiate the FXML loader given the {@code fxmlFileName}.
 * The class is used to instantiate the scenes of the game.
 * It is used to avoid code duplication and enhance the readability of the code.
 * Each method only expects the stage as a parameter and instantiates the scene.
 * We would like to use the same stage for all the scenes - this is why we pass the stage as a parameter.
 */
public class InstantiateScenes {

    private FXMLLoader initialiseFXMLLoader(String fxmlSource) {
        try {
            return new FXMLLoader(getClass().getResource(fxmlSource));
        } catch (Exception exception) {
            System.out.println("Cannot get resource file: " + fxmlSource);
        }
        return null;
    }

    /**
     * Instantiate menu scene.
     *
     * @param stage the stage
     * @throws IOException the io exception
     */
    public void instantiateMenuScene(Stage stage) throws IOException {
        System.out.println("Menu Screen");

        FXMLLoader loader = initialiseFXMLLoader("snakeMenu.fxml");
        assert loader != null;

        Scene scene = new Scene(loader.load());

        SnakeGameUtils.applyExitGameAlertToStage(stage);

        stage.setTitle("Snake Game – Menu");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Instantiate game scene.
     *
     * @param stage the stage
     */
    public void instantiateGameScene(Stage stage) {
        System.out.println("Starting the game... ");

        JSONObject config = SnakeGameUtils.loadJSONConfig();
        JSONObject gameSettings = config.getJSONObject("gameSettings");

        // parse the game settings from the config file (JSON)
        int initSnakeSize   = gameSettings.getInt("initialSnakeSize");
        int cellSize        = gameSettings.getInt("cellSize");
        int rows            = gameSettings.getInt("rows");
        int columns         = gameSettings.getInt("columns");
        int speed           = gameSettings.getInt("speed");

        SnakeGame snakeGame = new SnakeGame(initSnakeSize, cellSize, rows, columns, speed);

        SnakeGameUtils.applyExitGameAlertToStage(stage);

        snakeGame.start(stage);
    }

    /**
     * Instantiate game over scene.
     * This method depends on the call from the {@link SnakeGame} class.
     *
     * @param stage the stage
     * @param score the score
     * @throws IOException the io exception
     */
    public void instantiateGameOverScene(Stage stage, int score) throws IOException {
        System.out.println("You died!");

        FXMLLoader loader = initialiseFXMLLoader("snakeGameOver.fxml");
        assert loader != null;

        Scene scene = new Scene(loader.load());

        // display a random quote
        SnakeGameOverController controller = loader.getController();
        controller.setRandomQuoteLabel();

        // update the score
        controller.setUserScore(score);

        SnakeGameUtils.applyExitGameAlertToStage(stage);

        stage.setTitle("Game Over");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}