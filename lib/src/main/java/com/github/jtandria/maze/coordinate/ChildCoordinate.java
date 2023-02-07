package com.github.jtandria.maze.coordinate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

public class ChildCoordinate extends Coordinate{
    @JsonIgnore @Setter @Getter
    private AStarCoordinate parent;

    public ChildCoordinate(int x, int y) {
        super(x, y);
        this.parent = null;
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
