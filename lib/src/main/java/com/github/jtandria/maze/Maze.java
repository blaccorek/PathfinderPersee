package com.github.jtandria.maze;

import com.github.jtandria.maze.coordinate.Coordinate;

import lombok.Data;

@Data
public abstract class Maze<T> {

    T[][] map;

    protected Maze(T[][] map) {
        this.map = map.clone();
    }

    abstract boolean isWall(Coordinate coord);

    abstract boolean isPath(Coordinate coord);

    abstract boolean isCoordinateInMaze(Coordinate coordinate);
}
