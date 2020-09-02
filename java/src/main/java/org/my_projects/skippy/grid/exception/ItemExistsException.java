package org.my_projects.skippy.grid.exception;

public final class ItemExistsException extends GridException {
    public ItemExistsException() {
        super("The item is already on the grid!");
    }
}
