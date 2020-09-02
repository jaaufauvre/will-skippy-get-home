package org.my_projects.skippy.kangaroo;

import org.junit.jupiter.api.Test;
import org.my_projects.skippy.grid.Grid;
import org.my_projects.skippy.grid.GridItem;
import org.my_projects.skippy.grid.Point;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KangarooTest {

    @Test
    void chortle_ShouldPrintToStdout() {

        // GIVEN
        var testOutStream = new ByteArrayOutputStream();
        var systemOutStream = System.out;

        // WHEN
        System.setOut(new PrintStream(testOutStream));
        new Kangaroo().chortle("Hi there!");
        System.setOut(systemOutStream);

        // THEN
        assertTrue(testOutStream.toString().matches("Hi there!\\s*"));
    }

    @Test
    void findHome_ShouldEventuallyFindHome() throws Exception {

        // GIVEN
        var grid = new Grid(5);
        var kangaroo = new Kangaroo();
        var home = new GridItem() {};
        grid.placeItem(kangaroo, new Point(0, 0));
        grid.placeItem(home, new Point(4, 4));

        // WHEN
        kangaroo.findHome(grid, home);

        // THEN
        assertEquals(grid.getItemLocation(home), grid.getItemLocation(kangaroo));
    }
}