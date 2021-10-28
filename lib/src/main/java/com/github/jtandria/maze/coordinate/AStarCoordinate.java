package com.github.jtandria.maze.coordinate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AStarCoordinate extends Coordinate {

  private int distanceFromLastStar;

  private int distanceFromGoal;

  private AStarCoordinate parent;

  public AStarCoordinate(int x, int y) {
    super(x, y);
    this.distanceFromLastStar = 0;
    this.distanceFromGoal = 0;
    this.parent = null;
  }

  public AStarCoordinate(Coordinate coordinate) {
    this(coordinate.getX(), coordinate.getY());
  }

  public int getHeuristic() {
    return distanceFromLastStar + distanceFromGoal;
  }

  @Override
  public boolean equals(Object other) {
    return super.equals(other);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
