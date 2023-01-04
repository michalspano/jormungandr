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
import org.json.JSONException;
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
     * The game scene is instantiated with loading a {@code JSON} file - a {@code config} file (initially).
     * Later, if desired by the user, the game can be preloaded with a predefined {@code JSON} file - signifying the abstract state of the game.
     * These resemble levels of the game and are stored in the {@code levels} folder. Moreover, the user can create their custom levels.
     * This is largely described in the user manual of the software.
     *
     * @param stage the stage
     */
    public void instantiateGameScene(Stage stage) {
        System.out.println("Starting the game... ");

        String levelIdentifier = SnakeMain.levelIdentifier;

        // obtain the config JSON object
        JSONObject config = SnakeGameUtils.loadJSONObject("config");

        JSONObject gameSettings;
        int initSnakeSize, cellSize;
        int rows, columns;
        int speed, upperPadding;

        /* The config of the game is used for non-preloaded games and preloaded games with custom levels.
         * Using a try-catch block if some values are not present in the config file.
         * Then, a JSONException is thrown and the process does not continue.
         */
        try {
            gameSettings    = config.getJSONObject("gameSettings");
            initSnakeSize   = gameSettings.getInt("initialSnakeSize");
            cellSize        = gameSettings.getInt("cellSize");
            rows            = gameSettings.getInt("rows");
            columns         = gameSettings.getInt("columns");
            speed           = gameSettings.getInt("speed");
            upperPadding    = gameSettings.getInt("upperPadding");

        } catch (Exception exception) {
            throw new JSONException("Ensure that the config file is in the correct format.");
        }

        SnakeGame snakeGame;

        // a type of game without any preloading
        if (levelIdentifier == null) {
            snakeGame = new SnakeGame(initSnakeSize, cellSize, rows, columns, speed, upperPadding);

        } else {
            // marshal the game state from the JSON file
            JSONObject gamePreloadObject = SnakeGameUtils.loadJSONObject(levelIdentifier);

            JSONObject gamePreload;
            int sessionScore, foodX, foodY;
            SnakeDirection direction;
            List<GridPiece> snake, enemyList, blockList;

            /* If the user wants to preload a game, the game state is loaded from the JSON file.
             * Using a try-catch block if some values are not present in the JSON file.
             * Then, a JSONException is thrown and the process does not continue.
             * In the following step, the game state is parsed from the JSON file and assigned
             * to the constructor of the preloaded game, given that no exception is thrown.
             */
            try {
                gamePreload     = gamePreloadObject.getJSONObject("gameState");
                sessionScore    = gamePreload.getInt("sessionScore");
                foodX           = gamePreload.getInt("foodX");
                foodY           = gamePreload.getInt("foodY");
                direction       = SnakeDirection.valueOf(gamePreload.getString("direction"));
                snake           = SnakeGameUtils.parseJSONArrayList(gamePreload.getJSONArray("snake"));
                enemyList       = SnakeGameUtils.parseJSONArrayList(gamePreload.getJSONArray("enemy"));
                blockList       = SnakeGameUtils.parseJSONArrayList(gamePreload.getJSONArray("block"));

            } catch (Exception exception) {
                throw new JSONException("Ensure that the JSON level file is in the correct format.");
            }

            // create an instance of the game with the preloaded game state
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