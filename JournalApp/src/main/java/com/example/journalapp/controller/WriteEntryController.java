package com.example.journalapp.controller;

import com.example.journalapp.model.JournalEntry;
import com.example.journalapp.model.User;
import com.example.journalapp.service.JournalService;
import com.example.journalapp.util.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class WriteEntryController extends BaseController {
    @FXML private Label headerLabel;
    @FXML private TextField titleField;
    @FXML private TextArea contentArea;
    @FXML private Button saveButton;
    @FXML private Button backButton;

    private JournalService journalService;
    private JournalEntry editingEntry = null;

    @FXML
    public void initialize() {
        try {
            journalService = new JournalService();
        } catch (SQLException e) {
            showError("Error initializing entry service: " + e.getMessage());
        }
    }

    public void setEditingEntry(JournalEntry entry) {
        this.editingEntry = entry;
        titleField.setText(entry.getTitle());
        contentArea.setText(entry.getContent());
        saveButton.setText("Update Entry");
        headerLabel.setText("Edit Entry");
    }

    // Called when user clicks "Save Entry"
    @FXML
    public void onDashboard(ActionEvent event) {
        String title   = titleField.getText().trim();
        String content = contentArea.getText().trim();
        if (title.isEmpty()) {
            showError("Title cannot be empty");
            return;
        }
        try {
            User user = Session.getCurrentUser();

            if (editingEntry != null) {

                editingEntry.setTitle(title);
                editingEntry.setContent(content);
                journalService.updateEntry(editingEntry);

                switchScene("PastEntries-view.fxml", (Stage) saveButton.getScene().getWindow(), "Past Entries");
            } else {
                journalService.addEntry(title, content, user);
                switchScene("Dashboard-view.fxml", (Stage) saveButton.getScene().getWindow(), "Dashboard");
            }
        } catch (SQLException e) {
            showError("Error saving entry: " + e.getMessage());
        }
    }

    // Called when user clicks "← Back"
    @FXML
    public void onCancel(ActionEvent event) {
        if (editingEntry != null) {
            switchScene("PastEntries-view.fxml", (Stage) backButton.getScene().getWindow(), "Past Entries");
        } else {
            switchScene("Dashboard-view.fxml", (Stage) backButton.getScene().getWindow(), "Dashboard");
        }
    }
}
