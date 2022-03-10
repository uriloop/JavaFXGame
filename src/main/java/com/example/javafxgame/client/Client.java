package com.example.javafxgame.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.example.javafxgame.data.JsonClass;
import com.example.javafxgame.data.EstatJoc;
import com.example.javafxgame.data.Player;

public class Client extends Thread {

    String hostname;
    int port;
    boolean continueConnected;
    Player player;
    EstatJoc estatJoc;
    String nick;
    private int id;
    JsonClass json;

    public EstatJoc getEstatJoc() {
        return estatJoc;
    }

    public Client(String hostname, int port,String nick) {
        this.hostname = hostname;
        this.port = port;
        continueConnected = true;
        estatJoc= new EstatJoc();
        this.estatJoc.getPlayers().add(new Player());
        this.estatJoc.getPlayers().add(new Player());

        this.nick=nick;


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
            System.out.println("i. "+serverData);

            if (serverData.equals("0")){
                this.id=0;
                player= new Player(nick, 100,100, Player.Direccio.S);
                System.out.println("Conexió establerta amb el servidor. Ets el player 1");
            }else if (serverData.equals("1")){
                this.id=1;
                player= new Player(nick, 100,600, Player.Direccio.S);
                System.out.println("Conexió establerta amb el servidor. Ets el player 2");
            }else{
                System.out.println("T'has deixat una opció possible sense gestionar!!  Client.class");
            }
            // enviem el nick
            request="Conectat!"+nick;
            out.println(request);
            out.flush();
            System.out.println("o. "+request);

            // revem segon missatge d'espera
            serverData= in.readLine();
            System.out.println("i. "+serverData);


            // confirmem que esperem
            request= "Espero...";
            out.println(request);
            out.flush();
            System.out.println("o. "+request);


            // mentre no ens envia l'estat del joc amb el nou client, esperem
            while((serverData= in.readLine()).equals("Espera...")) {
                System.out.println("i. "+serverData);
                out.println(request);
                out.flush();
                System.out.println("o. "+request);
            }
            //processament de les dades rebudes aki rebem el primer json, hem de posicionar els clients

            request = getRequest(serverData);

            out.println(request);
            out.flush();



            // comencem a enviar json de l'estat del joc quan hi hagi dos players i iniciem joc
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

        JsonClass json= new JsonClass();
        estatJoc.actualitzaClient(id,recivedDataFromServer);
        String resposta=json.getJSON(estatJoc);
        // monitoritzar el que rebem del servidor
        System.out.println("i.  "+recivedDataFromServer);
        /*String responseFromClientToServer;*/
        /*try {
           estatJoc = new ObjectMapper().readValue(recivedDataFromServer, EstatJoc.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/

        System.out.println("o. "+resposta);
        return  new Scanner(System.in).nextLine();

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
