package com.example.javafxgame;

import com.example.javafxgame.client.Sprite;
import com.example.javafxgame.data.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class TheGameMain extends Application {

    private double cicles = 0;

    float viewPortX = 1200;
    float viewPortY = 800;

    private Pane root = new Pane();
    private int margeJugadors = 25;
    private boolean carrega;

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
        sprites().stream()
                .filter(sprite -> sprite.getType().equals("atac"))
                .forEach(atac -> {
                    if(atac.getBoundsInParent().intersects(player.getBoundsInParent())){
                        player.setDead(true);

                    }
                });

        // eliminar atacs que han sortit del joc per cada costat de la pantalla :  només els poso a DEAD=true;

        sprites().stream()
                .filter(s -> !s.getType().equals("player"))
                .filter(s -> s.getTranslateY() + s.getHeight() < 0 || s.getTranslateY() - s.getHeight() > viewPortY
                        || s.getTranslateX() + s.getWidth() < 0 || s.getTranslateX() - s.getWidth() < viewPortX)
                .forEach(s -> s.setDead(true));

        // Esborrar els Sprites que estan DEAD=true


        cicles++;

    }

    private Sprite player = new Sprite("player", Color.BLUE, 150, 100, 60, 90, Player.Direccio.S, 20);

    private List<Sprite> sprites() {
        return root.getChildren().stream().map(n -> (Sprite) n).collect(Collectors.toList());
    }


    double ciclesDispar;

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(createContent());

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A -> {
                    if (player.getDireccio() != Player.Direccio.W) {
                        player.setDireccio(Player.Direccio.W);
                    } else {
                        if (player.getTranslateX() - margeJugadors > 0)
                            player.moveLeft();
                    }

                }
                case D -> {
                    if (player.getDireccio() != Player.Direccio.E) {
                        player.setDireccio(Player.Direccio.E);
                    } else {
                        if (player.getTranslateX() + player.getWidth() + margeJugadors < viewPortX)

                            player.moveRight();
                    }
                }
                case W -> {
                    if (player.getDireccio() != Player.Direccio.N) {
                        player.setDireccio(Player.Direccio.N);
                    } else {
                        if (player.getTranslateY() - (float) margeJugadors * 0.8 > 0)
                            player.moveUp();
                    }
                }
                case S -> {
                    if (player.getDireccio() != Player.Direccio.S) {
                        player.setDireccio(Player.Direccio.S);
                    } else {
                        if (player.getTranslateY() + player.getHeight() + margeJugadors < viewPortY)
                            player.moveDown();

                    }
                }
                case COMMA -> {
                    if (cicles - ciclesDispar > 150 /*&& carrega*/) {
                        /*carrega=false;*/
                        ciclesDispar = cicles;
                        Sprite s = player.atacar(player);
                        root.getChildren().add(s);
                    }/*else if(!carrega){
                        player.carregar(player);
                        carrega=true;
                    }*/
                }
            }
        });


        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}
