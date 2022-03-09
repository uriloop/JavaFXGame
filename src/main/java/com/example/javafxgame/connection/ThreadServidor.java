package com.example.javafxgame.connection;

import com.example.javafxgame.data.EstatJoc;
import com.example.javafxgame.data.Player;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ThreadServidor implements Runnable {

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
    String nick;


    public ThreadServidor(Socket clientSocket, EstatJoc estatJoc, int numplayersConectats) {
        idPropia = numplayersConectats;
        this.estatJoc = estatJoc;
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
        System.out.println("Conexió establerta");
        try {
            // primer missatge on li passem el num de player per determinar la posició inicial i el color
            msgSortint = String.valueOf(idPropia);
            out.println(msgSortint);
            out.flush();

            // arriba = "Conectat!NickName"
            msgEntrant = in.readLine();

            if ((msgEntrant.substring(0, 9)).equals("Conectat!")) {
// Comprova si és el primer missatge de benvinguda i guarda el nickname

                if (msgEntrant.length() > 9) {
                    nick = msgEntrant.substring(9, msgEntrant.length());
                } else {
                    nick = "Player" + (idPropia + 1);
                }
                if (idPropia == 0)
                    estatJoc.getPlayers().add(new Player(nick, 100, 100, Player.Direccio.S));
                else
                    estatJoc.getPlayers().add(new Player(nick, 100, 600, Player.Direccio.S));

                msgSortint = "Has entrat a una partida "+nick+" Esperant a l'altre jugador..." ;
                out.println(msgSortint);
                out.flush();
                msgEntrant = in.readLine();
            }

            while (!acabat) {

                msgSortint = generarResposta(msgEntrant);
                out.println(msgSortint);
                out.flush();
                msgEntrant = in.readLine();
                // bucle de missatges
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // juntar tots els msgEntrants dels diferents jugadors, posar en comu i retornar el json amb les posicions
    private String generarResposta(String msgEntrant) {


        estatJoc.actualitzaJoc(idPropia, msgEntrant);

        // per monitoritzar el que passa al servidor
        System.out.println("Rebut de jug_" + nick + ": " + msgEntrant);

        return /*estatJoc.mapejar()*//*de moment és un xat */new Scanner(System.in).nextLine();
    }
}
