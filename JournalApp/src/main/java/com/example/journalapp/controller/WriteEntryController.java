package com.example.journalapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WriteEntryController extends BaseController {

    @FXML private Button backButton;
    @FXML private TextField titleField;
    @FXML private TextArea contentArea;
    @FXML private Button saveButton;

    @FXML public void onDashboard(ActionEvent event) {
        switchScene("Dashboard-view.fxml", (Stage) titleField.getScene().getWindow(), "Dashboard");
    }

    @FXML public void onCancel(ActionEvent event) {
        switchScene("Dashboard-view.fxml", (Stage) titleField.getScene().getWindow(), "Dashboard");
    }


}
