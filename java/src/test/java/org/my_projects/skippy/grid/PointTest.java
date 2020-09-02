package org.my_projects.skippy.grid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.my_projects.skippy.direction.Direction.EAST;
import static org.my_projects.skippy.direction.Direction.NORTH;
import static org.my_projects.skippy.direction.Direction.SOUTH;
import static org.my_projects.skippy.direction.Direction.WEST;

class PointTest {

    @Test
    void getX() {
        assertEquals(4L, new Point(4,5).getX());
    }

    @Test
    void getY() {
        assertEquals(5L, new Point(4,5).getY());
    }

    @Test
    void getNeighbour_ShouldReturnNeighbours() {
        var point = new Point(0, 0);
        assertEquals(new Point(0, 1), point.getNeighbour(NORTH));
        assertEquals(new Point(0, -1), point.getNeighbour(SOUTH));
        assertEquals(new Point(-1, 0), point.getNeighbour(WEST));
        assertEquals(new Point(1, 0), point.getNeighbour(EAST));
    }

    @Test
    void testToString() {
        assertEquals("(4, 5)", new Point(4,5).toString());
    }

    @Test
    void testEquals() {
        assertEquals(new Point(4,5), new Point(4,5));
        assertNotEquals(new Point(1,2), new Point(3,4));
    }

    @Test
    void testHashCode() {
        assertEquals(1090, new Point(4,5).hashCode());
    }
}