package com.example.javafxgame.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.example.javafxgame.data.EstatJoc;
import com.example.javafxgame.data.Player;

public class Client {

    String hostname;
    int port;
    boolean continueConnected;
    Player player;
    EstatJoc estatJoc;

    public EstatJoc getEstatJoc() {
        return estatJoc;
    }

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        continueConnected = true;
        estatJoc= new EstatJoc();

/*
 game=new TheGameMain();
try {
            game.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    */
    }

    public Player getPlayer() {
        return player;
    }

    public void run() {

        String serverData;
        String request;


        Socket socket;
        BufferedReader in;
        PrintStream out;
        try {
            socket = new Socket(InetAddress.getByName(hostname), port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());

            // Tractem el primer missatge on rebem la id del player i el creem
            serverData = in.readLine();
            //processament de les dades rebudes i obtenció d'una nova petició
            player= new Player(Integer.parseInt(serverData),100,100, Player.Direccio.S);
            System.out.println("Conexió establerta amb el servidor. Ets el player "+serverData);
            request="Conectat!";
            out.println(request);
            out.flush();

            //el client atén el port fins que decideix finalitzar
            while (continueConnected) {
                serverData = in.readLine();
                //processament de les dades rebudes i obtenció d'una nova petició
                request = getRequest(serverData);
                //enviament el número i els intents
                out.println(request);
                out.flush();
            }
            close(socket);
        } catch (UnknownHostException ex) {
            System.out.println("Error de connexió. No existeix el host: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error de connexió indefinit: " + ex.getMessage());
        }

    }

    // Tractem la rebuda de dades

    public String getRequest(String recivedDataFromServer) {

        String responseFromClientToServer;
        /*try {
           estatJoc = new ObjectMapper().readValue(recivedDataFromServer, EstatJoc.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
        return "aki retorna un json del player cap al servidor";

        /*try {
            responseFromClientToServer = new ObjectMapper().writeValueAsString(playerClient);
            return responseFromClientToServer;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
*/
        /*if( serverData.equals("Correcte") ) {
            continueConnected = false;
            ret = "Campió!";
        } else {
            Scanner in = new Scanner(System.in);
            System.out.print("Digues un número: ");
            ret = new String(in.next());
            intents++;
        }
        return null;*/

    }

    public boolean mustFinish(String dades) {
        if (dades.equals("exit")) return false;
        return true;

    }

    private void close(Socket socket) {
        //si falla el tancament no podem fer gaire cosa, només enregistrar
        //el problema
        try {
            //tancament de tots els recursos
            if (socket != null && !socket.isClosed()) {
                if (!socket.isInputShutdown()) {
                    socket.shutdownInput();
                }
                if (!socket.isOutputShutdown()) {
                    socket.shutdownOutput();
                }
                socket.close();
            }
        } catch (IOException ex) {
            //enregistrem l'error amb un objecte Logger
            ex.printStackTrace();
        }
    }

  /*  public static void main(String[] args) {
        Client clientTcp = new Client("localhost", 5555);
        clientTcp.run();
		*//*if (args.length != 2) {
            System.err.println(
                "Usage: java ClientTcpAdivina <host name> <port number>");
            System.exit(1);
        }*//*

        // String hostName = args[0];
        // int portNumber = Integer.parseInt(args[1]);
    }*/
}
