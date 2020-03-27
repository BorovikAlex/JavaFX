package client.controllers;

import client.entityClass.Admin;
import client.entityClass.Furniture;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class FurnitureController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Furniture> furnitureTable;

    @FXML
    private TableColumn<Furniture, String> furnitureColumn;

    @FXML
    void initialize() {
        fillTableView();
    }

    public void fillTableView() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getFurniture");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Furniture> furnitures = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
           Furniture furniture = new Furniture();
            furniture.setFurniture(list.get(i));
            furnitures.add(furniture);
        }
        furnitureColumn.setCellValueFactory(new PropertyValueFactory<>("furniture"));
        furnitureTable.setItems(furnitures);
    }
    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
    }
}
