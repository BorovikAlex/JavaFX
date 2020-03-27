package client.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class DelFirmController {

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> firmBox;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getFirmName");
        ArrayList<String> firmsList = client.receiveResultList();;
        ObservableList<String> firm = FXCollections.observableArrayList(firmsList);
       firmBox.setItems(firm);
    }
    public void del() {
        if (firmBox.getValue().equals("")) {
            AlertWindow.display("Вы не выбрали фирму!");
            return;
        }


        ClientInstance.INSTANCE.getInstance().send(("delFirm " + firmBox.getValue()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Данные удалены успешно!");
        }
    }
    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("firm", "");
    }
}
