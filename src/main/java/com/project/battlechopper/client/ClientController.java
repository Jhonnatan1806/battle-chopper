package com.project.battlechopper.client;

import com.project.battlechopper.model.Direction;
import com.project.battlechopper.model.Player;
import com.project.battlechopper.model.Stage;

import java.util.ArrayList;

public class ClientController {

    ClientView clientView;
    GameClient gameClient;
    Player player;
    char[][] map;

    public ClientController(ClientView clientView) {
        this.clientView = clientView;
        this.player = new Player("Client");
        this.map = new Stage("resources/mapa.txt").getMapa();
        this.defaultSettings();
    }

    private void defaultSettings() {
        StringBuilder mapaStr = new StringBuilder();
        for (char[] item : map) {
            for (int j = 0; j < item.length; j++) {
                mapaStr.append(item[j]);
            }
            mapaStr.append("\n");
        }
        clientView.getCanvas().setText(mapaStr.toString());
    }

    public void render(String map) {
        clientView.getCanvas().setText(map);
    }

    public void connect(String serverAddress, int serverPort) {
        gameClient = new GameClient(serverAddress, serverPort, this);
        new Thread(gameClient).start();
    }

    public void disconnect() {
        player.setX(45);
        player.setY(10);
        player.setDirection(Direction.RIGHT);
        gameClient.disconnect();
        defaultSettings();
    }

    public String getMessage() {
        String message = player.getX() + "," + player.getY() + "," + player.getDirection() + "," + player.getIsShooting();
        player.setIsShooting(false);
        return message;
    }

    public boolean isPossibleToMove(Direction direction) {
        int x = 0;
        int y = 0;

        switch (direction) {
            case UP:
                y = player.getY() - 1;
                break;
            case DOWN:
                y = player.getY() + 1;
                break;
            case LEFT:
                x = player.getX() - 1;
                break;
            case RIGHT:
                x = player.getX() + 1;
                break;
            default:
                return false;
        }

        if (x >= 0 && x < 370 && y >= 0 && y < 22) {
            /*if( map[y][x] != ' ' || map[y][x+1] != ' ' || map[y][x+2] != ' ' || map[y][x+6] != ' '){
                return false;
            }
            if(map[y+1][x] != ' '|| map[y+1][x+1] != ' '|| map[y+1][x+2] != ' ' || map[y+1][x+6] != ' '){
                return false;
            }*/
            return true;
        }
        return false;
    }

}
