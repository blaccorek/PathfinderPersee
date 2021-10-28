package com.github.jtandria.maze;

import java.util.*;
import java.util.stream.Collectors;

import com.github.jtandria.maze.coordinate.AStarCoordinate;
import com.github.jtandria.maze.coordinate.Coordinate;
import com.github.jtandria.maze.coordinate.DirectionEnum;

import lombok.Getter;
import lombok.Setter;

public class Resolver {

  private AStarCoordinate currentPosition;

  private List<AStarCoordinate> openList;

  private List<AStarCoordinate> closedList;

  @Getter
  @Setter
  private boolean[][] maze;

  public Resolver(boolean[][] maze) {
    this.maze = maze.clone();
    this.openList = new ArrayList<>();
    this.closedList = new ArrayList<>();
  }

  private boolean isCoordinateInMaze(Coordinate coord) {
    return (
      coord.getY() >= 0 &&
      coord.getY() < maze.length &&
      coord.getX() >= 0 &&
      coord.getX() < maze[coord.getY()].length
    );
  }

  AStarCoordinate createNextCoordinate(DirectionEnum direction) {
    AStarCoordinate coordinate;
    int deltaX;
    int deltaY;

    if (direction == DirectionEnum.UP || direction == DirectionEnum.DOWN) {
      deltaX = 0;
      deltaY = (direction == DirectionEnum.DOWN ? -1 : 1);
    } else {
      deltaX = (direction == DirectionEnum.LEFT ? -1 : 1);
      deltaY = 0;
    }
    coordinate =
      new AStarCoordinate(
        currentPosition.getX() + deltaX,
        currentPosition.getY() + deltaY
      );
    coordinate.setDistanceFromLastStar(coordinate.getDistance(currentPosition));
    return coordinate;
  }

  private AStarCoordinate getNeighborCoordinate(DirectionEnum direction) {
    AStarCoordinate nPoint;

    nPoint = createNextCoordinate(direction);
    if (
      isCoordinateInMaze(nPoint) &&
      !maze[nPoint.getX()][nPoint.getY()] &&
      closedList.stream().noneMatch(x -> x.equals(nPoint))
    ) {
      return nPoint;
    }
    return null;
  }

  boolean openSetContainsBetterScore(AStarCoordinate newCoordinate) {
    AStarCoordinate match;
    Optional<AStarCoordinate> search;

    search = openList.stream().filter(c -> c.equals(newCoordinate)).findFirst();
    if (search.isPresent()) {
      match = search.get();
      return (
        match.getDistanceFromLastStar() <=
        newCoordinate.getDistanceFromLastStar()
      );
    }
    return false;
  }

  private List<AStarCoordinate> getNeighbors() {
    AStarCoordinate neighbor;
    List<AStarCoordinate> neighborsList;

    neighborsList = new ArrayList<>();
    for (DirectionEnum direction : DirectionEnum.values()) {
      neighbor = getNeighborCoordinate(direction);
      if (neighbor != null) {
        neighbor.setParent(currentPosition);
        if (!openSetContainsBetterScore(neighbor)) {
          neighborsList.add(neighbor);
        }
      }
    }
    return neighborsList;
  }

  private List<AStarCoordinate> buildPath() {
    List<AStarCoordinate> path = new ArrayList<>();

    while (currentPosition != null) {
      path.add(0, currentPosition);
      currentPosition = currentPosition.getParent();
    }
    return path;
  }

  private void sortOpenList() {
    final Comparator<AStarCoordinate> comparator = (AStarCoordinate c1, AStarCoordinate c2) ->
      Integer.compare(c1.getHeuristic(), c2.getHeuristic());
    Collections.sort(openList, comparator);
  }

  private boolean isGoalReached(final Coordinate goal) {
    return currentPosition.getDistance(goal) == 0;
  }

  private void updateOpenList(Coordinate goal) {
    List<AStarCoordinate> neighborsList;

    neighborsList = getNeighbors();
    neighborsList.forEach(n -> n.setDistanceFromGoal(n.getDistance(goal)));
    openList.addAll(neighborsList);
    sortOpenList();
  }

  public List<Coordinate> findShortestWay(Coordinate start, Coordinate goal) {
    List<Coordinate> resultPath;

    openList.add(new AStarCoordinate(start));
    while (!openList.isEmpty()) {
      currentPosition = openList.get(0);
      openList.remove(0);
      if (isGoalReached(goal)) {
        openList.clear();
      } else {
        updateOpenList(goal);
      }
      closedList.add(currentPosition);
    }
    if (isGoalReached(goal)) {
      resultPath =
        buildPath()
          .stream()
          .map(Coordinate.class::cast)
          .collect(Collectors.toList());
    } else {
      resultPath = new ArrayList<>();
    }
    return resultPath;
  }
}
