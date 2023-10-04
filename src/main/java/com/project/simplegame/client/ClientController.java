package com.project.simplegame.client;

import com.project.simplegame.model.Direction;
import com.project.simplegame.model.Player;
import com.project.simplegame.model.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClientController {

    ClientView clientView;
    ClientSocket clientSocket;
    ArrayList<String> mapList;
    Player player;
    boolean moveRequested;
    public ClientController(ClientView clientView) {
        this.clientView = clientView;
        this.player = new Player(8, 20, Direction.NONE, "Player");
        this.moveRequested = false;
        this.mapList = new ArrayList<>();
        this.defaultSettings();
    }

    private void defaultSettings(){
        Stage stage = new Stage("resources/mapa.txt");
        char[][] map = stage.getMapa();
        StringBuilder mapaStr = new StringBuilder();
        for (char[] item : map) {
            for (int j = 0; j < item.length; j++) {
                mapaStr.append(item[j]);
            }
            mapaStr.append("\n");
        }
        clientView.getCanvas().setText(mapaStr.toString());
    }

    public void render(String map){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                clientView.getCanvas().setText(map);
            }
        });
    }

    public void connect(String serverAddress, int serverPort ){
        clientSocket = new ClientSocket(serverAddress, serverPort , this);
        new Thread(clientSocket).start();
    }

    public void disconnect(){
        clientSocket.disconnect();
        defaultSettings();
    }

    public boolean isMoveRequested() {
        return moveRequested;
    }

    public void setMoveRequested(boolean state) {
        moveRequested = state;
    }

    public String getDirection() {
        switch (player.getDirection()){
            case UP:
                return "UP";
            case DOWN:
                return "DOWN";
            case LEFT:
                return "LEFT";
            case RIGHT:
                return "RIGHT";
            default:
                return "NONE";
        }
    }
}