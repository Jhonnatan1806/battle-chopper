package com.project.battlechopper.server;

import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        new Thread(server).start();
    }
}
