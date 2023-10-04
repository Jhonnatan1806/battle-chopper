package com.project.simplegame.client;

import com.project.simplegame.model.Direction;
import com.project.simplegame.model.Player;
import com.project.simplegame.model.Stage;

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
        this.mapList = new ArrayList<>();
        this.player = new Player(8, 20, Direction.NONE, "0");
        this.moveRequested = false;
        this.defaultSettings();
    }

    private void defaultSettings(){
        Stage stage = new Stage("resources/mapa.txt");
        char[][] map = stage.getMapa();
        render(map);
    }

    private void render(char[][] map){
        StringBuilder mapaStr = new StringBuilder();
        for (char[] item : map) {
            for (int j = 0; j < item.length; j++) {
                mapaStr.append(item[j]);
            }
            mapaStr.append("\n");
        }

        clientView.getCanvas().setText(mapaStr.toString());
    }

    public void connect(String serverAddress, int serverPort ){
        clientSocket = new ClientSocket(serverAddress, serverPort , this);
        new Thread(clientSocket).start();
    }

    public void disconnect(){
        clientSocket.disconnect();
        defaultSettings();
    }

    public void sendMap(String partMap){
        mapList.add(partMap);
        if (mapList.size() >= 26) {
            char[][] mapArray = convertToCharArray(mapList);
            render(mapArray);
            mapList.removeAll(mapList);
        }
    }

    private char[][] convertToCharArray(List<String> lines) {
        int numRows = lines.size();
        int numCols = lines.get(0).length();
        char[][] mapArray = new char[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String line = lines.get(i);
            mapArray[i] = line.toCharArray();
        }

        return mapArray;
    }

    public boolean isMoveRequested() {
        return moveRequested;
    }

    public void setMoveRequested(boolean b) {
        moveRequested = b;
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