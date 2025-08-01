package com.example.journalapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PastEntriesController extends BaseController  {

    @FXML private Button backButton;
    @FXML private TextField searchField;
    @FXML private ListView journalListView;
    @FXML private Label entryDateLabel;
    @FXML private TextArea entryContentArea;
    @FXML private Button editButton;
    @FXML private Button deleteButton;

    @FXML public void onBack(ActionEvent event) {
        switchScene("Dashboard-view.fxml", (Stage) searchField.getScene().getWindow(), "Dashboard");
    }
}
