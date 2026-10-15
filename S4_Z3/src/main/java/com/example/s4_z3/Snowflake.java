package com.example.s4_z3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Snowflake extends WeatherObject{
    private final double FALL_SPEED = 1.25;
    private final double SWAY_SPEED = 0.05;
    private final double SWAY_DIST = 15;
    private double swayFrame = Math.random() * Math.PI * 2;

    public Snowflake()
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
        double[] xPoints = {0, 0, 0, 0, 0, 0};
        double[] yPoints = {0, 0, 0, 0, 0, 0};

        for (int i = 0; i < 6; i++)
        {
            xPoints[i] = 5 + Math.cos(Math.PI / 3 * i) * 5;
            yPoints[i] = 5 + Math.sin(Math.PI / 3 * i) * 5;
        }

        gc.setFill(Color.WHITE);
        gc.fillPolygon(xPoints, yPoints, 6);
    }

    protected void move()
    {
        double currY = getLayoutY();
        double nextY = currY + FALL_SPEED;
        if (nextY > 480)    { ((Pane) getParent()).getChildren().remove(this); t.stop(); }
        else
        {
            setLayoutY(nextY);
            setLayoutX(posX + Math.sin(swayFrame) * SWAY_DIST);
            swayFrame += SWAY_SPEED;
        }
    }
}
