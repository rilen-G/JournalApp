package com.example.journalapp.controller;

import com.example.journalapp.model.User;
import com.example.journalapp.service.JournalService;
import com.example.journalapp.util.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DashboardController extends BaseController {
    @FXML private Label streakLabel;
    @FXML private Button logoutButton;
    @FXML private Button createEntryButton;
    @FXML private Button pastEntriesButton;

    private JournalService journalService;

    @FXML
    public void initialize() {
        try {
            journalService = new JournalService();
            User user = Session.getCurrentUser();
            int streak = journalService.calculateStreak(user);
            streakLabel.setText("🔥 " + streak);
        } catch (SQLException e) {
            showError("Error loading streak: " + e.getMessage());
        }
    }

    @FXML
    public void onLogout(ActionEvent event) {
        Session.clear();
        switchScene("Login-view.fxml", (Stage) logoutButton.getScene().getWindow(), "Login");
    }

    @FXML
    public void onWriteEntry(ActionEvent event) {
        switchScene("WriteEntry-view.fxml", (Stage) createEntryButton.getScene().getWindow(), "New Entry");
    }

    @FXML
    public void onPastEntries(ActionEvent event) {
        switchScene("PastEntries-view.fxml", (Stage) pastEntriesButton.getScene().getWindow(), "Past Entries");
    }
}
