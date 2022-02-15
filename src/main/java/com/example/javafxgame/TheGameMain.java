package com.example.javafxgame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TheGameMain extends Application {

    float viewPortX=1200;
    float viewPortY=800;


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("JavaFX Game");
        stage.setHeight(viewPortY);
        stage.setWidth(viewPortX);
        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );

        Canvas canvas = new Canvas( viewPortX, viewPortY );
        root.getChildren().add( canvas );


        Image sun   = new Image( "aburrido.png", 100,100,true,true );
        Image space = new Image("spaceBack.png",viewPortX,viewPortY,false,true);
        Image earth = new Image( "flapbird.png" );

        final long startNanoTime = System.nanoTime();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                double x = 232 + viewPortX/2 * Math.cos(t);
                double y = 232 + viewPortY/2 * Math.sin(t);

                // background image clears canvas
                gc.drawImage( space, 0, 0 );
                gc.drawImage( earth, x, y );
                gc.drawImage( sun,viewPortX/2-sun.getWidth()/2, viewPortY/2-sun.getHeight()/2 );
            }
        }.start();

        gc.setFill( Color.RED );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(2);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
        gc.setFont( theFont );
        gc.fillText( "Hello, World!", viewPortX/2-130, 50 );
        gc.strokeText( "Hello, World!", viewPortX/2-130, 50 );


/*
        gc.drawImage( earth, viewPortX/2-earth.getWidth()/2, viewPortY/2-earth.getHeight()/2 );
*/

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
