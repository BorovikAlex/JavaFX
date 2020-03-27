package client.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import client.sceneLoaders.SceneLoaderInstance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminMenuController {

    @FXML
    private Button backButton;

    @FXML
    private Button admin;

    @FXML
    private Button user;

    @FXML
    private Button furniture;

    @FXML
    private Button materials;

    @FXML
    private Button firm;

    @FXML
    private Button profit;

    @FXML
    void userB() {
        user.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("user", "");
    }

    @FXML
    void adminB() {
        admin.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("admin", "");
    }

    @FXML
    void furnitureB() {
        furniture.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("furniture", "");
    }

    @FXML
    void materialsB() {
        materials.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("material", "");
    }

    @FXML
    void firmB() {
        firm.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("firm", "");
    }

    @FXML
    void profitB() {
        profit.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("profit", "");
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("sample", "");
    }
}
