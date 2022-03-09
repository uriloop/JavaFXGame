package com.example.javafxgame.data;

public class Player {

    float posY, posX;
    String id;


    private Direccio direccio;
    public enum Direccio{
        N,S,E,W;
    }



    public Player(String id, float posY, float posX, Direccio direccio) {
        this.id=id;
        this.posY = posY;
        this.posX = posX;
        this.direccio = direccio;
    }

    public float getPosY() {
        return posY;
    }

    public float getPosX() {
        return posX;
    }

    public Direccio getDireccio() {
        return direccio;
    }

    public String getId() {
        return id;
    }


}
