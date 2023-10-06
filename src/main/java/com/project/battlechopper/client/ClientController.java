package com.project.battlechopper.client;

import com.project.battlechopper.model.Direction;
import com.project.battlechopper.model.Player;
import com.project.battlechopper.model.Stage;

import javax.swing.*;
import java.awt.*;
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
        char initMap[][] = new Stage("resources/inicio.txt").getMapa();
        StringBuilder mapaStr = new StringBuilder();
        for (char[] item : initMap) {
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

    public void movePlayer(int x,int y,Direction direction,boolean isShooting) {
        if (isShooting) {
            player.setIsShooting(true);
            return;
        }
        if(isPossibleToMove(direction)){
            switch (direction) {
                case UP, DOWN:
                    player.setY(player.getY() + y);
                    player.setIsShooting(false);
                    break;
                case LEFT:
                    player.setX(player.getX() + x);
                    player.setIsShooting(false);
                    player.setDirection(direction);
                    moveCanvasLeft(clientView.getCanvas());
                    break;
                case RIGHT:
                    player.setX(player.getX() + x);
                    player.setIsShooting(false);
                    player.setDirection(direction);
                    moveCanvasRight(clientView.getCanvas());
                    break;
            }

        }
    }

    public boolean isPossibleToMove(Direction direction) {
        int x = 0;
        int y = 0;

        switch (direction) {
            case UP:
                y = player.getY() - 1;
                x = player.getX();
                break;
            case DOWN:
                y = player.getY() + 1;
                x = player.getX();
                break;
            case LEFT:
                x = player.getX() - 1;
                y = player.getY();
                break;
            case RIGHT:
                x = player.getX() + 1;
                y = player.getY();
                break;
            default:
                return false;
        }

        if (x >= 0 && x < 370 && y >= 0 && y < 22) {
            for(int i = 0; i < 7; i++){
                if(map[y][x+i] != ' ' || map[y+1][x+i] != ' '){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void moveCanvasLeft(JTextArea canvas) {
        JViewport viewport = (JViewport) canvas.getParent();
        int viewPosition = viewport.getViewPosition().x;

        int scrollAmount = 6;

        if(player.getX() <= 322){
            int newViewPosition = viewPosition - scrollAmount;

            if (newViewPosition < 0) {
                newViewPosition = 0;
            }

            viewport.setViewPosition(new Point(newViewPosition, 0));
        }
    }

    public void moveCanvasRight(JTextArea canvas) {
        JViewport viewport = (JViewport) canvas.getParent();
        int viewPosition = viewport.getViewPosition().x;

        int scrollAmount = 6;

        if(player.getX() >= 48){
            int newViewPosition = viewPosition + scrollAmount;

            int maxX = canvas.getWidth() - viewport.getWidth();
            if (newViewPosition > maxX) {
                newViewPosition = maxX;
            }

            viewport.setViewPosition(new Point(newViewPosition, 0));
        }
    }

}
