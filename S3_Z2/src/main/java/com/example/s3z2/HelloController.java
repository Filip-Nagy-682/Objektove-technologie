package com.example.s3z2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML private Pane root;
    Robot robot;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        robot = new Robot(root);
    }
}
