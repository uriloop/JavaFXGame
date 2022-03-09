package com.example.javafxgame;

import com.example.javafxgame.client.Client;
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

    private Parent root;
    private Stage stage;
    private Scene scene;

    private String nick="";

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


        Client client= new Client("localhost",5555,nick);

        client.run();

       TheGameMain game= new TheGameMain(client);
       int i=0;
       while (!client.getEstatJoc().getComenca()){
           if (i%100==0)System.out.print("-");
           i++;
       }

        try {
            game.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }



        /*FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("menu.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage) startButton.getScene().getWindow();
        scene = new Scene(root, 800, 550);
        scene.getStylesheets().add(GameMain.class.getResource(GameMain.theme).toExternalForm());
        stage.setScene(scene);
        stage.show();*/
        /*
        ///////  Aixó pel lobby, de moment tiro milles a lo bàsic

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
        stage.show();*/
    }
}
