package com.example.journalapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController extends BaseController {

    @FXML private Button logoutButton;
    @FXML private Button createEntryButton;
    @FXML private Button pastEntriesButton;
    @FXML private Label streakLabel;

    @FXML public void onLogin(ActionEvent event) {
        switchScene("Login-view.fxml", (Stage) streakLabel.getScene().getWindow(),"Login" );
    }

    @FXML public void onWriteEntry(ActionEvent event) {
        switchScene("WriteEntry-view.fxml", (Stage) streakLabel.getScene().getWindow(),"Write Entry");
    }

    @FXML public void onPastEntries(ActionEvent event) {
        switchScene("PastEntries-view.fxml", (Stage) streakLabel.getScene().getWindow(),"Past Entries");
    }

}
