package com.github.jtandria.maze.coordinate;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoordinateTest {
    @Test
    public void distance_between_coordinate_must_be_one() {
        // Arrange
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(0, 1);
        // Act
        int distance = start.getDistance(end);
        // Assert
        Assertions.assertEquals(1, distance);
    }

    @Test
    public void distance_between_coordinate_must_be_two() {
        // Arrange
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(1, 1);
        // Act
        int distance = start.getDistance(end);
        // Assert
        Assertions.assertEquals(2, distance);
    }

    @Test
    public void coordinate_must_be_different() {
        // Arrange
        Coordinate start = new Coordinate(0, 1);
        Coordinate end = new Coordinate(1, 0);
        // Assert
        assertNotEquals(start, end);
    }
}
