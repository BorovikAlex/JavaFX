package client.controllers;

import client.entityClass.Firm;
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

public class FirmController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Firm> firmsTable;

    @FXML
    private TableColumn<Firm, Integer> idColumn;

    @FXML
    private TableColumn<Firm, String> firmColumn;

    @FXML
    private TableColumn<Firm, String> cityColumn;

    @FXML
    private TableColumn<Firm, String> streetColumn;

    @FXML
    private TableColumn<Firm, Integer> houseColumn;

    @FXML
    private Button addB;

    @FXML
    private Button delB;

    @FXML
    private Button setB;

    public void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        fillTableView();
    }

    public void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getFirm");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Firm> information = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 6);
            Firm info = new Firm();
            info.setId_firm(Integer.valueOf(infoString[0]));
            info.setFirm(infoString[1]);
            info.setCity(infoString[2]);
            info.setStreet(infoString[3]);
            info.setHouse(Integer.valueOf(infoString[4]));
            information.add(info);
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_firm"));
        firmColumn.setCellValueFactory(new PropertyValueFactory<>("firm"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        houseColumn.setCellValueFactory(new PropertyValueFactory<>("house"));
        firmsTable.setItems(information);
    }

    @FXML
    void add() {
        addB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addFirm", "");
    }

    @FXML
    void del() {
        delB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("delFirm", "");
    }

    @FXML
    void set() {
        setB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("setFirm", "");
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
    }
}
