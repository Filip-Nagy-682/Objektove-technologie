package com.example.s2_z2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable
{
    @FXML private TextField numTF;
    @FXML private ChoiceBox<String> unitCB;
    @FXML private Label mmLb;
    @FXML private Label cmLb;
    @FXML private Label mLb;
    @FXML private Label kmLb;
    @FXML private Label convertLb;
    @FXML private Label errorLb;
    private Label[] labelArr = new Label[4];
    private int[] powArr = {3, 2, 0, -3};
    private String[] unitArr = {"mm", "cm", "m", "km"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelArr = new Label[] {mmLb, cmLb, mLb, kmLb};

        unitCB.getItems().addAll("mm", "cm", "m", "km");
        unitCB.getSelectionModel(). select(2);

        convert();
    }

    @FXML protected void convert()
    {
        try {
            double num = Double.parseDouble(numTF.getText());
            int unit = unitCB.getSelectionModel().getSelectedIndex();

            for (int i = 0; i < 4; i++) {
                int exp = powArr[i] - powArr[unit];
                double output = num * Math.pow(10, exp);

                labelArr[i].setText(output + " " + unitArr[i]);
            }
            convertLb.setText(num + " " + unitArr[unit] + " is equal to:");
            errorLb.setText("");
        } catch (Exception e) {
            errorLb.setText("Please enter a valid number!");
        }
    }
}
