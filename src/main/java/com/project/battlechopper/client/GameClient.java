package com.project.battlechopper.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameClient implements Runnable {
    private String serverIP;
    private int serverPort;
    private Socket socketClient;
    private Scanner serverIn;
    private PrintWriter serverOut;
    private ClientController clientController;
    private boolean disconnectRequested = false;

    public GameClient(String serverIP, int serverPort, ClientController clientController) {
        this.clientController = clientController;
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void connect() throws IOException {
        socketClient = new Socket(serverIP, serverPort);
        serverIn = new Scanner(socketClient.getInputStream());
        serverOut = new PrintWriter(socketClient.getOutputStream(), true);
    }

    public synchronized void disconnect() {
        disconnectRequested = true;
        try {
            if (socketClient != null && !socketClient.isClosed()) {
                socketClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socketClient = null;
            serverIn = null;
            serverOut = null;
        }
    }

    public void sendDataPlayer(String message) {
        if (serverOut != null) {
            System.out.println("Sending data: " + message);
            serverOut.println(message);
        }
    }

    public void receiveDataMap(){
        StringBuilder serverMessage = new StringBuilder();
        if (serverIn.hasNextLine()) {
            while (true) {
                String linea = serverIn.nextLine();
                if (linea.isEmpty()) {
                    break;
                }
                serverMessage.append(linea).append("\n");
            }
            clientController.render(serverMessage.toString());
        }
    }

    @Override
    public void run() {
        try {
            connect();

            new Thread(() -> {
                while (!disconnectRequested) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sendDataPlayer(clientController.getMessage());
                }
            }).start();

            while (!disconnectRequested) {
                receiveDataMap();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

}