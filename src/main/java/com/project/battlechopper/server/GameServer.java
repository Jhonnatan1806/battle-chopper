package com.project.battlechopper.server;

import com.project.battlechopper.model.Direction;
import com.project.battlechopper.model.Player;
import com.project.battlechopper.model.Stage;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer implements Runnable {
    private List<Player> playersList;
    private List<Socket> connList;
    private Stage stage;

    public GameServer() throws IOException {
        stage = new Stage("resources/mapa.txt");
        playersList = new ArrayList<>();
        connList = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            int nro_jugador = 0;
            java.net.ServerSocket serverSocket = new java.net.ServerSocket(8188);
            while (true) {
                Socket conex_cliente = serverSocket.accept();
                connList.add(conex_cliente);

                System.out.println("Engendrado jugador: " + nro_jugador);
                Player jugador = new Player(8, 20, Direction.RIGHT, String.valueOf((char)(65 + nro_jugador)));

                playersList.add(jugador);

                Thread t = new Thread(new PlayerHandler(connList, nro_jugador, stage, playersList));
                t.start();
                nro_jugador++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
