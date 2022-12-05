// SnakeGame - initialBaseline branch

package com.example.snakegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeMain extends Application {
    static final String FXML_SOURCE = "snakeMenu.fxml";

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(SnakeMain.class.getResource(FXML_SOURCE));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Snake Game – Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}