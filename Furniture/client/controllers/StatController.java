package client.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

public class StatController {

    @FXML
    private Button backButton;

    @FXML
    private PieChart pieChart;

    public void initialize() {
        ClientInstance.INSTANCE.getInstance().send("getStat");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList(
                new PieChart.Data("стол", Double.valueOf(list.get(0))),
                new PieChart.Data("стул", Double.valueOf(list.get(1))),
                new PieChart.Data("кровать", Double.valueOf(list.get(2))),
                new PieChart.Data("диван", Double.valueOf(list.get(3))),
                new PieChart.Data("шкаф", Double.valueOf(list.get(4))),
                new PieChart.Data("кресло", Double.valueOf(list.get(5)))
        );
        pieChart.setData(pieChartList);

    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("profitUser", "");
    }
}
