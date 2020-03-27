package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SetFirmController {

    @FXML
    private Button backButton;

    @FXML
    private TextField city;

    @FXML
    private TextField street;

    @FXML
    private TextField firm;

    @FXML
    private TextField house;

    public void setFirm() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (city.getText().equals("") || street.getText().equals("") || house.getText().equals("") || firm.getText().equals("")) {
            AlertWindow.display("Заполните все поля!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("setFirm " + firm.getText() + " " + city.getText() + " " + street.getText() + " " + house.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Данные изменены успешно!");
            firm.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("firm", "");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("firm", "");
    }
}
