package com.project.simplegame.view.Cliente;

import com.project.simplegame.view.GameView;
import java.io.BufferedReader;
import java.net.Socket;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameClient implements Runnable {

    private int ServerPort;
    private String ServerIP;

    private Socket socketCliente;
    private PrintWriter out;
    //private BufferedReader in;
    private Scanner in;

    private boolean Desconectar = false;

    private GameView gameView;

    public GameClient(String ServerIP, int ServerPort, GameView gameView) {
        this.ServerIP = ServerIP;
        this.ServerPort = ServerPort;
        this.gameView = gameView;
    }

    public void Conectar() {
        try {
            socketCliente = new Socket(ServerIP, ServerPort);

            out = new PrintWriter(socketCliente.getOutputStream(), true);
            //in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            in = new Scanner(socketCliente.getInputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendDirection(String message) {
        if (out != null) {
            out.println(message);
        } else {
            System.out.println("No hay mensaje.");
        }
    }

    @Override
    public void run() {
        //try {
//            String serverMessage;
//            
//            if(in!=null){
//                while(!Desconectar){
//                if((serverMessage=in.readLine()) != null){
//                    gameView.actualizarMapa(serverMessage);
//                    }
//                }
//                socketCliente.close();
//                
//            }
//        while (in == null) {
//
//        }


        while (!Desconectar) {
            StringBuilder serverMessage = new StringBuilder();
            
            while (true) {
                String linea = in.nextLine();
                if (linea.isEmpty()) {
                    break;
                }
                //System.out.println("linea: " + linea);
                serverMessage.append(linea + "\n");
                //System.out.println(serverMessage);
            }

            gameView.actualizarMapa(serverMessage.toString());

        }

        //} 
//        catch (IOException e) {
//            System.out.println(e.getMessage());
//        }   
    }

}
