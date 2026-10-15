package com.example.s4_z3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private Timeline rainDropper = new Timeline(new KeyFrame(Duration.millis(33), e -> dropRain()));
    private Timeline snowDropper = new Timeline(new KeyFrame(Duration.millis(66), e -> dropSnow()));
    private boolean isRaining = true;
    private boolean isSnowing = true;

    @FXML private Pane root;

    @FXML private void spawnCloud() { root.getChildren().add(new Cloud()); }
    @FXML private void toggleRain() {
        if (isRaining)  { rainDropper.stop(); }
        else            { rainDropper.play(); }

        isRaining = !isRaining;
    }
    @FXML private void toggleSnow() {
        if (isSnowing)  { snowDropper.stop(); }
        else            { snowDropper.play(); }

        isSnowing = !isSnowing;
    }
    @FXML private void blueClouds() {
        for (int i = 0; i < root.getChildren().size(); i++)
        {
            Node n = root.getChildren().get(i);
            if (n instanceof Cloud) { ((Cloud) n).makeBlue(); }
        }
    }
    @FXML private void moveLeft() {
        for (int i = 0; i < root.getChildren().size(); i++)
        {
            Node n = root.getChildren().get(i);
            if (n instanceof WeatherObject) { n.setLayoutX(n.getLayoutX() - 20); }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rainDropper.setCycleCount(Timeline.INDEFINITE);
        rainDropper.play();

        snowDropper.setCycleCount(Timeline.INDEFINITE);
        snowDropper.play();
    }

    private void dropRain()
    {
        root.getChildren().add(new Raindrop());
    }
    private void dropSnow()
    {
        root.getChildren().add(new Snowflake());
    }
}
