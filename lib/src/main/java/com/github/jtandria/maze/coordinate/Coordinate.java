package com.github.jtandria.maze.coordinate;

import lombok.Data;

/**
 *
 * @author jtandria
 *
 */
@Data
public class Coordinate {

  /**
   * Abscises value.
   *
   * @see	Coordinate#getX()
   */
  private final int x;

  /**
   * Ordinate value.
   *
   * @see	Coordinate#getY()
   */
  private final int y;

  /**
   * Return the distance between the Cell and <b>other</b>
   *
   * @param other
   * @return The distance between the current Coordinate and the other given in parameter
   */
  public int getDistance(Coordinate other) {
    int deltaX = this.x - other.x;
    int deltaY = this.y - other.y;
    return (deltaX * deltaX) + (deltaY * deltaY);
  }
}
