package com.project.simplegame.controller;

import com.project.simplegame.model.Direction;
import com.project.simplegame.model.Player;
import com.project.simplegame.model.Stage;
import com.project.simplegame.view.GameView;
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
            this.stage = new Stage("resources/mapa.txt");
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.playerList = new ArrayList<>();
        playerList.add(new Player(8, 20, Direction.RIGHT, "A"));
        this.defaultSettings();
    }
    
    private void defaultSettings(){
        Player player = playerList.get(0);
        this.updatePositionPlayer(0, 0, player);
    }
    
    public Player getPlayer(int index) {
        return playerList.get(index);
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
            
    public void updatePositionPlayer(int x, int y, Player player){
        
        char[][] map = stage.getMapa().clone();
        
        int currentX = player.getX() + x;
        int currentY = player.getY() + y;
        
        if(!isValid(map, currentX , currentY )){
            return; // si no es valido no realiza movimiento
        }
        
        player.setX(currentX);
        player.setY(currentY);
        String avatar = player.getAvatar();
        
        // Clonar el mapa original
        for (int i = 0; i < map.length; i++) {
            map[i] = stage.getMapa()[i].clone();
        }

        for (int i = currentY; i < currentY + 7; i++) {
            map[currentX][i] = avatar.charAt(i - currentY);
        }
        for (int i = currentY; i < currentY + 7; i++) {
            map[currentX + 1][i] = avatar.charAt(i - currentY + 8);
        }
        
        this.showMap(map);
    }

    public boolean isValid(char[][] map, int x, int y){
        //System.out.println("eje X "+y+" eje Y "+x);
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
   
}
