package com.example.journalapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.example.journalapp.util.SceneManager;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends BaseController {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Button loginButton;

    @FXML
    private void onLogin(ActionEvent event) {
    }

    @FXML
    private void onGoSignUp(ActionEvent event) {
        switchScene("Signup-view.fxml", (Stage) usernameField.getScene().getWindow(), "Signup");
    }
}
