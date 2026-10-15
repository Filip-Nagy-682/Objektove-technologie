package com.example.s4_z3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Raindrop extends WeatherObject{
    private final double FALL_SPEED = 5;

    public Raindrop()
    {
        super();
        draw();

        posX = Math.random() * 630;
        posY = -20;

        setLayoutX(posX);
        setLayoutY(posY);

        t = new Timeline(new KeyFrame(Duration.millis(17), e -> move()));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    protected void draw()
    {
        gc.setFill(Color.AQUA);
        gc.fillOval(0, 0, 10, 20);
    }

    protected void move()
    {
        double currY = getLayoutY();
        double nextY = currY + FALL_SPEED;
        if (nextY > 480)    { ((Pane) getParent()).getChildren().remove(this); t.stop(); }
        else                { setLayoutY(nextY); }
    }
}
