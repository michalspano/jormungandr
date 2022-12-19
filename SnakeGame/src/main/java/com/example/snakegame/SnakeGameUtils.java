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
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * This is a static class that contains utility methods for the Snake Game.
 * The point is re-usage of code.
 */
public class SnakeGameUtils {
    public static final Random random = new Random();

    /**
     * The constant images part of an anonymous class.
     */
    public static final Map<String, Image> IMAGES = new HashMap<>() {{
        put("head", new Image(Objects.requireNonNull(getClass().getResource("images/snake_head.png")).toExternalForm()));
        put("body", new Image(Objects.requireNonNull(getClass().getResource("images/snake_piece.png")).toExternalForm()));
        put("enemyBody", new Image(Objects.requireNonNull(getClass().getResource("images/snake_piece_puffer.png")).toExternalForm()));
        put("food", new Image(Objects.requireNonNull(getClass().getResource("images/puffer_fish.png")).toExternalForm()));
        put("gameBackground", new Image(Objects.requireNonNull(getClass().getResource("images/gameBackground.png")).toExternalForm()));
        put("enemy", new Image(Objects.requireNonNull(getClass().getResource("images/enemy.png")).toExternalForm()));
        put("block", new Image(Objects.requireNonNull(getClass().getResource("images/block.png")).toExternalForm()));
    }};

    /**
     * The constant CSS styles part of an anonymous class.
     */
    public static final Map<String, String> CSS_STYLES = new HashMap<>() {{
        put("menu", Objects.requireNonNull(getClass().getResource("css/menu.css")).toExternalForm());
        put("gameOver", Objects.requireNonNull(getClass().getResource("css/gameOver.css")).toExternalForm());
    }};

    /**
     * The constant JSON source part of an anonymous class.
     * We don't need to use the {@code getClass()} method here, since we are in a static context.
     */
    public static final Map<String, String> JSON_SOURCES = new HashMap<>() {{
        put("config", "SnakeGame/src/main/resources/config.json");
        put("score", "SnakeGame/src/main/resources/score.json");
    }};
    /**
     * Exit game alert.
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
     * Draw image.
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
    public static JSONObject loadJSONConfig() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(JSON_SOURCES.get("config"))));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return new JSONObject(jsonString);
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
    public static int getSessionMaxScore() {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(Files.readString(Paths.get(JSON_SOURCES.get("score"))));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return jsonObject.getInt("sessionMaxScore");
    }

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
     * Generate random RGB color.
     *
     * @return the color
     */
    public static Color generateRandomColor() {
        final int COLOR_OFFSET = 50;

        // range of each color is: COLOR_OFFSET to (255 - COLOR_OFFSET)
        int red = COLOR_OFFSET + (int)(Math.random() * (255 - COLOR_OFFSET));
        int green = COLOR_OFFSET + (int)(Math.random() * (255 - COLOR_OFFSET));
        int blue = COLOR_OFFSET + (int)(Math.random() * (255 - COLOR_OFFSET));

        return Color.rgb(red, green, blue);
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
