package com.example.journalapp.controller;

import com.example.journalapp.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController extends BaseController {

    @FXML private Button logoutButton;
    @FXML private Button createEntryButton;
    @FXML private Button pastEntriesButton;
    @FXML private Label streakLabel;

    @FXML private void onLogin(ActionEvent event) {
        switchScene("Login-view.fxml", (Stage) streakLabel.getScene().getWindow(),"Login" );
    }

    @FXML private void onWriteEntry(ActionEvent event) {
        switchScene("WriteEntry-view.fxml", (Stage) streakLabel.getScene().getWindow(),"Write Entry");
    }

    @FXML private void onPastEntries(ActionEvent event) {
        switchScene("Past-Entries-view.fxml", (Stage) streakLabel.getScene().getWindow(),"Past Entries");
    }

}
