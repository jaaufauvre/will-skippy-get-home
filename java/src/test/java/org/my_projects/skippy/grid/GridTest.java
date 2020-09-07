package org.my_projects.skippy.grid;

import org.junit.jupiter.api.Test;
import org.my_projects.skippy.grid.exception.ItemExistsException;
import org.my_projects.skippy.grid.exception.ItemNotFoundException;
import org.my_projects.skippy.grid.exception.OutOfBoundsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.my_projects.skippy.direction.Direction.*;

class GridTest {

    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenDimensionNotAPositiveInteger() {
        var exception1 = assertThrows(IllegalArgumentException.class, () -> new Grid(0));
        var exception2 = assertThrows(IllegalArgumentException.class, () -> new Grid(-10));
        var expectedErrorMessage = "Dimension of the grid must be a positive integer!";
        assertEquals(expectedErrorMessage, exception1.getMessage());
        assertEquals(expectedErrorMessage, exception2.getMessage());
    }

    @Test
    void placeItem_ShouldPlaceItemAtGivenLocation() throws Exception {

        // GIVEN
        var gridItem = new Object();
        var grid = new Grid(10);

        // WHEN
        grid.placeItem(gridItem, new Point(3, 4));

        // THEN
        assertEquals(new Point(3, 4), grid.getItemLocation(gridItem));
    }

    @Test
    void placeItem_ShouldThrowItemExistsException_WhenItemAlreadyOnTheGrid() throws Exception {

        // GIVEN
        var gridItem = new Object();
        var grid = new Grid(2);
        grid.placeItem(gridItem, new Point(0, 0));

        // WHEN & THEN
        var exception = assertThrows(ItemExistsException.class, () ->
            grid.placeItem(gridItem, new Point(1, 1))
        );
        assertEquals("The item is already on the grid!", exception.getMessage());
    }

    @Test
    void placeItem_ShouldThrowOutOfBoundsException_WhenItemPlacedOutsideTheGrid() {

        // GIVEN
        var gridItem = new Object();
        var grid = new Grid(1);

        // WHEN & THEN
        var exception = assertThrows(OutOfBoundsException.class, () ->
            grid.placeItem(gridItem, new Point(1, 0))
        );
        assertEquals("This location is outside the grid: (1, 0)!", exception.getMessage());
        assertEquals(new Point(1, 0), exception.getLocation());
    }

    @Test
    void moveItem_ShouldMoveItemInTheGivenDirection() throws Exception {

        // GIVEN
        var gridItem = new Object();
        var grid = new Grid(10);
        grid.placeItem(gridItem, new Point(3, 4));

        // WHEN & THEN
        assertEquals(new Point(3, 5), grid.moveItem(gridItem, NORTH));
        assertEquals(new Point(3, 5), grid.getItemLocation(gridItem));
        assertEquals(new Point(3, 4), grid.moveItem(gridItem, SOUTH));
        assertEquals(new Point(3, 4), grid.getItemLocation(gridItem));
        assertEquals(new Point(4, 4), grid.moveItem(gridItem, EAST));
        assertEquals(new Point(4, 4), grid.getItemLocation(gridItem));
        assertEquals(new Point(3, 4), grid.moveItem(gridItem, WEST));
        assertEquals(new Point(3, 4), grid.getItemLocation(gridItem));
    }

    @Test
    void moveItem_ShouldThrowOutOfBoundsException_WhenItemMovedOutsideTheGrid() throws Exception {

        // GIVEN
        var gridItem = new Object();
        var grid = new Grid(1);
        grid.placeItem(gridItem, new Point(0, 0));

        // WHEN & THEN
        var exception = assertThrows(OutOfBoundsException.class, () ->
            grid.moveItem(gridItem, SOUTH)
        );
        assertEquals("This location is outside the grid: (0, -1)!", exception.getMessage());
        assertEquals(new Point(0, -1), exception.getLocation());
    }

    @Test
    void moveItem_ShouldThrowItemNotFoundException_WhenItemNotOnTheGrid() {

        // GIVEN
        var gridItem = new Object();
        var grid = new Grid(1);

        // WHEN & THEN
        var exception = assertThrows(ItemNotFoundException.class, () ->
            grid.moveItem(gridItem, SOUTH)
        );
        assertEquals("The item is not on the grid!", exception.getMessage());
    }

    @Test
    void getItemLocation_ShouldReturnItemLocation() throws Exception {

        // GIVEN
        var gridItem = new Object();
        var grid = new Grid(10);
        grid.placeItem(gridItem, new Point(4, 5));

        // WHEN & THEN
        assertEquals(new Point(4, 5), grid.getItemLocation(gridItem));
    }

    @Test
    void getItemLocation_ShouldThrowItemNotFoundException_WhenItemNotOnTheGrid() {

        // GIVEN
        var gridItem = new Object();
        var grid = new Grid(1);

        // WHEN & THEN
        var exception = assertThrows(ItemNotFoundException.class, () ->
            grid.getItemLocation(gridItem)
        );
        assertEquals("The item is not on the grid!", exception.getMessage());
    }
}