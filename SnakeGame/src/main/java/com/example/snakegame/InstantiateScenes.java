package com.example.snakegame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** Modulate the instantiation of scenes.
 * {@link #initialiseFXMLLoader(String)} is used to instantiate the FXML loader given the {@code fxmlFileName}.
 * The class is used to instantiate the scenes of the game.
 * It is used to avoid code duplication and ehance the readability of the code.
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

        stage.setTitle("Snake Game – Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Instantiate the start scene.
     *
     * @param stage the stage
     * @throws IOException the io exception
     */
    public void instantiateGameScene(Stage stage) throws IOException {
        System.out.println("Starting the game... ");

        FXMLLoader loader = initialiseFXMLLoader("snakeGame.fxml");
        assert loader != null;

        Scene scene = new Scene(loader.load());

        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W, UP -> System.out.println("UP");
                case S, DOWN -> System.out.println("DOWN");
                case A, LEFT -> System.out.println("LEFT");
                case D, RIGHT -> System.out.println("RIGHT");
                default -> System.out.println("OTHER");
            }
        });

        stage.setTitle("Snake Game");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Instantiate game over scene.
     *
     * @param stage the stage
     * @throws IOException the io exception
     */
    public void instantiateGameOverScene(Stage stage) throws IOException {
        System.out.println("You died!");

        FXMLLoader loader = initialiseFXMLLoader("snakeGameOver.fxml");
        assert loader != null;

        Scene scene = new Scene(loader.load());

        SnakeGameOverController controller = loader.getController();
        controller.setRandomQuoteLabel();

        /* TODO: fix this so that it actually displays the score (not a pre-defined value)
         *       now, it just displays some random number */

        int someScore = (int) (Math.random() * 20 + 1);
        controller.setUserScore(someScore);

        stage.setTitle("Game Over");
        stage.setScene(scene);
        stage.show();
    }
}
