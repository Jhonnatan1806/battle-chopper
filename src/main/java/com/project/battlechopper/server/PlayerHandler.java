package com.project.battlechopper.server;

import com.project.battlechopper.model.Direction;
import com.project.battlechopper.model.Player;
import com.project.battlechopper.model.Stage;
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
    private boolean connected;


    public PlayerHandler(List<Socket> connList, int nro_jugador, Stage stage, List<Player> jugadores) {
        this.connList = connList;
        this.nro_jugador = nro_jugador;
        this.stage = stage;
        this.playersList = jugadores;
        this.player = playersList.get(nro_jugador);
        this.conexCliente = connList.get(nro_jugador);
        this.connected = true;
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
                logMessageReceived(direccion);
                actualizarJugador(direccion);
                actualizarMapa();

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexCliente.close();
                connected = false;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void actualizarJugador(String direccion) {

        int [] pos = parsePosition(direccion);

        int currentX = player.getX() + pos[0];
        int currentY = player.getY() + pos[1];

        if(!isValidMovement(stage.getMapa(), currentX, currentY)){
            return;
        }

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
        try {
            for (char[] item : map) {
                for (int j = 0; j < item.length; j++) {
                    mapaStr.append(item[j]);
                }
                mapaStr.append("\n");
            }

            for (Socket conexion : connList) {
                // verificar si la conexion esta abierta
                if (!conexion.isClosed()) {
                    OutputStream outputStream = conexion.getOutputStream();
                    PrintWriter out = new PrintWriter(outputStream, true);
                    out.println(mapaStr.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void logMessageReceived(String message) {
        System.out.println("Mensaje recibido del cliente " + nro_jugador + ": " + message);
    }

    public boolean isValidMovement(char[][] map, int x, int y){
        if( x>=0 && x<24 && y>=0 && y<370){
            if( map[x][y] != ' ' || map[x][y+1] != ' ' || map[x][y+2] != ' ' || map[x][y+6] != ' '){
                return false;
            }
            if(map[x+1][y] != ' '|| map[x+1][y+1] != ' '|| map[x+1][y+2] != ' ' || map[x+1][y+6] != ' '){
                return false;
            }
            return true;
        }
        return false;
    }

    public int[] parsePosition(String direction){
        switch (direction){
            case "UP":
                return new int[]{-1, 0};
            case "DOWN":
                return new int[]{1, 0};
            case "LEFT":
                return new int[]{0, -1};
            case "RIGHT":
                return new int[]{0, 1};
            default:
                return new int[]{0, 0};
        }
    }
}