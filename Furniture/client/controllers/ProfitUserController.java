package client.controllers;

import client.entityClass.Profit;
import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ProfitUserController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Profit> profitTable;

    @FXML
    private TableColumn<Profit, Integer> idColumn;

    @FXML
    private TableColumn<Profit, String> firmColumn;

    @FXML
    private TableColumn<Profit, String> furnitureColumn;

    @FXML
    private TableColumn<Profit, String> materialColumn;

    @FXML
    private TableColumn<Profit, Double> costsColumn;

    @FXML
    private TableColumn<Profit, Double> priceColumn;

    @FXML
    private TableColumn<Profit, Double> profitColumn;

    @FXML
    private Button addB;

    @FXML
    private Button delB;

    @FXML
    private TextField search;

    @FXML
    private Button statB;

    public void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        fillTableView();
        search.textProperty().addListener((observable, oldValue, newValue) ->
                filterList(oldValue, newValue));
    }

    public void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getProfit");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Profit> information = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 8);
            Profit profit = new Profit();
            profit.setId_profit(Integer.valueOf(infoString[0]));
            profit.setFirm(infoString[1]);
            profit.setFurniture(infoString[2]);
            profit.setMaterial(infoString[3]);
            profit.setCosts(Double.valueOf(infoString[4]));
            profit.setPrice(Double.valueOf(infoString[5]));
            profit.setProfit(Double.valueOf(infoString[6]));

            information.add(profit);
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_profit"));
        firmColumn.setCellValueFactory(new PropertyValueFactory<>("firm"));
        furnitureColumn.setCellValueFactory(new PropertyValueFactory<>("furniture"));
        materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
        costsColumn.setCellValueFactory(new PropertyValueFactory<>("costs"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        profitColumn.setCellValueFactory(new PropertyValueFactory<>("profit"));
        profitTable.setItems(information);
    }



    public void filterList(String oldValue, String newValue) {
        ObservableList<Profit> filteredList = FXCollections.observableArrayList();
        if (search == null || (newValue.length() < oldValue.length() || newValue == null)) {
            fillTableView();
        } else {
            newValue = newValue.toUpperCase();
            for (Profit profit : profitTable.getItems()) {
                String filter = profit.getFurniture();
                if (filter.toUpperCase().contains(newValue) || filter.toUpperCase().contains(newValue)) {
                    filteredList.add(profit);
                }
            }
            profitTable.setItems(filteredList);
        }
    }

    public void saveToFile() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить в файл");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));
                for (Profit profit : profitTable.getItems()) {
                    outWriter.write(profit.toString());
                    outWriter.newLine();
                }
                outWriter.close();
            } catch (IOException e) {
                AlertWindow.display("Ошибка записи в файл!");
            }
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("sample", "");
    }

    @FXML
    void stat() {
        statB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("stat", "");
    }

    @FXML
    void add() {
        addB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addProfitUser", "");
    }


}
