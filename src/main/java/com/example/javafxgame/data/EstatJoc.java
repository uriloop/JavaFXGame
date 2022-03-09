package com.example.javafxgame.data;

import java.util.ArrayList;
import java.util.List;

/**
 *  Classe que instancia totes les variables del joc. Basicament actualitza els estats dels jugadors i s'encarrega dels mapejos
 */
public class EstatJoc{

    Missatge missatge;

    boolean comenca=false;
    List<Player> players;

    List<BalaEvent> balesDisparades= new ArrayList<>();


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
    public void actualitzaPlayers(String msgEntrant) {
        // mapejar el json a player i a traves de la id igualar aquest player al player pertinent


    }

    /**
     * @return retorna un Json amb tot l'estat del joc per enviar-lo als clients
     */
    public String mapejar() {
        // mapejar l'estat del joc a string json i retornar

        return "json de l'estat del joc";

    }

    public boolean getComenca() {
        return comenca;
    }

    public void actualitzaJoc(int idPropia, String msgEntrant) {
       // players.get(idPropia); // ja tenim el player a qui pertany y el json







        /*if (idPropia==0){
            // mapejar el json a que? no em serveix el player (xq rebo més coses) no em serveix el estat de joc o si?
            json.getPlayerInfo(0,msgEntrant,players.get(1));

        }
        else {
            json.getPlayerInfo(1, msgEntrant, players.get(0));

        }*/

    }
}
