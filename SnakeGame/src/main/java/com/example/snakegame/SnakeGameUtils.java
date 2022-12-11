package com.example.snakegame.utils;

import com.example.snakegame.snake.GridPiece;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

/**
 * This is a static class that contains utility methods for the Snake Game.
 * The point is reusage of code.
 */
public class SnakeGameUtils {
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
    public static final Random random = new Random();

    /**
     * The constant images part of an anonymous class.
     */
    public static final Map<String, Image> images = new HashMap<>() {{
        put("head", new Image(Objects.requireNonNull(getClass().getResource("images/snake_head.png")).toExternalForm()));
        put("body", new Image(Objects.requireNonNull(getClass().getResource("images/snake_piece.png")).toExternalForm()));
        put("enemyBody", new Image(Objects.requireNonNull(getClass().getResource("images/snake_piece_puffer.png")).toExternalForm()));
        put("food", new Image(Objects.requireNonNull(getClass().getResource("images/puffer_fish.png")).toExternalForm()));
        put("gameBackground", new Image(Objects.requireNonNull(getClass().getResource("images/gameBackground.png")).toExternalForm()));
        put("enemy", new Image(Objects.requireNonNull(getClass().getResource("images/enemy.png")).toExternalForm()));
        put("block", new Image(Objects.requireNonNull(getClass().getResource("images/block.png")).toExternalForm()));
    }};

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
        gc.drawImage(images.get(imageName), x, y, width, height);
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
