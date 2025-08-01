package com.example.journalapp.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public abstract class BaseController {
    protected void showError(String msg) { Alert a = new Alert(Alert.AlertType.ERROR, msg); a.setHeaderText(null); a.showAndWait(); }
    protected void switchScene(String fxml, Stage stage, String title) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/journalapp/view/" + fxml)));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/journalapp/styles/styles.css")).toExternalForm());
            stage.setTitle("JournalApp - " + title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) { showError("Cannot load view " + fxml); }
    }
}
