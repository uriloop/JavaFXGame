package com.example.javafxgame.data;

import java.util.ArrayList;
import java.util.List;

/**
 *  Classe que instancia totes les variables del joc. Basicament actualitza els estats dels jugadors i s'encarrega dels mapejos
 */
public class EstatJoc {

    boolean comenca=false;
    List<Player> players;

    public EstatJoc() {
       players=new ArrayList<>();
    }



    public void setComenca(boolean b) {
        comenca=true;
    }

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Actualitza l'estat del joc amb la info que envia el client sobre la seva posicio
     *
     * @param msgEntrant Json amb l'estat del player client
     */
    public void actualitzaPlayer(String msgEntrant) {
        // mapejar el json a player i a traves de la id igualar aquest player al player pertinent


    }

    /**
     * @return retorna un Json amb tot l'estat del joc per enviar-lo als clients
     */
    public String mapejar() {
        // mapejar l'estat del joc a string json i retornar

        return "json de l'estat del joc";

    }
}
