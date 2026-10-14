package com.example.s3z2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Robot extends Canvas
{
    private final int SPEED = 5;
    private Pane pane;
    private int dirX = 1;
    private int dirY = 0;
    private Timeline t;
    private boolean isAuto = false;
    private Polygon arrow;

    public Robot(Pane pane_)
    {
        super(131, 133);

        pane = pane_;
        pane.getChildren().add(this);
        GraphicsContext gc = getGraphicsContext2D();

        setOnKeyPressed(ev -> handleWASD(ev));
        setFocusTraversable(true);
        requestFocus();

        draw(gc);

        setOnMousePressed(ev -> toggleAuto());
        t = new Timeline(new KeyFrame(Duration.millis(17), ev -> moveAuto()));
        t.setCycleCount(Timeline.INDEFINITE);

        double[] points = {0, -10, -8.66, 5, 8.66, 5};
        arrow = new Polygon(points);
        arrow.setLayoutX(64);
        arrow.setLayoutY(77);
        arrow.setRotate(90);
        pane.getChildren().add(arrow);
    }



    private void draw(GraphicsContext gc_)
    {
        final Color COL_BODY = new Color(0.85, 0.85, 0.85, 1);
        final Color COL_CHEST = new Color(0.75, 0.75, 0.75, 1);
        final Color COL_EYE = new Color(0.35, 0.73, 1, 1);

        final int BODY_WIDTH = 75;
        final int BODY_HEIGHT = 87;
        final int FOOT_OFFSET = 5;
        final int ARM_OFFSET = 12;
        final int ARM_WIDTH = 28;
        final int HEAD_WIDTH = 50;
        final int HEAD_HEIGHT = 33;
        final int EYE_OFFSET_X = 8;
        final int EYE_OFFSET_Y = 8;
        final int MOUTH_OFFSET_Y = 24;

        gc_.setFill(COL_BODY);
        gc_.fillRect(ARM_WIDTH, HEAD_HEIGHT, BODY_WIDTH, BODY_HEIGHT);

        gc_.setFill(COL_CHEST);
        gc_.fillRect(ARM_WIDTH + 12, HEAD_HEIGHT + 15, 50, 58);

        gc_.setFill(Color.BLACK);
        gc_.fillRect(ARM_WIDTH + FOOT_OFFSET, HEAD_HEIGHT + BODY_HEIGHT, 25, 13);
        gc_.fillRect(ARM_WIDTH + BODY_WIDTH - 25 - FOOT_OFFSET, HEAD_HEIGHT + BODY_HEIGHT, 25, 13);

        gc_.setFill(COL_BODY);
        gc_.fillRect(ARM_WIDTH + BODY_WIDTH, HEAD_HEIGHT + ARM_OFFSET, 28, 15);
        gc_.fillRect(0, HEAD_HEIGHT + ARM_OFFSET, ARM_WIDTH, 15);

        gc_.fillRect(ARM_WIDTH + 12, 0, HEAD_WIDTH, HEAD_HEIGHT);

        gc_.setFill(COL_EYE);
        gc_.fillRect(ARM_WIDTH + 12 + EYE_OFFSET_X, EYE_OFFSET_Y, 13, 8);
        gc_.fillRect(ARM_WIDTH + 12 + HEAD_WIDTH - 13 - EYE_OFFSET_X, EYE_OFFSET_Y, 13, 8);

        gc_.setFill(COL_CHEST);
        gc_.fillRect(ARM_WIDTH + 12 + EYE_OFFSET_X, MOUTH_OFFSET_Y, 34, 5);
    }

    private void handleWASD(KeyEvent ev)
    {
        if (isAuto) return;

        KeyCode kc = ev.getCode();

        switch (kc)
        {
            case A: { moveManual(-1, 0); arrow.setRotate(-90); break; }
            case D: { moveManual(1, 0); arrow.setRotate(90); break; }
            case W: { moveManual(0, -1); arrow.setRotate(0); break; }
            case S: { moveManual(0, 1); arrow.setRotate(180);break; }
        }
    }

    private void moveManual(int x, int y)
    {
        dirX = x;
        dirY = y;

        double currX = getLayoutX();
        double currY = getLayoutY();

        double newX = Math.min(Math.max(currX + x * SPEED, 0), pane.getWidth() - 131);
        double newY = Math.min(Math.max(currY + y * SPEED, 0), pane.getHeight() - 133);

        setPos(newX, newY);
    }

    private void toggleAuto()
    {
        isAuto = !isAuto;

        if (isAuto) t.play();
        else        t.stop();
    }

    private void moveAuto()
    {
        double currX = getLayoutX();
        double currY = getLayoutY();
        double nextX = currX + dirX * SPEED;
        double nextY = currY + dirY * SPEED;

        if (nextX > pane.getWidth() - 131 || nextX < 0)
        {
            dirX *= -1;
            nextX = currX + dirX * SPEED;
            arrow.setRotate(arrow.getRotate() + 180);
        }

        if (nextY > pane.getHeight() - 133 || nextY < 0)
        {
            dirY *= -1;
            nextY = currY + dirY * SPEED;
            arrow.setRotate(arrow.getRotate() + 180);
        }

        setPos(nextX, nextY);
    }

    private void setPos(double xPos, double yPos)
    {
        setLayoutX(xPos);
        setLayoutY(yPos);

        arrow.setLayoutX(xPos + 64);
        arrow.setLayoutY(yPos + 77);
    }
}
