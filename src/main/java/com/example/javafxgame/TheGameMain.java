package com.example.javafxgame;

import com.example.javafxgame.client.Client;
import com.example.javafxgame.client.Sprite;
import com.example.javafxgame.data.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private List<String> input= new ArrayList<>();

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

    private void update() {

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
            if (sprite.isDead()){
                root.getChildren().remove(sprite);
            }
        });

        cicles++;

    }

    private Sprite player = new Sprite("player", Color.DARKOLIVEGREEN, 150, 100, 60, 90, Player.Direccio.S, 20);

    private List<Sprite> sprites() {
        return root.getChildren().stream().map(n -> (Sprite) n).collect(Collectors.toList());
    }


    double ciclesDispar;

    @Override
    public void start(Stage stage) throws Exception {
        client= new Client("localhost",5555);
        client.start();
        Scene scene = new Scene(createContent());




        // posem a escoltar diferents tecles per als inputs

        scene.setOnKeyPressed(e->{
            input.add(e.getCode().toString());
        });


       /*  ARREGLAT PER A QUE ACCEPTI VARIES TECLES SIMULTANIES


       scene.setOnKeyPressed(e -> {


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
                    if (cicles - ciclesDispar > 150 *//*&& carrega*//*) {
                        *//*carrega=false;*//*
                        ciclesDispar = cicles;
                        Sprite s = player.atacar(player);
                        root.getChildren().add(s);
                    }*//*else if(!carrega){
                        player.carregar(player);
                        carrega=true;
                    }*//*
                }
            }
        });
*/

        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {



        launch();

    }
}
