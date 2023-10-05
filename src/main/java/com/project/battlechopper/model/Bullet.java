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
                this.x -= this.speed;
                break;
            case DOWN:
                this.x += this.speed;
                break;
            case LEFT:
                this.y -= 2*this.speed;
                break;
            case RIGHT:
                this.y += 2*this.speed;
                break;
        }
    }

    public boolean isOnScreen() {
        return this.x >= 0 && this.x < 23 && this.y >= 0 && this.y < 100;
    }

}