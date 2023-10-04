package com.project.simplegame.trash;

import com.project.simplegame.model.Direction;
import com.project.simplegame.model.Player;
import com.project.simplegame.model.Stage;

import java.util.ArrayList;

public class GameController {
    
    private GameView gameView;
    private Stage stage;
    private ArrayList<Player> playerList;
    
    public GameController(GameView gameView, Player player) {
        this.gameView = gameView;
        this.stage = new Stage("resources/mapa.txt");
        this.playerList = new ArrayList<>();
        playerList.add(player);
        this.defaultSettings();
    }
    
    private void defaultSettings(){
        Player player = playerList.get(0);
        this.move(Direction.NONE, player);
    }
    
    public void showMap(char[][] map){
        StringBuilder mapaStr = new StringBuilder();
        for (char[] item : map) {
            for (int j = 0; j < item.length; j++) {
                mapaStr.append(item[j]);
            }
            mapaStr.append("\n");
        }

        gameView.getCanvas().setText(mapaStr.toString());
    }

    public void move(Direction direction, Player player){

        char[][] map = stage.getMapa().clone();

        int[] pos =  parseDirectionToPosition(direction);

        int currentX = player.getX() + pos[0];
        int currentY = player.getY() + pos[1];

        if(!isValid(map, currentX , currentY )){
            return;
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

    public int[] parseDirectionToPosition(Direction direction){
        switch (direction){
            case UP:
                return new int[]{-1, 0};
            case DOWN:
                return new int[]{1, 0};
            case LEFT:
                return new int[]{0, -1};
            case RIGHT:
                return new int[]{0, 1};
            default:
                return new int[]{0, 0};
        }
    }
   
}
