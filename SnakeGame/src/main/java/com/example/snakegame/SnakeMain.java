// SnakeGame

package com.example.snakegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        final int[] DIMENSIONS = { 500, 500 }; // dimensions

        FXMLLoader fxmlLoader = new FXMLLoader(SnakeMain.class.getResource("snake-game.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), DIMENSIONS[0], DIMENSIONS[1]);

        stage.setTitle("Snake Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}