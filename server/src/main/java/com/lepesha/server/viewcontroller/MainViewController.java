package com.lepesha.server.viewcontroller;

import com.lepesha.server.connection.ServerHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.chrono.HijrahEra;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private ListView messageListView;

    @FXML
    private Label disconnectMessage;

    @FXML
    private TextField IPAddressField;

    @FXML
    private TextField portField;

    @FXML
    private Button startButton;

    public void launchAlert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }

    public void startButton(ActionEvent event) {
        try {
            ServerHandler serverHandler = new ServerHandler(Integer.valueOf(portField.getText()));
            serverHandler.setController(this);
            serverHandler.start();

            startButton.setDisable(true);
        } catch (Exception e) {
            launchAlert("Некорректный адрес или порт!", "Попробуйте ввести другой адрес или порт");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void addMessage(String message) {
        messageListView.getItems().addAll(message);
    }
}