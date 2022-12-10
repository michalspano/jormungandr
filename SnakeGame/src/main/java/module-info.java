/***************************************************************************************************
 * The Snake Game - Jörmungandr
 * File: {@code module-info.java} - the module-info file for the Snake Game
 * Members: Michal Spano, Malte Bengtsson, Simone Graziosi, Feride Hansson, Anna Mäkinen, Katinka Romanus
 * For DIT094 Mini Project: Team Programming; SEM@GU.
 ***************************************************************************************************/

module com.example.snakegame {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires org.json;

    opens com.example.snakegame to javafx.fxml;
    exports com.example.snakegame;
    exports com.example.snakegame.snake;
    opens com.example.snakegame.snake to javafx.fxml;
}