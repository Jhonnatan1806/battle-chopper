package com.project.battlechopper.server;

import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer();
        new Thread(server).start();
    }
}
