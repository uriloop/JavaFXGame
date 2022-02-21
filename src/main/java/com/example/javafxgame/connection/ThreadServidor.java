package com.example.javafxgame.connection;

import java.io.*;
import java.net.Socket;

public class ThreadServidor implements Runnable{




    Socket clientSocket = null;
    BufferedReader in = null;
    PrintStream out = null;
    String msgEntrant, msgSortint;
    boolean acabat;


    public ThreadServidor(Socket clientSocket) {
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
        try {
            while(!acabat) {
                // rep la posició del player

                // tracta les dades dels diferents clients per mdificar l'objecte mapa

                // envia l'objecte mapa al client (a cada client)

            }
        }catch(IOException e){
            System.out.println(e.getLocalizedMessage());
        }
        System.out.println("rebudes dades del client");
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
