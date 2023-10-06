package com.project.battlechopper.model;

public class Bullet {

    private int x;
    private int y;
    private Direction direction;
    private final int speed;

    public Bullet(int initX, int initY, Direction direction) {
        this.x = initX;
        this.y = initY;
        this.direction = direction;
        this.speed = 1;
    }

    public String getAvatar() {
        return "o";
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    private Direction getDirection() {
        return this.direction;
    }

    public void move() {
        switch (this.getDirection()) {
            case UP:
                this.y -= this.speed;
                break;
            case DOWN:
                this.y += this.speed;
                break;
            case LEFT:
                this.x -= this.speed;
                break;
            case RIGHT:
                this.x += this.speed;
                break;
        }
    }

    public boolean isOnScreen() {
        char[][] map = new Stage("resources/mapa.txt").getMapa();
        if (x >= 0 && x < 370 && y >= 0 && y < 22) {
            if(map[y][x] == '[' || map[y][x] == ']' || map[y][x] == '*'){
                return false;
            }
            return true;
        }
        return false;
    }

}
