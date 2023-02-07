package com.github.jtandria.maze.coordinate;

public class WrongCoordinateFormatException extends Exception {
    static final String MESSAGE_FORMAT = "Coordinate (\"%s\") has wrong format. Must be \"n,n\"";

    public WrongCoordinateFormatException(String coordinates) {
        super(String.format(MESSAGE_FORMAT, coordinates));
    }

    public WrongCoordinateFormatException(String coordinates, Throwable cause) {
        super(String.format(MESSAGE_FORMAT, coordinates), cause);
    }
}
