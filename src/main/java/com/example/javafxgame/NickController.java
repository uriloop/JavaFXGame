package com.example.javafxgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NickController implements Initializable {

    User user;
    private Parent root;
    private Stage stage;
    private Scene scene;

    String nick="";

    @FXML
    TextField nomField;

    @FXML
    Button startButton;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void startMenuSala() {
       nick=nomField.getText();

       user= new User(nick,0,0);

        FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("menu.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage) startButton.getScene().getWindow();
        scene = new Scene(root, 800, 550);
        scene.getStylesheets().add(GameMain.class.getResource(GameMain.theme).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
