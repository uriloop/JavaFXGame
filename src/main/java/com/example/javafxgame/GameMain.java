package com.example.javafxgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameMain extends Application {
    public static String theme= "styles/darcula.css";
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(GameMain.class.getResource("com_et_dius.fxml"));
        Parent root= fxmlLoader.load();
        Scene scene = new Scene(root, 300, 156);
        scene.getStylesheets().add(getClass().getResource(theme).toExternalForm());
        stage.setTitle("The Game");
        stage.setScene(scene);
       // stage.getIcons().add(new Image(getResource("com/example/javafxgame/img/aburrido.png")));
        stage.show();

    }


    public static void main(String[] args) {

        launch();

    }


}
