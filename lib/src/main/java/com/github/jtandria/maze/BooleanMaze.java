package com.github.jtandria.maze;

import com.github.jtandria.maze.coordinate.Coordinate;

public class BooleanMaze extends Maze<Boolean> {

    public BooleanMaze(Boolean[][] map) {
        super(map);
    }

    @Override
    public boolean isWall(Coordinate coordinate) {
        return map[coordinate.getY()][coordinate.getX()];
    }

    @Override
    public boolean isPath(Coordinate coordinate) {
        return !isWall(coordinate);
    }

    @Override
    public boolean isCoordinateInMaze(Coordinate coordinate) {
        return (coordinate.getY() >= 0 &&
                coordinate.getY() < map.length &&
                coordinate.getX() >= 0 &&
                coordinate.getX() < map[coordinate.getY()].length);
    }
}
