package client.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.entityClass.Admin;
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

public class AdminController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Admin> adminTable;

    @FXML
    private TableColumn<Admin, String> adminColumn;

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
        ClientInstance.INSTANCE.getInstance().send("getAdmin");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Admin> admins = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            Admin admin = new Admin();
            admin.setLogin(list.get(i));
            admins.add(admin);
        }
        adminColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        adminTable.setItems(admins);
    }
    @FXML
    void add() {
        addB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addAdmin", "");
    }

    @FXML
    void del() {
        delB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("delAdmin", "");
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
    }
}
