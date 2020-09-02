package org.my_projects.skippy.grid.exception;

import org.my_projects.skippy.grid.Point;

import java.text.MessageFormat;

public final class OutOfBoundsException extends GridException {

    private final transient Point location;

    public OutOfBoundsException(Point location) {
        super(MessageFormat.format("This location is outside the grid: {0}!", location));
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }
}
