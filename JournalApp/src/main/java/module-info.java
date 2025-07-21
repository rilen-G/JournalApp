module com.example.journalapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.journalapp to javafx.fxml;
    exports com.example.journalapp;
}