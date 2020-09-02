package org.my_projects.skippy.kangaroo;

import org.my_projects.skippy.direction.Die;
import org.my_projects.skippy.grid.Grid;
import org.my_projects.skippy.grid.GridItem;
import org.my_projects.skippy.grid.exception.ItemNotFoundException;
import org.my_projects.skippy.grid.exception.OutOfBoundsException;

import java.text.MessageFormat;

public final class Kangaroo implements GridItem {

    /**
     * Make the Kangaroo say something.
     */
    public void chortle(String something) {
        System.out.println(something);
    }

    /**
     * Ask the kangaroo to find its home on the grid.
     * The kangaroo’s strategy to find his way home is to take random hops either North, South, East or West.
     */
    public void findHome(Grid grid, GridItem home) throws ItemNotFoundException {
        var hopCount = 0L;
        while (!grid.getItemLocation(this).equals(grid.getItemLocation(home))) {
            takeRandomHop(grid);
            hopCount++;
        }
        chortle(MessageFormat.format("Finished in {0} hops!", hopCount));
    }

    private void takeRandomHop(Grid grid) throws ItemNotFoundException {
        try {
            var randomDirection = Die.getInstance().roll();
            var location = grid.moveItem(this, randomDirection);
            chortle(MessageFormat.format("Hopped to: {0}", location));
        } catch (OutOfBoundsException e) {
            chortle(MessageFormat.format("Oops, hit the boundary: {0}", e.getLocation()));
        }
    }
}
