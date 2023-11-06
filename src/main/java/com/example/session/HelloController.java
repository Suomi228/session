package com.example.session;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logInButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;


    @FXML
    void initialize() {

        logInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();
            if (!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText, loginPassword);
            }
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DButils dbManager = new DButils();
        String role = dbManager.authenticate(loginText, loginPassword);
        switch (role){
            case "student" -> {
                logInButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("educationalWorker-view.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
            case "admin" -> {
                logInButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("admin-registration.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
            case "zam_director" -> {
                logInButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("dispatcher-view.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
            default -> System.out.println("wrong login or password");
        }
    }

}