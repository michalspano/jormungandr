/***************************************************************************************************
 * The Snake Game - Jörmungandr
 * File: {@code SnakeMain.java}
 * Members: Michal Spano, Malte Bengtsson, Simone Graziosi, Feride Hansson, Anna Mäkinen, Katinka Romanus
 * For DIT094 Mini Project: Team Programming; SEM@GU.
 ***************************************************************************************************/

package com.example.snakegame;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

 /**
  * SnakeMain is a class that extends the {@code Application} {@code abstract} class and is responsible for starting the game.
  * It contains a static String variable (levelIdentifier) and an overridden method - {@code start()}.
  */
public class SnakeMain extends Application {
    public static String levelIdentifier; // stored for the optional game preloading

    @Override
    public void start(Stage stage) throws IOException {
        InstantiateScenes instantiateScenes = new InstantiateScenes();
        instantiateScenes.instantiateMenuScene(stage);
    }

    /**
     * Main method for running the Snake game.
     * @throws IllegalArgumentException if an invalid argument is detected.
     */
    public static void main(String[] args) {
        try {
            levelIdentifier = SnakeGameUtils.getLevelIdentifier(args);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Invalid argument: " + exception.getMessage());
        }
        launch();
    }
}