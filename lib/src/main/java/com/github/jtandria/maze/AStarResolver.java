package com.github.jtandria.maze;

import java.util.*;

import com.github.jtandria.maze.coordinate.AStarCoordinate;
import com.github.jtandria.maze.coordinate.Coordinate;

public class AStarResolver extends AbstractResolver<AStarCoordinate> implements Resolver {

    private List<AStarCoordinate> coordinatesTovisit;

    private List<AStarCoordinate> visitedPlaces;

    public AStarResolver(Maze<?> maze) {
        super(maze);
        this.coordinatesTovisit = new ArrayList<>();
        this.visitedPlaces = new ArrayList<>();
    }

    public List<Coordinate> findShortestWay(Coordinate start, Coordinate goal) {
        coordinatesTovisit.add(new AStarCoordinate(start));
        while (!coordinatesTovisit.isEmpty()) {
            currentPosition = coordinatesTovisit.get(0);
            coordinatesTovisit.remove(0);
            updateCoordinatesToVisit(goal);
            visitedPlaces.add((AStarCoordinate)currentPosition);
        }
        if (isGoalReached(goal)) {
            return buildPathAsCoordinate();
        }
        return new ArrayList<>();
    }

    private void updateCoordinatesToVisit(Coordinate coordinate) {
        if (isGoalReached(coordinate)) {
            coordinatesTovisit.clear();
        } else {
            addNeighboursAsCoordinatesToVisit(coordinate);
            sortCoordinatesToVisitByHeuristicValue();
        }
    }

    private void sortCoordinatesToVisitByHeuristicValue() {
        Collections.sort(coordinatesTovisit, Comparator.comparing(AStarCoordinate::getHeuristic));
    }

    protected List<AStarCoordinate> getNeighbours() {
        List<AStarCoordinate> neighbours = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            AStarCoordinate neighbor = getNeighbourCoordinate(direction);
            if (neighbor != null) {
                neighbor.setParent((AStarCoordinate) currentPosition);
                if (!findCoordinateWithBetterScore(neighbor)) {
                    neighbours.add(neighbor);
                }
            }
        }
        return neighbours;
    }

    private void addNeighboursAsCoordinatesToVisit(Coordinate goal) {
        List<AStarCoordinate> neighbours;
        neighbours = getNeighbours();
        neighbours.forEach(n -> n.setDistanceFromGoal(n.getDistance(goal)));
        coordinatesTovisit.addAll(neighbours);
    }

    private boolean findCoordinateWithBetterScore(AStarCoordinate coordinate) {
        Optional<AStarCoordinate> coordinateWithSameLocation = coordinatesTovisit.stream().filter(c -> c.equals(coordinate)).findFirst();
        if (coordinateWithSameLocation.isPresent()) {
            AStarCoordinate sameCoordinate = coordinateWithSameLocation.get();
            return (sameCoordinate.getDistanceFromLastStar() <= coordinate.getDistanceFromLastStar());
        }
        return false;
    }

    @Override
    protected boolean canVisitCoordinate(Coordinate coord) {
        return super.canVisitCoordinate(coord) &&
                !isCoordinateAlreadyVisited(coord);
    }

    private boolean isCoordinateAlreadyVisited(Coordinate coord) {
        return visitedPlaces.stream().anyMatch(x -> x.equals(coord));
    }

    @Override
    protected AStarCoordinate createNextCoordinate(Direction direction) {
        AStarCoordinate coordinate = new AStarCoordinate(
                currentPosition.getX() + direction.x,
                currentPosition.getY() + direction.y);
        coordinate.setDistanceFromLastStar(coordinate.getDistance(currentPosition));
        return coordinate;
    }
}
