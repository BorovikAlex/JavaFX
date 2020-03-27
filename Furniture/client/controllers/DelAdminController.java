package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;

public class DelAdminController {

    @FXML
    private Button backButton;

    @FXML
    private Button delB;

    @FXML
    private ChoiceBox<String> adminBox;
    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getAdmin");
        ArrayList<String> adminsList = client.receiveResultList();;
        ObservableList<String> login = FXCollections.observableArrayList(adminsList);
        adminBox.setItems(login);
    }
    public void del() {
        if (adminBox.getValue().equals("")) {
            AlertWindow.display("Вы не ввели логин!");
            return;
        }
        if (adminBox.getValue().equals("admin")) {
            AlertWindow.display("Этого админа нельзя удалить!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("delAdmin " + adminBox.getValue()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Данные удалены успешно!");
        }
    }
    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("admin", "");
    }
}
