package com.example.s4_z3;

import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public abstract class WeatherObject extends Canvas {
    protected double posX;
    protected double posY;
    protected GraphicsContext gc;
    protected Timeline t;

    public WeatherObject()
    {
        super(40, 25);
        gc = getGraphicsContext2D();
    }

    protected abstract void draw();
    protected abstract void move();
}
