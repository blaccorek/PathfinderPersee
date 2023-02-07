package com.github.jtandria.maze.coordinate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AStarCoordinate extends ChildCoordinate {

  @JsonIgnore
  private int distanceFromLastStar;

  @JsonIgnore
  private int distanceFromGoal;

  public AStarCoordinate(int x, int y) {
    super(x, y);
    this.distanceFromLastStar = 0;
    this.distanceFromGoal = 0;
  }

  public AStarCoordinate(Coordinate coordinate) {
    this(coordinate.getX(), coordinate.getY());
  }

  @JsonIgnore
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
