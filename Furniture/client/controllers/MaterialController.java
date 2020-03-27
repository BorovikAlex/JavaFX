package client.controllers;

import client.entityClass.Admin;
import client.entityClass.Material;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MaterialController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Material> materialTable;

    @FXML
    private TableColumn<Material, String> materialColumn;

    @FXML
    private Button addB;

    @FXML
    private Button delB;

    @FXML
    void initialize() {
        fillTableView();
    }

    public void fillTableView() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getMaterial");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Material> materials = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
           Material material = new Material();
            material.setMaterial(list.get(i));
            materials.add(material);
        }
        materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
        materialTable.setItems(materials);
    }
    @FXML
    void add() {
        addB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addMaterial", "");
    }

    @FXML
    void del() {
        delB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("delMaterial", "");
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
    }
}
