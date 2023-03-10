/***************************************************************************************************
 * The Snake Game - Jörmungandr
 * File: {@code SnakeGameUtils.java} - the utility class for the Snake Game.
 * Members: Michal Spano, Malte Bengtsson, Simone Graziosi, Feride Hansson, Anna Mäkinen, Katinka Romanus
 * For DIT094 Mini Project: Team Programming; SEM@GU.
 ***************************************************************************************************/

package com.example.snakegame;

import javafx.scene.Node;
import javafx.scene.Parent;

import com.example.snakegame.snake.GridPiece;

import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * This is a static class that contains utility methods for the Snake Game.
 * The point is re-usage of code, and avoiding code duplication.
 */
public class SnakeGameUtils {
    public static final Random random = new Random();

    /**
     * The constant images part of an anonymous class.
     */
    public static final Map<String, Image> IMAGES = new HashMap<>() {{
        put("head", new Image(Objects.requireNonNull(getClass().getResource("images/game/snake_head.png")).toExternalForm()));
        put("body", new Image(Objects.requireNonNull(getClass().getResource("images/game/snake_piece.png")).toExternalForm()));
        put("enemyBody", new Image(Objects.requireNonNull(getClass().getResource("images/game/snake_piece_puffer.png")).toExternalForm()));
        put("food", new Image(Objects.requireNonNull(getClass().getResource("images/game/puffer_fish.png")).toExternalForm()));
        put("enemy", new Image(Objects.requireNonNull(getClass().getResource("images/game/enemy.png")).toExternalForm()));
        put("block", new Image(Objects.requireNonNull(getClass().getResource("images/game/block.png")).toExternalForm()));
        put("gameBackground", new Image(Objects.requireNonNull(getClass().getResource("images/gameBackground.png")).toExternalForm()));
    }};

    /**
     * The constant {@code CSS} styles part of an anonymous class.
     */
    public static final Map<String, String> CSS_STYLES = new HashMap<>() {{
        put("menu", Objects.requireNonNull(getClass().getResource("css/menu.css")).toExternalForm());
        put("gameOver", Objects.requireNonNull(getClass().getResource("css/gameOver.css")).toExternalForm());
    }};

    /**
     * The constant JSON source part of an anonymous class.
     * We don't need to use the {@code getClass()} method here, since we are in a static context.
     * The first 2 files define the {@code configuration} file and the {@code highscores} file.
     * The remaining {@code JSON} files are the {@code levels} files. Use the User Manual for more information
     * and how custom levels can be created. The {@code levels} files are stored in the {@code resources/levels} folder.
     */
    public static final Map<String, String> JSON_SOURCES = new HashMap<>() {{
        put("config", "SnakeGame/src/main/resources/config.json");
        put("score", "SnakeGame/src/main/resources/score.json");
        // snake levels (default)
        put("snakeLevel1", "SnakeGame/src/main/resources/levels/snakeLevel1.json");
        put("snakeLevel2", "SnakeGame/src/main/resources/levels/snakeLevel2.json");
        put("snakeLevel3", "SnakeGame/src/main/resources/levels/snakeLevel3.json");
        put("snakeLevel4", "SnakeGame/src/main/resources/levels/snakeLevel4.json");
        put("snakeLevel5", "SnakeGame/src/main/resources/levels/snakeLevel5.json");
    }};

    /**
     * Exit game alert.
     * An alert that asks the user if they want to exit the game.
     * @param currentScene the current scene
     */
    public static void exitGameAlert(Parent currentScene) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        // description of the alert
        alert.setTitle("Exit Game");
        alert.setHeaderText("You are about to exit our awesome game");
        alert.setContentText("Press OK to exit, or cancel to stay on this page.");
        alert.setGraphic(null); // TODO: we may later replace this with an image (e.g., a snake)

        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) currentScene.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Apply exit game alert to stage.
     *
     * @param stage the stage
     */
    public static void applyExitGameAlertToStage(Stage stage) {
        stage.setOnCloseRequest(event -> {
            event.consume();
            SnakeGameUtils.exitGameAlert(stage.getScene().getRoot());
        });
    }

    /**
     * Detect collision over iterable boolean.
     *
     * @param x        the x
     * @param y        the y
     * @param iterable the iterable
     * @return the boolean
     */
    public static boolean detectCollisionOverIterable(int x, int y, List<?> iterable) {
        return iterable.stream().anyMatch(piece -> {
            if (piece instanceof GridPiece gridPiece) {
                return gridPiece.getX() == x && gridPiece.getY() == y;
            }
            return false;
        });
    }

    /**
     * Draw image at x and y given the width and height.
     *
     * @param gc        the gc
     * @param imageName the image name
     * @param x         the x
     * @param y         the y
     * @param width     the width
     * @param height    the height
     */
    public static void drawImage(GraphicsContext gc, String imageName, int x, int y, int width, int height) {
        gc.drawImage(IMAGES.get(imageName), x, y, width, height);
    }

    /**
     * Load json config JSON object.
     *
     * @return the JSON object
     */
    public static JSONObject loadJSONObject(String JSONIdentifier) throws RuntimeException {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(JSON_SOURCES.get(JSONIdentifier))));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return new JSONObject(jsonString);
    }

    /**
     * Parse {@code JSON} array of objects to List of objects.
     *
     * @param jsonArray the json array
     * @return the list
     */
    public static List<GridPiece> parseJSONArrayList(JSONArray jsonArray) {
        // detect a null or empty array - return an empty list
        if (jsonArray == null || jsonArray.isEmpty()) return new ArrayList<>();

        // detect an array of empty object - return an empty list
        if (jsonArray.length() == 1 && jsonArray.get(0) instanceof JSONObject) return new ArrayList<>();

        // then, proceed to parse the array
        List<GridPiece> tempArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            tempArray.add(new GridPiece(
                    jsonObject.getInt("x"),
                    jsonObject.getInt("y"))
            );
        }
        return tempArray;
    }

    /**
     * Update game session (score).
     *
     * @param score the score
     */
    public static void updateGameSession(int score) {
        JSONObject jsonObject = new JSONObject() {{ put("sessionMaxScore", score); }};
        try {
            Files.writeString(Paths.get(JSON_SOURCES.get("score")), jsonObject.toString());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Gets session max score.
     *
     * @return the session max score
     */
    public static int getOverallMaxScore() {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(Files.readString(Paths.get(JSON_SOURCES.get("score"))));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return jsonObject.getInt("sessionMaxScore");
    }

    /**
     * Gets level identifier.
     * Ensure that the level identifier is valid; belongs to the JSON_SOURCES map.
     * Then, ensure that the file at the path exists.
     * If not, throw an exception.
     *
     * @param params the params
     * @return the level identifier
     * @throws Exception the exception
     */
    public static String getLevelIdentifier(String[] params) throws Exception {
        if (params.length == 0) return null; // default value

        String levelIdentifier = params[0];
        if (JSON_SOURCES.containsKey(levelIdentifier)) {
            String tempLevelPath = JSON_SOURCES.get(levelIdentifier);
            if (tempLevelPath != null && Files.exists(Paths.get(tempLevelPath))) {
                return levelIdentifier;
            }
            throw new Exception(String.format("The level file at path %s does not exist.", tempLevelPath));
        }
        throw new Exception(String.format("The level identifier %s is not valid.", levelIdentifier));
    }

    /* Additional methods */

    /**
     * Snake logger.
     *
     * @param snake the snake
     */
    public static void snakeLogger(List<GridPiece> snake) {
        for (GridPiece sp : snake) { System.out.print(sp + " "); }
        System.out.println(); // end line
    }

    /**
     * Simply show a 'TODO' message and exit the thread.
     */
    public static void TODO() {
        System.out.println("\u001B[31m" + "TODO: method not implemented" + "\u001B[0m");
        System.exit(1);
    }

    /**
     * Current scene string.
     *
     * @param event the event
     * @return the string
     */
    public String currentScene(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        return stage.getScene().toString();
    }
}
