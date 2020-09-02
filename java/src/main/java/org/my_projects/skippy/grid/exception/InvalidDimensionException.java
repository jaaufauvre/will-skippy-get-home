package org.my_projects.skippy.grid.exception;

public class InvalidDimensionException extends GridException {
    public InvalidDimensionException() {
        super("Dimension of the grid must be a positive integer!");
    }
}
