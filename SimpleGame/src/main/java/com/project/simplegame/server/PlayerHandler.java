package com.project.simplegame.server;

import com.project.simplegame.model.Direction;
import com.project.simplegame.model.Player;
import com.project.simplegame.model.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class PlayerHandler implements Runnable {

    private List<Socket> connList;
    private int nro_jugador;
    private Stage stage;
    private List<Player> playersList;
    private Player player;
    private Socket conexCliente;

    public PlayerHandler(List<Socket> connList, int nro_jugador, Stage stage, List<Player> jugadores) {
        this.connList = connList;
        this.nro_jugador = nro_jugador;
        this.stage = stage;
        this.playersList = jugadores;
        this.player = playersList.get(nro_jugador);
        this.conexCliente = connList.get(nro_jugador);
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = conexCliente.getInputStream();

            Scanner in = new Scanner(inputStream);

            actualizarMapa();

            while (in.hasNextLine()) {
                String direccion = in.nextLine();
                actualizarJugador(direccion);
                actualizarMapa();

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void actualizarJugador(String direccion) {

        switch (direccion) {
            case "RIGHT":
                player.setY(player.getY() + 1);
                player.setDirection(Direction.RIGHT);
                break;
            case "LEFT":
                player.setY(player.getY() - 1);
                player.setDirection(Direction.LEFT);
                break;
            case "UP":
                player.setX(player.getX() - 1);
                break;
            case "DOWN":
                player.setX(player.getX() + 1);
                break;
        }
    }

    public synchronized void actualizarMapa() throws IOException {

        //Player player = playersList.get(nro_jugador);
        // Clonar el mapa original
        char[][] map = stage.getMapa().clone();
        for (int i = 0; i < stage.getMapa().length; i++) {
            map[i] = stage.getMapa()[i].clone();
        }

        for (Player player : playersList) {
            int x = player.getX();
            int y = player.getY();

            String avatar = player.getAvatar();

            for (int i = y; i < y + 7; i++) {
                map[x][i] = avatar.charAt(i - y);
            }
            for (int i = y; i < y + 7; i++) {
                map[x + 1][i] = avatar.charAt(i - y + 8);
            }
        }

        StringBuilder mapaStr = new StringBuilder();
        for (char[] item : map) {
            for (int j = 0; j < item.length; j++) {
                mapaStr.append(item[j]);
            }
            mapaStr.append("\n");
        }

        for (Socket conexion : connList) {
            OutputStream outputStream = conexion.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);
            out.println(mapaStr.toString());
        }

    }

}
