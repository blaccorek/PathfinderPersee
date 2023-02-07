package com.github.jtandria.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.jtandria.maze.coordinate.ChildCoordinate;
import com.github.jtandria.maze.coordinate.Coordinate;

public abstract class AbstractResolver<T extends ChildCoordinate>
{
    protected ChildCoordinate currentPosition;

    private Maze<?> maze;

    protected AbstractResolver(Maze<?> maze) {
        this.maze = maze;
    }

    protected abstract T createNextCoordinate(Direction direction);

    protected boolean isGoalReached(final Coordinate goal) {
        return currentPosition.getDistance(goal) == 0;
    }

    protected boolean canVisitCoordinate(Coordinate coord) {
        return maze.isCoordinateInMaze(coord) &&
                maze.isPath(coord);
    }

    protected T getNeighbourCoordinate(Direction direction) {
        T neighbourCoordinate = createNextCoordinate(direction);
        if (canVisitCoordinate(neighbourCoordinate)) {
            return neighbourCoordinate;
        }
        return null;
    }

    protected List<Coordinate> buildPathAsCoordinate() {
        return buildPath()
                .stream()
                .map(Coordinate.class::cast)
                .collect(Collectors.toList());
    }

    protected List<ChildCoordinate> buildPath() {
        List<ChildCoordinate> path = new ArrayList<>();
        while (currentPosition != null) {
            path.add(0, currentPosition);
            currentPosition = currentPosition.getParent();
        }
        return path;
    }
}
