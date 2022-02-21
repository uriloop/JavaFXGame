module com.example.javafxgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxgame to javafx.fxml;
    exports com.example.javafxgame;
    exports com.example.javafxgame.objectes;
    opens com.example.javafxgame.objectes to javafx.fxml;
}