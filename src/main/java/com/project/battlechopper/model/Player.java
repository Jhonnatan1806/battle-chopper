package com.project.battlechopper.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

  private int x;
  private int y;
  private Direction direction;
  private String name;
  private List<Bullet> bullets;

  public Player(int x, int y, Direction direction, String name) {
    this.x = x;
    this.y = y;
    this.direction = direction;
    this.name = name;
    this.bullets = new ArrayList<>();
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

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Bullet> getBullets() {
    return bullets;
  }

  public void shoot(){
    if(this.bullets.size() >= 3){
      return;
    }
    new Thread(() -> {
      Bullet bullet = new Bullet(this.x, this.y, this.direction);
      this.bullets.add(bullet);
      bullet.move();
      while (bullet.isOnScreen()) {
        bullet.move();
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      this.bullets.remove(bullet);
    }).start();
  }

}