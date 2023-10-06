package com.project.battlechopper.server;

import com.project.battlechopper.model.Bullet;
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
    //private boolean connected;

    public PlayerHandler(List<Socket> connList, int nro_jugador, Stage stage, List<Player> jugadores) {
        this.connList = connList;
        this.nro_jugador = nro_jugador;
        this.stage = stage;
        this.playersList = jugadores;
        for (Player player : playersList) {
            if (player.getNro_jugador() == nro_jugador) {
                this.player = player;
                break;
            }
        }
        this.conexCliente = connList.get(nro_jugador);
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = conexCliente.getInputStream();

            Scanner in = new Scanner(inputStream);

            sendDataMap();

            while (in.hasNextLine()) {
                String mensaje = in.nextLine();
                if (mensaje.equals("close")) {
                    playersList.remove(player);
                    break;
                }
                logMessageReceived(mensaje);
                receiveDataPlayer(mensaje);
                sendDataMap();

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexCliente.close();
                //connected = false;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void receiveDataPlayer(String data) {

        // receive data of player -> "x,y,direction,shooting"
        String[] parts = data.split(",");

        // check if data is valid
        if (parts.length != 4) {
            return;
        }

        // parse data
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Direction direction = Direction.valueOf(parts[2]);
        boolean isShooting = parts[3].equals("true");

        // set player position
        player.setX(x);
        player.setY(y);

        // set player direction
        player.setDirection(direction);

        // shoot
        if (isShooting) {
            player.shoot();
        }
    }

    public synchronized void sendDataMap() throws IOException {
        char[][] map = stage.getMapa().clone();
        for (int i = 0; i < stage.getMapa().length; i++) {
            map[i] = stage.getMapa()[i].clone();
        }

        for (Player player : playersList) {
            int x = player.getX();
            int y = player.getY();

            String avatar = player.getAvatar();

            for (int i = 0; i < 7; i++) {
                map[y][x + i] = avatar.charAt(i);
            }
            for (int i = 0; i < 7; i++) {
                map[y + 1][x + i] = avatar.charAt(i + 8);
            }

            for (Bullet bullet : player.getBullets()) {
                int bulletX = bullet.getX();
                int bulletY = bullet.getY();
                map[bulletY][bulletX] = bullet.getAvatar().charAt(0);
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

}
