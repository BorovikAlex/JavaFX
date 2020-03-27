package client.controllers;

import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SampleController {

    @FXML
    private Button adminButton;

    @FXML
    private Button userButton;

    public void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
    }

    @FXML
    void userButt() {
        userButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("signIn", "");

    }

    @FXML
    void adminButt() {
        adminButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminSignIn", "");

    }
}