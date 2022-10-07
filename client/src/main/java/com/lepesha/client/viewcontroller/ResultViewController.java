package com.lepesha.client.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultViewController {
    @FXML
    private Label resultLabel;

    @FXML
    private Label xLabel;

    @FXML
    private Label yLabel;

    public void setResultLabelText(String result) {
        resultLabel.setText(result);
    }

    public void setxLabelText(String result) {
        xLabel.setText(result);
    }

    public void setyLabelText(String result) {
        yLabel.setText(result);
    }
}
