package com.example.javafxgame.data;

public class Player {

    private float posY, posX;
    private String id;
    private BalaEvent bala;

    public Player() {
    }

    private Direccio direccio;

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setDireccio(Direccio direccio) {
        this.direccio = direccio;
    }

    public enum Direccio {
        N, S, E, W;
    }

    public void dispara(long id,Direccio dir, float x, float y) {  // Els id, aniran de 10 en 10 i sumarem 1 o 0 depenent del player així, si acaba en 1 pertany al segon player, else ...
        id=id+Integer.parseInt(this.id);
        bala = new BalaEvent(id,dir, x, y);
    }

    public void eliminarBala() {
        bala = null;
    }


    public Player(String id, float posY, float posX, Direccio direccio) {
        this.id = id;
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
