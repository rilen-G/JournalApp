package com.example.journalapp.controller;

import com.example.journalapp.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class DashboardController {

    @FXML
    private void switchToLogin(ActionEvent event) throws IOException {
        SceneManager.switchScene(event,"/com/example/journalapp/fxmls/login.fxml","Login");
    }

    @FXML
    private void switchToWriteJournal(ActionEvent event) throws IOException {
        SceneManager.switchScene(event,"/com/example/journalapp/fxmls/write_journal.fxml", "New Entry");
    }

    @FXML
    private void switchToPastJournal(ActionEvent event) throws IOException {
        SceneManager.switchScene(event,"/com/example/journalapp/fxmls/past_journal.fxml", "Past Entries");
    }
}
