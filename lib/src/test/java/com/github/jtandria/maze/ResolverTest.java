package com.github.jtandria.maze;

import java.util.List;

import com.github.jtandria.maze.coordinate.Coordinate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

class ResolverTest {

  @ParameterizedTest
  @CsvSource({ "0, 1", "1, 0", "1, 2", "2, 1" })
  void testReallyShortPath(ArgumentsAccessor arguments) {
    final boolean[] line0 = { false, false, false };
    final boolean[] line1 = { false, false, false };
    final boolean[] line2 = { false, false, false };
    final boolean[][] maze = { line0, line1, line2 };
    final Resolver resolver = new Resolver(maze);
    final Coordinate start = new Coordinate(1, 1);
    final Coordinate goal = new Coordinate(
      arguments.getInteger(0),
      arguments.getInteger(1)
    );

    List<?> result = resolver.findShortestWay(start, goal);
    Assertions.assertEquals(
      2,
      result.size(),
      "The number of elements in the path is incorrect"
    );
    Assertions.assertEquals(
      start,
      result.get(0),
      "Missing start coordinate in path"
    );
    Assertions.assertEquals(
      goal,
      result.get(result.size() - 1),
      "Missing goal coordinate in path"
    );
  }

  @ParameterizedTest
  @CsvSource({ "0, 0", "4, 0", "4, 4", "0, 4" })
  void testSmallOpenMaze(ArgumentsAccessor arguments) {
    final boolean[] line0 = { false, false, false, false, false };
    final boolean[] line1 = { false, true, false, true, false };
    final boolean[] line2 = { false, false, false, false, false };
    final boolean[] line4 = { false, true, false, true, false };
    final boolean[] line5 = { false, false, false, false, false };
    final boolean[][] maze = { line0, line1, line2, line4, line5 };
    final Resolver resolver = new Resolver(maze);
    final Coordinate start = new Coordinate(2, 2);
    final Coordinate goal = new Coordinate(
      arguments.getInteger(0),
      arguments.getInteger(1)
    );

    List<?> result = resolver.findShortestWay(start, goal);
    Assertions.assertEquals(
      5,
      result.size(),
      "The number of elements in the path is incorrect"
    );
    Assertions.assertEquals(
      start,
      result.get(0),
      "Missing start coordinate in path"
    );
    Assertions.assertEquals(
      goal,
      result.get(result.size() - 1),
      "Missing goal coordinate in path"
    );
  }
}
