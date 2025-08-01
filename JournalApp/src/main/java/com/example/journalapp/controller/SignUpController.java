package com.example.journalapp.controller;

import com.example.journalapp.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController extends BaseController {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private TextField confirmPasswordField;
    @FXML private Button signUpButton;

    @FXML private void onSignUp(ActionEvent event) {
        switchScene("Signup-view.fxml", (Stage) usernameField.getScene().getWindow(), "Signup");
    }
    @FXML private void onLogin(ActionEvent event) {
        switchScene("Login-view.fxml", (Stage) usernameField.getScene().getWindow(), "Login" );
    }
}
