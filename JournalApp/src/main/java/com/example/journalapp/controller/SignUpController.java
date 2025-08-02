package com.example.journalapp.controller;

import com.example.journalapp.service.AuthService;
import com.example.journalapp.util.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class SignUpController extends BaseController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;

    private AuthService authService;

    @FXML
    public void initialize() {
        try {
            authService = new AuthService();
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
        }
    }

    @FXML
    public void onSignUp(ActionEvent event) {
        String raw = passwordField.getText();
        if (!raw.equals(confirmPasswordField.getText())) {
            showError("Passwords do not match");
            return;
        }

        try {
            authService.signup(usernameField.getText().trim(), raw);
            switchScene("Login-view.fxml",
                    (Stage) usernameField.getScene().getWindow(),
                    "Log In");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onLogin(ActionEvent event) {
        switchScene("Login-view.fxml",
                (Stage) usernameField.getScene().getWindow(),
                "Log In");
    }
}
