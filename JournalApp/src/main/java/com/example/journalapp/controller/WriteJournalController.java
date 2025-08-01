package com.example.journalapp.controller;

import com.example.journalapp.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class WriteJournalController {

    @FXML
    private void switchToJournalApp(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "/com/example/journalapp/view/Dashboard-view.fxml", "Dashboard");
    }


}
