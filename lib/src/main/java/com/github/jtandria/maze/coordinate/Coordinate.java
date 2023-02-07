package com.github.jtandria.maze.coordinate;

import lombok.Data;

@Data
public class Coordinate {

  private final int x;

  private final int y;

  public int getDistance(Coordinate other) {
    int deltaX = this.x - other.getX();
    int deltaY = this.y - other.getY();
    return (deltaX * deltaX) + (deltaY * deltaY);
  }
}
