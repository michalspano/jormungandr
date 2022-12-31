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

public class SnakeMain extends Application {
    public static String levelIdentifier;

    @Override
    public void start(Stage stage) throws IOException {
        InstantiateScenes instantiateScenes = new InstantiateScenes();
        instantiateScenes.instantiateMenuScene(stage);
    }
    public static void main(String[] args) {
        try {
            levelIdentifier = SnakeGameUtils.getLevelIdentifier(args);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Invalid argument: " + exception.getMessage());
        }
        launch();
    }
}