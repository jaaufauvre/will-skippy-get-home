package org.my_projects.skippy.grid;

import org.my_projects.skippy.direction.Direction;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * A point on a grid, defined by a pair of coordinates.
 */
public final class Point {

    private final long x;
    private final long y;
    
    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    /**
     * Get the neighbour point located at the North, East, South, or West of this point.
     * Doesn't check bounds.
     */
    public Point getNeighbour(Direction direction) {
        switch (direction) {
            case EAST:
                return new Point(x + 1, y);
            case WEST:
                return new Point(x - 1, y);
            case NORTH:
                return new Point(x, y + 1);
            case SOUTH:
                return new Point(x, y - 1);
            default:
                throw new IllegalStateException(String.format("Unsupported direction: %s!", direction));
        }
    }

    @Override
    public String toString() {
        return MessageFormat.format("({0}, {1})", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
