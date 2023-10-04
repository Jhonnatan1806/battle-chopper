package com.project.simplegame.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket implements Runnable {
    private String serverAddress;
    private int serverPort;
    private Socket socket;
    private Scanner serverScanner;
    private PrintWriter serverWriter;
    private ClientController clientController;
    private boolean disconnectRequested = false;

    public ClientSocket(String serverAddress, int serverPort, ClientController clientController) {
        this.clientController = clientController;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connect() throws IOException {
        socket = new Socket(serverAddress, serverPort);
        serverScanner = new Scanner(socket.getInputStream());
        serverWriter = new PrintWriter(socket.getOutputStream(), true);
    }

    public void disconnect() {
        disconnectRequested = true;
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            connect();

            new Thread(() -> {
                while (!disconnectRequested) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (clientController.isMoveRequested()) {
                        System.out.println("Sending direction: " + clientController.getDirection());
                        serverWriter.println(clientController.getDirection());
                        clientController.setMoveRequested(false);
                    }
                }

            }).start();

            while (!disconnectRequested) {
                if (serverScanner.hasNextLine()) {
                    String serverMessage = serverScanner.nextLine();
                    clientController.sendMap(serverMessage);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }
}