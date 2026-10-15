package com.example.s4_z3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Cloud extends WeatherObject{
    private final double MOVE_SPEED = 1;
    private int moveDir = 1;

    public Cloud()
    {
        super();
        draw();

        posX = Math.random() * 600;
        posY = Math.random() * 100;
        setLayoutX(posX);
        setLayoutY(posY);

        t = new Timeline(new KeyFrame(Duration.millis(17), e -> move()));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    protected void draw()
    {
        gc.setFill(Color.GREY);
        gc.fillOval(0, 0, 40, 25);
    }

    protected void move()
    {
        double currX = getLayoutX();
        double nextX = currX + MOVE_SPEED * moveDir;
        if (nextX < 0 || nextX > 600)
        {
            moveDir = -moveDir;
            nextX = currX + MOVE_SPEED * moveDir;
        }

        setLayoutX(nextX);
    }

    public void makeBlue()
    {
        gc.clearRect(0, 0, 40, 25);
        gc.setFill(Color.DARKBLUE);
        gc.fillOval(0, 0, 40, 25);
    }
}
