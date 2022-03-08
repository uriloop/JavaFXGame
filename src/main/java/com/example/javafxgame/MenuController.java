package com.example.javafxgame;

import com.example.javafxgame.client.Client;
import javafx.beans.value.ObservableListValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    Label nom_user_1,nom_user_2,nom_user_3, victories_user_1, victories_user_2,victories_user_3, derrotes_user_1,derrotes_user_2,derrotes_user_3;

    @FXML
    TextArea xat_box;

    @FXML
    TextField xat_text;

    @FXML
    ListView<String> llista_usuaris;


    Client client= new Client("localhost", 5555);



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        client.start();





    }
}
