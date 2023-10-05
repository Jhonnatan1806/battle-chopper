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

    public void sendData(String message) {
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

            while (!disconnectRequested) {
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
                } else {
                    Thread.sleep(100);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

}