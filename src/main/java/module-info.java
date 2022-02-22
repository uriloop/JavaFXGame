module com.example.javafxgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.example.javafxgame to javafx.fxml;
    exports com.example.javafxgame;
    exports com.example.javafxgame.connection;
    exports com.example.javafxgame.controllers;
    exports com.example.javafxgame.client;
    exports com.example.javafxgame.data;
    opens com.example.javafxgame.data to javafx.fxml;
}