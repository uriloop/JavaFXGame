package com.example.javafxgame;

import com.example.javafxgame.client.Client;
import com.example.javafxgame.client.Sprite;
import com.example.javafxgame.data.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TheGameMain extends Application {

    private double cicles = 0;
    private Client client;

    float viewPortX = 1200;
    float viewPortY = 800;

    private Pane root = new Pane();
    private int margeJugadors = 25;
    private boolean carrega;

    private List<String> input = new ArrayList<>();

    public TheGameMain( Client client) {
        this.client=client;

    }

    private Parent createContent() {
        root.setPrefSize(viewPortX, viewPortY);
        root.getChildren().add(player);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        timer.start();

        return root;
    }

    double ciclesMov = cicles;

    private void update() {




            input.forEach(s -> {

           /*    ///   AMB CAMBI DE DIRECCIÓ ABANSDE MOURE'S


                if (s.equals("D") || s.equals("RIGHT")) {
                    if (player.getDireccio() != Player.Direccio.E) {
                        player.setDireccio(Player.Direccio.E);
                    } else {
                        if (player.getTranslateX() + player.getWidth() + margeJugadors < viewPortX)

                            player.moveRight();
                    }

                }
                if (s.equals("A") || s.equals("LEFT")) {
                    if (player.getDireccio() != Player.Direccio.W) {
                        player.setDireccio(Player.Direccio.W);
                    } else {
                        if (player.getTranslateX() - margeJugadors > 0)
                            player.moveLeft();
                    }

                }
                if (s.equals("W") || s.equals("UP")) {
                    if (player.getDireccio() != Player.Direccio.N) {
                        player.setDireccio(Player.Direccio.N);
                    } else {
                        if (player.getTranslateY() - (float) margeJugadors * 0.8 > 0)
                            player.moveUp();
                    }

                }
                if (s.equals("S") || s.equals("DOWN")) {

                    if (player.getDireccio() != Player.Direccio.S) {
                        player.setDireccio(Player.Direccio.S);
                    } else {
                        if (player.getTranslateY() + player.getHeight() + margeJugadors < viewPortY)
                            player.moveDown();

                    }

                }*/


               /////  PROVANT DIAGONALS


                List<String> dir= new ArrayList<>();


                if (s.equals("D") || s.equals("RIGHT")) {
                        player.setDireccio(Player.Direccio.E);
                        if (player.getTranslateX() + player.getWidth() + margeJugadors < viewPortX)
                           dir.add("-Dreta");

                }
                if (s.equals("A") || s.equals("LEFT")) {
                        player.setDireccio(Player.Direccio.W);
                        if (player.getTranslateX() - margeJugadors > 0)
                            dir.add("-Esquerre");

                }
                if (s.equals("W") || s.equals("UP")) {
                        player.setDireccio(Player.Direccio.N);
                        if (player.getTranslateY() - (float) margeJugadors * 0.8 > 0)
                            dir.add("-Dalt");



                }
                if (s.equals("S") || s.equals("DOWN")) {

                        player.setDireccio(Player.Direccio.S);
                        if (player.getTranslateY() + player.getHeight() + margeJugadors < viewPortY)
                            dir.add("-Baix");


                }


                StringBuilder direccioFinal=new StringBuilder();
                dir.forEach(direccioFinal::append);

                switch (direccioFinal.toString()){
                    case "-Dalt-Baix" :
                    case "-Baix-Dalt":
                    case "-Esquerre-Dreta":
                    case "Dreta-Esquerre":
                        break;
                    case "-Dalt-Dreta":
                    case "-Dreta-Dalt":
                        player.moveRightUp();
                        break;
                    case "-Dalt-Esquerre":
                    case "-Esquerre-Dalt":
                        player.moveLeftUp();
                        break;
                    case "-Baix-Esquerre":
                    case "-Esquerre-Baix":
                        player.moveLeftDown();
                        break;
                    case "-Baix-Dreta":
                    case "-Dreta-Baix":
                        player.moveRightDown();
                        break;
                    case "-Baix":
                        player.moveDown();
                        break;
                    case "-Dalt":
                        player.moveUp();
                        break;
                    case "-Esquerre":
                        player.moveLeft();
                        break;
                    case "-Dreta":
                        player.moveRight();
                        break;                }

                dir=new ArrayList<>();
                    if (s.equals("COMMA")){
                        if(cicles - ciclesDispar>150){
                            ciclesDispar=cicles;
                            Sprite sp= player.atacar(player);
                            root.getChildren().add(sp);
                        }

                    }




/*

                if (cicles - ciclesDispar > 150) {

                    if (s.equals("COMMA")) {
                        carrega = false;
                        ciclesDispar = cicles;
                        Sprite sp = player.atacar(player);
                        root.getChildren().add(sp);
                    }
                } else if (!carrega) {
                    player.carregar(player);
                    carrega = true;
                }

*/

            });
            ciclesMov = cicles;
            input= new ArrayList<>();





        sprites().stream()
                .filter(s -> s.getType().equals("atac"))
                .filter(s -> s.getDireccio() == Player.Direccio.S)
                .forEach(Sprite::moveDown);

        sprites().stream()
                .filter(s -> s.getType().equals("atac"))
                .filter(s -> s.getDireccio() == Player.Direccio.N)
                .forEach(Sprite::moveUp);
        sprites().stream()
                .filter(s -> s.getType().equals("atac"))
                .filter(s -> s.getDireccio() == Player.Direccio.W)
                .forEach(Sprite::moveLeft);
        sprites().stream()
                .filter(s -> s.getType().equals("atac"))
                .filter(s -> s.getDireccio() == Player.Direccio.E)
                .forEach(Sprite::moveRight);
       /* sprites().stream()
                .filter(sprite -> sprite.getType().equals("atac"))
                .forEach(atac -> {
                    if(atac.getBoundsInParent().intersects(player.getBoundsInParent())){
                        player.setDead(true);

                    }
                });*/

        // eliminar atacs que han sortit del joc per cada costat de la pantalla :  només els poso a DEAD=true;

        sprites().stream()
                .filter(s -> s.getType().equals("atac"))
                .filter(s -> s.getTranslateY() + s.getHeight() < 0 || s.getTranslateY() - s.getHeight() > viewPortY
                        || s.getTranslateX() + s.getWidth() < 0 || s.getTranslateX() - s.getWidth() > viewPortX)
                .forEach(s -> s.setDead(true));

        // Esborrar els Sprites que estan DEAD=true


        // intentant esborrar les bales que surten de pantalla o xoquen, o... que estan      dead = true;





        sprites().forEach(sprite -> {
            if (sprite.isDead()) {
                root.getChildren().remove(sprite);
            }
        });

        cicles++;

    }

    private Sprite player = new Sprite("player", Color.DARKOLIVEGREEN, 150, 100, 60, 90, Player.Direccio.S, 25);

    private List<Sprite> sprites() {
        return root.getChildren().stream().map(n -> (Sprite) n).collect(Collectors.toList());
    }


    double ciclesDispar;

    @Override
    public void start(Stage stage) throws Exception {

        /*Scene scene = new Scene(createContent());


        // posem a escoltar diferents tecles per als inputs

        scene.setOnKeyPressed(e -> {
            input.add(e.getCode().toString());
        });
*//*

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

            }
        });
*//*

       *//*  ARREGLAT PER A QUE ACCEPTI VARIES TECLES SIMULTANIES


       scene.setOnKeyPressed(e -> {
e.get¡¡

            switch (e.getCode()) {
                case A,LEFT -> {
                    if (player.getDireccio() != Player.Direccio.W) {
                        player.setDireccio(Player.Direccio.W);
                    } else {
                        if (player.getTranslateX() - margeJugadors > 0)
                            player.moveLeft();
                    }

                }
                case D,RIGHT -> {
                    if (player.getDireccio() != Player.Direccio.E) {
                        player.setDireccio(Player.Direccio.E);
                    } else {
                        if (player.getTranslateX() + player.getWidth() + margeJugadors < viewPortX)

                            player.moveRight();
                    }
                }
                case W,UP -> {
                    if (player.getDireccio() != Player.Direccio.N) {
                        player.setDireccio(Player.Direccio.N);
                    } else {
                        if (player.getTranslateY() - (float) margeJugadors * 0.8 > 0)
                            player.moveUp();
                    }
                }
                case S,DOWN -> {
                    if (player.getDireccio() != Player.Direccio.S) {
                        player.setDireccio(Player.Direccio.S);
                    } else {
                        if (player.getTranslateY() + player.getHeight() + margeJugadors < viewPortY)
                            player.moveDown();

                    }
                }
                case COMMA,ENTER -> {
                    if (cicles - ciclesDispar > 150 *//**//*&& carrega*//**//*) {
         *//**//*
                }
            }
        });
*//*

        stage.setScene(scene);
        stage.show();

*/
    }

    public static void main(String[] args) {


        launch();

    }
}
