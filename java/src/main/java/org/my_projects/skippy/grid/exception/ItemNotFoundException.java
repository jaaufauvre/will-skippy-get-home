package org.my_projects.skippy.grid.exception;

public class ItemNotFoundException extends GridException {
    public ItemNotFoundException() {
        super("The item is not on the grid!");
    }
}
