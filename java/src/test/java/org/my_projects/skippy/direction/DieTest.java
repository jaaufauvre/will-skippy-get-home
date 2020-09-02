package org.my_projects.skippy.direction;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DieTest {

    @Test
    void getInstance_ShouldReturnSameInstance() {
        assertSame(Die.getInstance(), Die.getInstance());
    }

    @Test
    void roll_ShouldReturnDirection() {
        assertNotNull(Die.getInstance().roll());
    }

    @Test
    void printStats_ShouldPrintStatsToStdout() {

        // GIVEN
        var testOutStream = new ByteArrayOutputStream();
        var systemOutStream = System.out;

        // WHEN
        System.setOut(new PrintStream(testOutStream));
        Die.getInstance().roll();
        Die.getInstance().printStats();
        System.setOut(systemOutStream);

        // THEN
        /*
        Die statistics:
        Total throws: 100
        North: 25% South: 25% East: 25% West: 25%
        */
        var floatRegExp = "[+-]?([0-9]*[.])?[0-9]+";
        var statsRegExp = "Die statistics:\\s" +
                "Total throws: " + floatRegExp + "\\s" +
                "North: " + floatRegExp + "% South: " + floatRegExp + "% East: " + floatRegExp + "% West: " + floatRegExp + "%\\s*";
        var stats = testOutStream.toString();
        assertTrue(stats.matches(statsRegExp));
    }
}