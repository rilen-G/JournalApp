package com.example.journalapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController extends BaseController {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private TextField confirmPasswordField;
    @FXML private Button signUpButton;

    @FXML public void onSignUp(ActionEvent event) {
        switchScene("Login-view.fxml", (Stage) usernameField.getScene().getWindow(), "Login");
    }

    @FXML public void onLogin(ActionEvent event) {
        switchScene("Login-view.fxml", (Stage) usernameField.getScene().getWindow(), "Login" );
    }
}
