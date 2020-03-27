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

public class DelMaterialController {
    @FXML
    private Button backButton;

    @FXML
    private Button delB;

    @FXML
    private ChoiceBox<String> materialBox;
    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getMaterial");
        ArrayList<String> materialsList = client.receiveResultList();;
        ObservableList<String> material = FXCollections.observableArrayList(materialsList);
        materialBox.setItems(material);
    }
    public void del() {
        if (materialBox.getValue().equals("")) {
            AlertWindow.display("Вы не выбрали материал!");
            return;
        }


        ClientInstance.INSTANCE.getInstance().send(("delMaterial " + materialBox.getValue()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Данные удалены успешно!");
        }
    }
    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("material", "");
    }
}
