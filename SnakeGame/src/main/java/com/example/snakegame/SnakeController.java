package com.example.snakegame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SnakeController {
    @FXML
    private Label someText;

    @FXML
    protected void onHelloButtonClick() {
        someText.setText("Mini Project Group 10 ~ 'Jörmungandr'");
    }
}