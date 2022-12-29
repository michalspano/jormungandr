/***************************************************************************************************
 * The Snake Game - Jörmungandr
 * File: {@code InstantiateScenes.java}
 * Members: Michal Spano, Malte Bengtsson, Simone Graziosi, Feride Hansson, Anna Mäkinen, Katinka Romanus
 * For DIT094 Mini Project: Team Programming; SEM@GU.
 ***************************************************************************************************/

package com.example.snakegame;

import com.example.snakegame.snake.GridPiece;
import com.example.snakegame.snake.SnakeDirection;
import com.example.snakegame.snake.SnakeGame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/** Modulate the instantiation of scenes.
 * {@link #initialiseFXMLLoader(String)} is used to instantiate the FXML loader given the {@code fxmlFileName}.
 * The class is used to instantiate the scenes of the game.
 * It is used to avoid code duplication and enhance the readability of the code.
 * Each method only expects the stage as a parameter and instantiates the scene.
 * We would like to use the same stage for all the scenes - this is wh  y we pass the stage as a parameter.
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
        scene.getStylesheets().add(SnakeGameUtils.CSS_STYLES.get("menu")); // apply menu.css

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

        int gamePreloadIndex = SnakeMain.gamePreloadIndex;

        JSONObject config = SnakeGameUtils.loadJSONObject("config");
        JSONObject gameSettings = config.getJSONObject("gameSettings");

        // parse the game settings from the config file (JSON) - shared for all games
        int initSnakeSize   = gameSettings.getInt("initialSnakeSize");
        int cellSize        = gameSettings.getInt("cellSize");
        int rows            = gameSettings.getInt("rows");
        int columns         = gameSettings.getInt("columns");
        int speed           = gameSettings.getInt("speed");
        int upperPadding    = gameSettings.getInt("upperPadding");

        SnakeGame snakeGame;

        if (gamePreloadIndex == 0) { // a type of game without any preloading
            snakeGame = new SnakeGame(initSnakeSize, cellSize, rows, columns, speed, upperPadding);
        } else {
            // marshal the game state from the JSON file
            JSONObject gamePreloadObject = SnakeGameUtils.loadJSONObject("gameScenario" + gamePreloadIndex);
            JSONObject gamePreload = gamePreloadObject.getJSONObject("gameState");

            // parse the game state from the JSON file and assign it to the constructor of the preloaded game

            int sessionScore            = gamePreload.getInt("sessionScore");
            int foodX                   = gamePreload.getInt("foodX");
            int foodY                   = gamePreload.getInt("foodY");
            SnakeDirection direction    = SnakeDirection.valueOf(gamePreload.getString("direction"));
            List<GridPiece> snake       = SnakeGameUtils.parseJSONArrayList(gamePreload.getJSONArray("snake"));
            List<GridPiece> enemyList   = SnakeGameUtils.parseJSONArrayList(gamePreload.getJSONArray("enemy"));
            List<GridPiece> blockList   = SnakeGameUtils.parseJSONArrayList(gamePreload.getJSONArray("block"));

            snakeGame = new SnakeGame(initSnakeSize, cellSize, rows, columns, speed, upperPadding,
                            sessionScore, direction, foodX, foodY, snake, enemyList, blockList);
        }

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
        scene.getStylesheets().add(SnakeGameUtils.CSS_STYLES.get("gameOver")); // apply gameOver.css

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