package com.example.journalapp;

import com.example.journalapp.util.DatabaseManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class JournalApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JournalApplication.class.getResource("view/Login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("styles/styles.css").toExternalForm());
        stage.setTitle("JournalApp - Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            DatabaseManager.initializeDatabase();
        } catch (SQLException e) {
            System.err.println("Failed to initialize database: " + e.getMessage());
            e.printStackTrace();
            Platform.exit();
            return;
        }
        launch(args);
    }
}