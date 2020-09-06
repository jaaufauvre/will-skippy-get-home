package org.my_projects.skippy.grid;

import org.my_projects.skippy.direction.Direction;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * A point on a grid, defined by a pair of coordinates.
 */
public final class Point {

    private final long xCoordinate;
    private final long yCoordinate;
    
    public Point(long xCoordinate, long yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public long getXCoordinate() {
        return xCoordinate;
    }

    public long getYCoordinate() {
        return yCoordinate;
    }

    /**
     * Get the neighbour point located at the North, East, South, or West of this point.
     * Doesn't check bounds.
     */
    public Point getNeighbour(Direction direction) {
        switch (direction) {
            case EAST:
                return new Point(xCoordinate + 1, yCoordinate);
            case WEST:
                return new Point(xCoordinate - 1, yCoordinate);
            case NORTH:
                return new Point(xCoordinate, yCoordinate + 1);
            case SOUTH:
                return new Point(xCoordinate, yCoordinate - 1);
            default:
                throw new IllegalArgumentException(String.format("Unsupported direction: %s!", direction));
        }
    }

    @Override
    public String toString() {
        return MessageFormat.format("({0}, {1})", xCoordinate, yCoordinate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var point = (Point) o;
        return xCoordinate == point.xCoordinate &&
                yCoordinate == point.yCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate);
    }
}
