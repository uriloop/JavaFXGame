package com.example.javafxgame.connection;

import com.example.javafxgame.data.EstatJoc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servidor {

    int maxPlayers = 4;
    EstatJoc estatJoc;
    int port;
    // un map per tenir els players en una llista numerada
    List<ThreadServidor> playersConectats;
    List<Thread> clients;
    int numplayersConectats = 0;

    public Servidor(int port) {
        this.port = port;
        playersConectats = new ArrayList<>();
        clients = new ArrayList<>();
        estatJoc = new EstatJoc();
    }

    public void listen() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            while (numplayersConectats < maxPlayers) { //esperar connexió del client i llançar thread  // si hi ha 3 clients deixa d'esperar conexions
                clientSocket = serverSocket.accept();
                //Llançar Thread per establir la comunicació
                playersConectats.add(new ThreadServidor(clientSocket, estatJoc,numplayersConectats));
                clients.add(new Thread(playersConectats.get(numplayersConectats)));
                clients.get(numplayersConectats).start();
                numplayersConectats++;
                if (numplayersConectats == maxPlayers - 1) estatJoc.setComenca(true);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Servidor srv = new Servidor(5555);
        srv.listen();

    }

}
