package com.example.journalapp.controller;

import com.example.journalapp.model.JournalEntry;
import com.example.journalapp.service.JournalService;
import com.example.journalapp.util.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class PastEntriesController extends BaseController {
    @FXML private TextField searchField;
    @FXML private ListView<JournalEntry> journalListView;
    @FXML private Label entryTitleLabel;
    @FXML private Label entryDateLabel;
    @FXML private TextArea entryContentArea;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    @FXML private Button backButton;

    private JournalService journalService;
    private ObservableList<JournalEntry> entries;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @FXML
    public void initialize() {
        try {
            journalService = new JournalService();
            List<JournalEntry> list = journalService.getEntries(Session.getCurrentUser());
            entries = FXCollections.observableArrayList(list);
            journalListView.setItems(entries);

            journalListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, sel) -> {
                if (sel != null) showEntry(sel);
            });

            searchField.textProperty().addListener((obs, oldText, newText) -> {
                try {
                    List<JournalEntry> results = journalService.searchEntries(Session.getCurrentUser(), newText);
                    entries.setAll(results);
                } catch (SQLException e) {
                    showError("Search error: " + e.getMessage());
                }
            });
        } catch (SQLException e) {
            showError("Failed to load entries: " + e.getMessage());
        }
    }

    private void showEntry(JournalEntry entry) {
        entryTitleLabel.setText(entry.getTitle());
        entryDateLabel.setText(entry.getCreatedAt().format(formatter));
        entryContentArea.setText(entry.getContent());
    }


    @FXML
    public void onEdit(ActionEvent event) {
        JournalEntry sel = journalListView.getSelectionModel().getSelectedItem();
        if (sel == null) {
            showError("No entry selected");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/journalapp/view/WriteEntry-view.fxml")));
            Parent root = loader.load();
            WriteEntryController controller = loader.getController();
            controller.setEditingEntry(sel);
            Stage stage = (Stage) editButton.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Edit Entry");
        } catch (IOException e) {
            showError("Unable to load editor: " + e.getMessage());
        }
    }

    @FXML
    public void onDelete(ActionEvent event) {
        JournalEntry sel = journalListView.getSelectionModel().getSelectedItem();
        if (sel == null) {
            showError("No entry selected");
            return;
        }
        try {
            journalService.deleteEntry(sel.getId());
            entries.remove(sel);
            entryTitleLabel.setText("");
            entryDateLabel.setText("");
            entryContentArea.setText("");
        } catch (SQLException e) {
            showError("Delete failed: " + e.getMessage());
        }
    }

    @FXML
    public void onBack(ActionEvent event) {
        switchScene("Dashboard-view.fxml", (Stage) backButton.getScene().getWindow(), "Dashboard");
    }
}
