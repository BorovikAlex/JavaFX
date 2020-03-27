package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminSignInController {

    @FXML
    private TextField adminLogin;

    @FXML
    private PasswordField adminPassword;

    @FXML
    private Button backButton;
    public void signIn() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("loginAdm " + adminLogin.getText() + " " + adminPassword.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            adminPassword.getScene().getWindow().hide();
            client.send("getadmintdata " + adminLogin.getText() + " " + adminPassword.getText());
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
        } else {
            AlertWindow.display("Неверные логин или пароль!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("sample", "");
    }
}
