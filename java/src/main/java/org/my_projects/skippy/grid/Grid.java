package org.my_projects.skippy.grid;

import org.my_projects.skippy.direction.Direction;
import org.my_projects.skippy.grid.exception.ItemExistsException;
import org.my_projects.skippy.grid.exception.ItemNotFoundException;
import org.my_projects.skippy.grid.exception.OutOfBoundsException;

import java.util.concurrent.ConcurrentHashMap;

/**
 * A square grid with items placed on it.
 */
public final class Grid {

    private final long dimension;

    /** Items placed on the grid so far */
    private final ConcurrentHashMap<Object, Point> items = new ConcurrentHashMap<>();

    public Grid(long dimension) throws IllegalArgumentException {
        if (dimension < 1) {
            throw new IllegalArgumentException("Dimension of the grid must be a positive integer!");
        }
        this.dimension = dimension;
    }

    public void placeItem(Object item, Point location) throws ItemExistsException, OutOfBoundsException {
        if (items.containsKey(item)) {
            throw new ItemExistsException();
        }
        checkBounds(location);
        items.put(item, location);
    }

    public Point moveItem(Object item, Direction direction) throws ItemNotFoundException, OutOfBoundsException {
        if (!items.containsKey(item)) {
            throw new ItemNotFoundException();
        }
        var currentLocation = items.get(item);
        var newLocation = currentLocation.getNeighbour(direction);
        checkBounds(newLocation);
        items.replace(item, newLocation);
        return newLocation;
    }

    public Point getItemLocation(Object item) throws ItemNotFoundException {
        if (!items.containsKey(item)) {
            throw new ItemNotFoundException();
        }
        return items.get(item);
    }

    private void checkBounds(Point location) throws OutOfBoundsException {
        var x = location.getXCoordinate();
        var y = location.getYCoordinate();
        if (x < 0 || y < 0 || x > dimension - 1 || y > dimension - 1) {
            throw new OutOfBoundsException(location);
        }
    }
}
