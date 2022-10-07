package com.lepesha.client.viewcontroller;

import com.lepesha.client.connect.Handler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static Handler handler;

    @FXML
    private TextField IPAddressField;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField portField;

    @FXML
    private TextField xField;

    @FXML
    private TextField yField;

    @FXML
    private Button connectButton;

    @FXML
    private Button sendDataButton;

    @FXML
    void sendData(ActionEvent event) {
        handler.prepareToSend(xField.getText(), yField.getText());
        double[] result = handler.produce();
        launchResultModalWindow(event, result);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IPAddressField.setText("127.0.0.1");
        portField.setText("8000");
        sendDataButton.setDisable(true);
    }

    public void launchAlert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }

    public void launchResultModalWindow(ActionEvent event, double[] result) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/lepesha/client/resultWindow.fxml"));
        Parent root = null;

        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResultViewController resultViewController = fxmlLoader.getController();

        resultViewController.setxLabelText("Координата x: " + result[0]);
        resultViewController.setyLabelText("Координата y: " + result[1]);
        resultViewController.setResultLabelText("Четверть, в которой распологается точка: " + Math.round(result[2]));

        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root));
        stage.show();

//        StackPane layout = new StackPane();
//
//        Scene secondScene = new Scene(layout, 230, 100);
//
//        Stage newWindow = new Stage();
//        newWindow.setTitle("Result stage");
//        newWindow.setScene(secondScene);
//
//        // Specifies the modality for new window.
//        newWindow.initModality(Modality.WINDOW_MODAL);
//
//        // Specifies the owner Window (parent) for new window
//        newWindow.initOwner(primaryStage);
//
//        // Set position of second window, related to primary window.
//        newWindow.setX(primaryStage.getX() + 200);
//        newWindow.setY(primaryStage.getY() + 100);
//
//        newWindow.show();
    }

    public void connectButton(ActionEvent event) {
        try {
            handler = new Handler(IPAddressField.getText(), Integer.valueOf(portField.getText()));
            sendDataButton.setDisable(false);
            connectButton.setDisable(true);
        } catch (ConnectException e) {
            sendDataButton.setDisable(true);
            connectButton.setDisable(false);
            launchAlert("Некорректный адрес или порт!", "Попробуйте ввести другой адрес или порт");
        }
    }
}