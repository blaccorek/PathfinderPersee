package com.github.jtandria.maze;

import java.util.List;

import com.github.jtandria.maze.coordinate.Coordinate;

public interface Resolver {
    List<Coordinate> findShortestWay(Coordinate start, Coordinate goal);
}
