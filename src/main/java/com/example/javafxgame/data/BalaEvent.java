package com.example.javafxgame.data;

public class BalaEvent {

    private long id;
    private Player.Direccio dir;
    private float posX, posY;

    public BalaEvent(long id,Player.Direccio dir, float x, float y) {
        this.dir=dir;
        this.posX=x;
        this.posY=y;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Player.Direccio getDir() {
        return dir;
    }

    public void setDir(Player.Direccio dir) {
        this.dir = dir;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public BalaEvent() {
    }
}
