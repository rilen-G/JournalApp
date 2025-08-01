package com.example.journalapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.example.journalapp.util.SceneManager;

import java.io.IOException;

public class LoginController {

    @FXML
    private void switchToSignUp(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "/com/example/journalapp/view/Signup-view.fxml", "Sign Up");
    }

    @FXML
    private void switchToJournalApp(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "/com/example/journalapp/view/Dashboard-view.fxml", "Dashboard");
    }
}
