package com.project.battlechopper.client;

import com.project.battlechopper.model.Direction;
import com.project.battlechopper.model.Player;
import com.project.battlechopper.model.Stage;

import java.util.ArrayList;

public class ClientController {

    ClientView clientView;
    GameClient gameClient;
    ArrayList<String> mapList;
    Player player;
    boolean isShooting;

    public ClientController(ClientView clientView) {
        this.clientView = clientView;
        this.player = new Player(8, 20, Direction.NONE, "Player");
        this.mapList = new ArrayList<>();
        this.isShooting = false;
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
        clientView.getCanvas().setText(map);
    }

    public void connect(String serverAddress, int serverPort ){
        gameClient = new GameClient(serverAddress, serverPort , this);
        new Thread(gameClient).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    gameClient.sendData(player.getDirection()+","+ isShooting);
                    player.setDirection(Direction.NONE);
                    isShooting = false;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void disconnect(){
        gameClient.disconnect();
        defaultSettings();
    }

    public void setData(Direction direction, boolean state) {
        player.setDirection(direction);
        isShooting = state;
    }
}