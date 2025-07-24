module com.example.journalapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.journalapp to javafx.fxml, javafx.graphics;
    opens com.example.journalapp.controller to javafx.fxml;

    exports com.example.journalapp;
    exports com.example.journalapp.controller to javafx.fxml;
}
