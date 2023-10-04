package com.project.simplegame.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket implements Runnable {
    private String serverIP;
    private int serverPort;
    private Socket socketClient;
    private Scanner serverIn;
    private PrintWriter serverOut;
    private ClientController clientController;
    private boolean disconnectRequested = false;

    public ClientSocket(String serverIP, int serverPort, ClientController clientController) {
        this.clientController = clientController;
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void connect() throws IOException {
        socketClient = new Socket(serverIP, serverPort);
        serverIn = new Scanner(socketClient.getInputStream());
        serverOut = new PrintWriter(socketClient.getOutputStream(), true);
    }

    public void disconnect() {
        disconnectRequested = true;
        try {
            socketClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDirection(String message) {
        if (serverOut != null) {
            System.out.println("Sending direction: " + message);
            serverOut.println(message);
        } else {
            System.out.println("No hay mensaje.");
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
                        sendDirection(clientController.getDirection());
                        clientController.setMoveRequested(false);
                    }
                }

            }).start();

            while (!disconnectRequested) {
                StringBuilder serverMessage = new StringBuilder();

                while (true) {
                    String linea = serverIn.nextLine();
                    if (linea.isEmpty()) {
                        break;
                    }
                    serverMessage.append(linea + "\n");
                }

                clientController.render(serverMessage.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }
}