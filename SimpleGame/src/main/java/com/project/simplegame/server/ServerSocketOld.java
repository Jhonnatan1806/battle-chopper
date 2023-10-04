package com.project.simplegame.server;

import com.project.simplegame.model.Direction;
import com.project.simplegame.model.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSocketOld {
    private java.net.ServerSocket serverSocket;
    private char[][] gameMap;
    private ArrayList<Player> players;

    public ServerSocketOld(char[][] gameMap) {
        this.gameMap = gameMap;
        this.players = new ArrayList<>();
    }

    public void start(int port) {
        try {
            serverSocket = new java.net.ServerSocket(port);
            System.out.println("Servidor esperando conexiones en el puerto " + port);
            int index = 0;
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente " + index  +" conectado desde " + clientSocket.getInetAddress());
                players.add(new Player(8, 20, Direction.RIGHT, String.valueOf(index)));
                index++;

                // Envía el mapa al cliente
                ObjectOutputStream outMap = new ObjectOutputStream(clientSocket.getOutputStream());
                outMap.writeObject(gameMap);
                outMap.flush();

                // Envía el player al cliente
                ObjectOutputStream outPlayer = new ObjectOutputStream(clientSocket.getOutputStream());
                outPlayer.writeObject(players.get(players.size() - 1));

                // No cierres la conexión aquí para mantenerla abierta
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
                // Lee el mensaje del cliente
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String message = reader.readLine();
                System.out.println("Mensaje del cliente: " + message);

                // Lee el player del cliente
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                Player player = (Player) objectInputStream.readObject();
                System.out.println("Objeto Player recibido: " + player.getName());*/

}