package com.project.simplegame.server;

import com.project.simplegame.model.Stage;

public class Server {
    public static void main(String[] args) {
       /*Stage stage = new Stage("resources/mapa.txt");
        ServerSocket server = new ServerSocket(stage.getMapa());
        server.start(12345);*/
        GameServer server = new GameServer();
        new Thread(server).start();
    }
}
