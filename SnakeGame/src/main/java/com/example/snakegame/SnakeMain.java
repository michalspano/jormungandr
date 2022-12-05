// SnakeGame - initialBaseline branch

package com.example.snakegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(SnakeMain.class.getResource("snake-game.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 500, 500);

        stage.setTitle("Snake Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}