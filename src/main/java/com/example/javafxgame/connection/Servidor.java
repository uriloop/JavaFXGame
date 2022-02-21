package com.example.javafxgame.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Servidor {

    int port;

    public Servidor(int port) {
        this.port = port;
    }

    public void listen(){
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            while(true) { //esperar connexió del client i llançar thread
                clientSocket = serverSocket.accept();
                //Llançar Thread per establir la comunicació
                ThreadServidor FilServidor = new ThreadServidor(clientSocket);
                Thread client = new Thread(FilServidor);
                client.start();
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


}
