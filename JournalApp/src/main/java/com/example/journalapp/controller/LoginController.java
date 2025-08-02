package com.example.journalapp.controller;

import com.example.journalapp.model.User;
import com.example.journalapp.service.AuthService;
import com.example.journalapp.util.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginController extends BaseController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

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
    public void onLogin(ActionEvent event) {
        try {
            User user = authService.login(
                    usernameField.getText().trim(),
                    passwordField.getText()
            );
            Session.setCurrentUser(user);
            switchScene("Dashboard-view.fxml",
                    (Stage) usernameField.getScene().getWindow(),
                    "Dashboard");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onGoSignUp(ActionEvent event) {
        switchScene("Signup-view.fxml",
                (Stage) usernameField.getScene().getWindow(),
                "Sign Up");
    }
}
