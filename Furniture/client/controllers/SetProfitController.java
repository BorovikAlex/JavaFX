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
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class SetProfitController {
    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> firmBox;

    @FXML
    private ChoiceBox<String> furnitureBox;

    @FXML
    private ChoiceBox<String> materialBox;

    @FXML
    private ChoiceBox<String> idBox;

    @FXML
    private TextField costs;

    @FXML
    private TextField price;

    @FXML
    private TextField profit;


    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getFurniture");
        ArrayList<String> furnitureList = client.receiveResultList();
        client.send("getFirmName");
        ArrayList<String> firmNameList = client.receiveResultList();
        client.send("getMaterial");
        ArrayList<String> materialList = client.receiveResultList();
        client.send("getId");
        ArrayList<String> idList = client.receiveResultList();
        ObservableList<String> furniture = FXCollections.observableArrayList(furnitureList);
        ObservableList<String> firmName = FXCollections.observableArrayList(firmNameList);
        ObservableList<String> material = FXCollections.observableArrayList(materialList);
        ObservableList<String> id = FXCollections.observableArrayList(idList);
        firmBox.setItems(firmName);
        furnitureBox.setItems(furniture);
        materialBox.setItems(material);
        idBox.setItems(id);
    }

    public void addcost() {
        profit.setText(String.valueOf(Double.parseDouble(price.getText()) - Double.parseDouble(costs.getText())));
    }

    public void set() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (costs.getText().equals("") || price.getText().equals("") || profit.getText().equals("")
                || furnitureBox.getValue() == null || firmBox.getValue() == null || materialBox.getValue() == null || idBox.getValue() == null) {
            AlertWindow.display("Вы не заполнили поля!");
            return;
        }

        StringBuilder info = new StringBuilder();
        info.append("setProfit " + firmBox.getValue() + " " + furnitureBox.getValue() +
                " " + materialBox.getValue() + " " + costs.getText() +
                " " + price.getText() + " " + profit.getText()+ " " + idBox.getValue() );
        AlertWindow.display("Данные изменены успешно!");
        ClientInstance.INSTANCE.getInstance().send(info.toString());
        if (!ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Ошибка добавления");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("profit", "");
    }
}
