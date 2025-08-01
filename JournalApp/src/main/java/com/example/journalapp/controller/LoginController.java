package com.example.journalapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends BaseController {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Button loginButton;

    @FXML
    public void onLogin(ActionEvent event) {
        switchScene("Dashboard-view.fxml", (Stage) usernameField.getScene().getWindow(), "Dashboard");
    }

    @FXML
    public void onGoSignUp(ActionEvent event) {
        switchScene("Signup-view.fxml", (Stage) usernameField.getScene().getWindow(), "Signup");
    }
}
