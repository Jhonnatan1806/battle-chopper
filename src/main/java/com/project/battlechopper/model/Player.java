package com.project.battlechopper.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

  private int x;
  private int y;
  private Direction direction;
  private final String name;
  private final List<Bullet> bullets;
  private boolean isShooting;
  private int nro_jugador;

  public Player(String name) {
    this.name = name;
    this.x = 45;
    this.y = 10;
    this.direction = Direction.RIGHT;
    this.bullets = new ArrayList<>();
    this.isShooting = false;
  }

  public String getAvatar() {
    if (this.direction == Direction.LEFT) {
      return "<-[" + this.name + "]->\n  ---q ";
    } else {
      return "<-[" + this.name + "]->\n p---  ";
    }
  }

  public int getX() {
    return x;
  }

  public Direction getDirection() {
    return direction;
  }

  public String getName() {
    return name;
  }

  public boolean getIsShooting() {
    return isShooting;
  }

  public List<Bullet> getBullets() {
    return bullets;
  }

  public int getY() {
    return y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }
  
  public int getNro_jugador() {
        return nro_jugador;
    }

    public void setNro_jugador(int nro_jugador) {
        this.nro_jugador = nro_jugador;
    }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public void setIsShooting(boolean isShooting) {
    this.isShooting = isShooting;
  }

  public void shoot(char[][] map){
    if(this.bullets.size() >= 5){
      return;
    }
    new Thread(() -> {
      Bullet bullet = new Bullet(this.x , this.y+1, this.direction);
      this.bullets.add(bullet);
      bullet.move();
      while (bullet.isOnScreen(map)) {
        bullet.move();
        try {
          Thread.sleep(25);
        } catch (InterruptedException e) {
          System.out.println(e.getMessage());
        }
      }
      this.bullets.remove(bullet);
    }).start();
  }

}