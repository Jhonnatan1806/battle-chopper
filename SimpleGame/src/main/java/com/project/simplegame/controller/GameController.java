package com.project.simplegame.controller;

import com.project.simplegame.model.Direction;
import com.project.simplegame.model.Player;
import com.project.simplegame.model.Stage;
import com.project.simplegame.view.GameView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameController{    
    
    private GameView gameView;
    private Stage stage;
    private ArrayList<Player> playerList;
    
    public GameController(GameView gameView){
        this.gameView = gameView;
        try {
            this.stage = new Stage("mapa.txt");
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.playerList = new ArrayList<>();
        playerList.add(new Player(8, 20, Direction.RIGHT, "A"));
        this.defaultSettings();
    }
    
    private void defaultSettings(){
        this.update();
    }
    
    public void showMap(char[][] map){
        StringBuilder mapaStr = new StringBuilder();
        for (char[] item : map) {
            for (int j = 0; j < item.length; j++) {
                mapaStr.append(item[j]);
            }
            mapaStr.append("\n"); // Agregar un salto de línea al final de cada fila
        }

        gameView.getCanvas().setText(mapaStr.toString()); // Establecer el contenido del mapa en el TextArea
    }
            
    public void update(){
        
        char[][] map = stage.getMapa().clone();
        Player player  =  playerList.get(0);
        
        int x = player.getX();
        int y = player.getY();
        String name = player.getName();
        String avatar = player.getAvatar();
        
        // Clonar el mapa original
        for (int i = 0; i < stage.getMapa().length; i++) {
            map[i] = stage.getMapa()[i].clone();
        }

        for (int i = y; i < y + 7; i++) {
            map[x][i] = avatar.charAt(i - y);
        }
        for (int i = y; i < y + 7; i++) {
            map[x + 1][i] = avatar.charAt(i - y + 8);
        }
        
        //this.showMap(map);
    }

    public Player getPlayer(int index) {
        return playerList.get(index);
    }
    
    
   
}
