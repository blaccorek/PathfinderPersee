package com.github.jtandria.maze;

import java.util.ArrayList;
import java.util.List;

import com.github.jtandria.maze.coordinate.Coordinate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

class AStarResolverTest {

    @ParameterizedTest
    @CsvSource({ "0, 1", "1, 0", "1, 2", "2, 1" })
    void validate_going_in_all_direction(ArgumentsAccessor arguments) {
        final Boolean[] line0 = { false, false, false };
        final Boolean[] line1 = { false, false, false };
        final Boolean[] line2 = { false, false, false };
        final Boolean[][] map = { line0, line1, line2 };
        final Maze<?> maze = new BooleanMaze(map);

        final Resolver resolver = new AStarResolver(maze);
        final Coordinate start = new Coordinate(1, 1);
        final Coordinate goal = new Coordinate(
                arguments.getInteger(0),
                arguments.getInteger(1));

        List<?> result = resolver.findShortestWay(start, goal);
        Assertions.assertEquals(
                2,
                result.size(),
                "The number of elements in the path is incorrect");
        Assertions.assertEquals(
                start,
                result.get(0),
                "Missing start coordinate in path");
        Assertions.assertEquals(
                goal,
                result.get(result.size() - 1),
                "Missing goal coordinate in path");
    }

    @ParameterizedTest
    @CsvSource({ "0, 0", "4, 0", "4, 4", "0, 4" })
    void validate_going_in_all_direction_with_walls(ArgumentsAccessor arguments) {
        final Boolean[] line0 = { false, false, false, false, false };
        final Boolean[] line1 = { false, true, false, true, false };
        final Boolean[] line2 = { false, false, false, false, false };
        final Boolean[] line4 = { false, true, false, true, false };
        final Boolean[] line5 = { false, false, false, false, false };
        final Boolean[][] map = { line0, line1, line2, line4, line5 };
        final Maze<?> maze = new BooleanMaze(map);

        final Resolver resolver = new AStarResolver(maze);
        final Coordinate start = new Coordinate(2, 2);
        final Coordinate goal = new Coordinate(
                arguments.getInteger(0),
                arguments.getInteger(1));

        List<?> result = resolver.findShortestWay(start, goal);
        Assertions.assertEquals(
                5,
                result.size(),
                "The number of elements in the path is incorrect");
        Assertions.assertEquals(
                start,
                result.get(0),
                "Missing start coordinate in path");
        Assertions.assertEquals(
                goal,
                result.get(result.size() - 1),
                "Missing goal coordinate in path");
    }

    @Test
    void test_getting_shortest_way() {
        // Arrange
        final Boolean[] line0 = { false, false, false, false, false };
        final Boolean[] line1 = { true, true, true, true, false };
        final Boolean[] line2 = { false, false, false, false, false };
        final Boolean[] line4 = { false, true, true, true, false };
        final Boolean[] line5 = { false, false, false, false, false };
        final Boolean[][] map = { line0, line1, line2, line4, line5 };
        final Maze<?> maze = new BooleanMaze(map);

        final Resolver resolver = new AStarResolver(maze);
        final Coordinate start = new Coordinate(4, 4);
        final Coordinate goal = new Coordinate(0, 0);

        final List<Coordinate> expected = new ArrayList<Coordinate>() {
            {
                add(new Coordinate(4, 4));
                add(new Coordinate(4, 3));
                add(new Coordinate(4, 2));
                add(new Coordinate(4, 1));
                add(new Coordinate(4, 0));
                add(new Coordinate(3, 0));
                add(new Coordinate(2, 0));
                add(new Coordinate(1, 0));
                add(new Coordinate(0, 0));
            }
        };
        // Act
        List<?> result = resolver.findShortestWay(start, goal);
        // Assert
        Assertions.assertEquals(expected, result);
    }

}
