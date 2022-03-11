package com.example.javafxgame.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que instancia totes les variables del joc. Basicament actualitza els estats dels jugadors i s'encarrega dels mapejos
 */
public class EstatJoc implements Serializable {

    // private boolean comenca = false;

    private List<Player> players = new ArrayList<>();
    private List<BalaEvent> balesDisparades = new ArrayList<>();

    public EstatJoc() {
    }


    /*

        public boolean isComenca() {
            return comenca;
        }*/
    public List<BalaEvent> getBalesDisparades() {
        return balesDisparades;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setBalesDisparades(List<BalaEvent> balesDisparades) {
        this.balesDisparades = balesDisparades;
    }
///  ACTUALITZAR ESTAT DEL JOC
    // 1
    // el client envia al servidor:   estat del joc complet mapejat a json, (mapejar objecte estatJoc a json i retornar)


    // 2
    // el servidor agafa les dades del player del client i les bales i actualitza l'objecte estatJoc

    public void actualitzaServidor(int idDeQuiEnvia, String json) {


        EstatJoc estatJocRebut = new JsonClass().getObject(json);
        // akí tria de dades entre el estatjocRebut i this tenint en compte qui ha enviat en quant al player
        this.getPlayers().get(idDeQuiEnvia).setPosX(estatJocRebut.getPlayers().get(idDeQuiEnvia).getPosX());
        this.getPlayers().get(idDeQuiEnvia).setPosY(estatJocRebut.getPlayers().get(idDeQuiEnvia).getPosY());
        this.getPlayers().get(idDeQuiEnvia).setDireccio(estatJocRebut.getPlayers().get(idDeQuiEnvia).getDireccio());
        // actualitzem les dades del jugador que ens ha enviat el json.
        // comprovem la llista de bales en busca de bales noves

        // he de fer un stream o varios:   quedar-me amb les noves i generar-les al joc...   afegir-les a la llista propia de bales
    }

    // 3
    // el client rep l'estat complet del joc del servidor i el mapeja a objecte,
    // el client tria la info que vol i l'actualitza:   llista bales, posX posY i direcció del contrincant

    public void actualitzaClient(int idClient, String json) {
        //   EstatJoc estatJocRebut=json/*mapped*/;
        // ara actualitzar el estat del joc amb les dades pertinents
        int id = idClient == 0 ? 1 : 0;
        EstatJoc estatJocRebut = new JsonClass().getObject(json);

        this.getPlayers().get(id).setPosX(estatJocRebut.getPlayers().get(id).getPosX());
        this.getPlayers().get(id).setPosY(estatJocRebut.getPlayers().get(id).getPosY());
        this.getPlayers().get(id).setDireccio(estatJocRebut.getPlayers().get(id).getDireccio());
        this.getPlayers().get(id).setId(estatJocRebut.getPlayers().get(id).getId());


        // akí tria de dades entre l'estatjocRebut i el propi tenint en compte qui ha enviat en quant al player.
        // actualitzem les dades de l'altre jugador.


    }

// 4
// tots mapegen i desmapegen el mateix objecte a un objecte temporal del mateix tipus per a triar les dades que requereix cada "dispositiu" serv. i clients



  /*  public List<BalaEvent> getNewBales(){
        // per passar-li al client les bales noves. Simplement comparar amb la llista propia i retornar les que siguin noves
        return new ArrayList<BalaEvent>().get(0);  // aixó s'ha d'arreglar
    }
    */

/*
    public void setComenca(boolean b) {
        comenca = true;
    }*/

    public List<Player> getPlayers() {
        return players;
    }

    /* *//**
     * Actualitza l'estat del joc amb la info que envia el client sobre la seva posicio
     *
     * @param msgEntrant Json amb l'estat del player client
     * @return retorna un Json amb tot l'estat del joc per enviar-lo als clients
     *//*
    public void actualitzaPlayer(int idPropia,String msgEntrant) {
        // mapejar el json a player i a traves de la id igualar aquest player al player pertinent

    }

    */

    /**
     * @return retorna un Json amb tot l'estat del joc per enviar-lo als clients
     *//*
    public String mapejar() {
        // mapejar l'estat del joc a string json i retornar

        return "json de l'estat del joc";

    }
*/
/*
    public boolean getComenca() {
        return comenca;
    }
*/

  /*  public void actualitzaJoc(int idPropia, String msgEntrant) {
       // players.get(idPropia); // ja tenim el player a qui pertany y el json







        *//*if (idPropia==0){
            // mapejar el json a que? no em serveix el player (xq rebo més coses) no em serveix el estat de joc o si?
            json.getPlayerInfo(0,msgEntrant,players.get(1));

        }
        else {
            json.getPlayerInfo(1, msgEntrant, players.get(0));

        }*/
    /*

    }

    */
}
