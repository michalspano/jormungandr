/***************************************************************************************************
 * The Snake Game - Jörmungandr
 * File: {@code SnakeMain.java}
 * Members: Michal Spano, Malte Bengtsson, Simone Graziosi, Feride Hansson, Anna Mäkinen, Katinka Romanus
 * For DIT094 Mini Project: Team Programming; SEM@GU.
 ***************************************************************************************************/

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

        /* event listener for the window closing in the upper corner
         * TODO: add a confirmation dialog (to all windows) */

        stage.setOnCloseRequest(event -> System.out.println("Stage is closing"));

        stage.setTitle("Snake Game – Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}