package com.project.simplegame.server;

import java.io.IOException;

public class ServerStarter {

    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer();
        new Thread(server).start();
    }
}
