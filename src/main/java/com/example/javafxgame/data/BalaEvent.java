package com.example.javafxgame.data;

public class BalaEvent {

    long id;
    Player.Direccio dir;
    float posX, posY;

    public BalaEvent(long id,Player.Direccio dir, float x, float y) {
        this.dir=dir;
        this.posX=x;
        this.posY=y;
    }
}
