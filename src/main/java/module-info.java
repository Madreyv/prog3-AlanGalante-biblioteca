module com.example.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.example.biblioteca to javafx.fxml;
    exports com.example.biblioteca;
    exports gui;
    opens gui to javafx.fxml;
}