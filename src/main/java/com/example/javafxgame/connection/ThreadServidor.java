package com.example.javafxgame.connection;

import com.example.javafxgame.data.EstatJoc;
import com.example.javafxgame.data.Player;

import java.io.*;
import java.net.Socket;

public class ThreadServidor implements Runnable{

// com comunico el serverThread amb el server per compartir les dades dels players???????
    // Que arranca que???? el servidor va apart, només escolta.
    /*   El servidor arranca el client i el client arranca el game     ?????           */


    Socket clientSocket = null;
    BufferedReader in = null;
    PrintStream out = null;
    String msgEntrant, msgSortint;
    boolean acabat;
    EstatJoc estatJoc;
    int idPropia;


    public ThreadServidor(Socket clientSocket, EstatJoc estatJoc, int numplayersConectats) {
        idPropia=numplayersConectats;
        this.estatJoc=estatJoc;
        this.clientSocket = clientSocket;
        acabat = false;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintStream(clientSocket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Conexió establerta per al player");
        try {
            // primer missatge onb li passem el id al player
            msgSortint= String.valueOf(idPropia);
            out.println(msgSortint);
            out.flush();
            msgEntrant = in.readLine();

            while(!acabat) {
                // rep la posició del player
                msgSortint= generarResposta(msgEntrant);
                out.println(msgSortint);
                out.flush();
                msgEntrant = in.readLine();
                // tracta les dades dels diferents clients per mdificar l'objecte mapa

                // envia json mapa al client (a cada client)

            }
        }catch(IOException e){
            System.out.println(e.getLocalizedMessage());
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generarResposta(String msgEntrant) {
        // junta tots els msgEntrants dels diferents jugadors, els posa en comu i retorna el json amb les posicions

        if(msgEntrant.equals("Conectat!")){
            System.out.println(msgEntrant);
            estatJoc.getPlayers().add(new Player(idPropia,100,100, Player.Direccio.S));
        }else{
            estatJoc.actualitzaPlayer(msgEntrant);
        }



        return estatJoc.mapejar();
    }

}
