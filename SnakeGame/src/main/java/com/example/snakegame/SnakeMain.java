// SnakeGame - initialBaseline branch

package com.example.snakegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeMain extends Application {
    static final String SNAKE_MENU_FXML = "snakeMenu.fxml";

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(SnakeMain.class.getResource(SNAKE_MENU_FXML));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Snake Game – Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}