package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrController {

    @FXML
    private TextField userName;

    @FXML
    private TextField userSurname;

    @FXML
    private TextField userLogin;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Button backButton;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userPhone;

    public void registration() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (userName.getText().equals("") || userSurname.getText().equals("") || userLogin.getText().equals("")
                || userPassword.getText().equals("") || userEmail.getText().equals("")|| userPhone.getText().equals("")) {
            AlertWindow.display("Введите все данные!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("addclient " + userName.getText() + " " + userSurname.getText() + " " +
                userLogin.getText() + " " + userPassword.getText() + " " + userEmail.getText()+ " " + userPhone.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Клиент добавлен успешно!");
            userName.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("signIn", "");
        } else {
            AlertWindow.display("Ошибка добавления!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("signIn", "");
    }
}
