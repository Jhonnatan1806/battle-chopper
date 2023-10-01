package com.project.simplegame.model;

public class Player {

  private int x;
  private int y;
  private Direction direction;
  private String name;

  public Player(int x, int y, Direction direction, String name) {
    this.x = x;
    this.y = y;
    this.direction = direction;
    this.name = name;
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
  
  

}