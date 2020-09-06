package org.my_projects.skippy.direction;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DieTest {

    @Test
    void getInstance_ShouldAlwaysReturnSameInstance() {
        assertSame(Die.getInstance(), Die.getInstance());
    }

    @Test
    void roll_ShouldReturnRandomDirection() {
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
        var floatRegExp = "[+-]?([0-9]*[.])?[0-9]+";
        var statsRegExp = "Die statistics:\\s" +
                "Total throws: " + floatRegExp + "\\s" +
                "North: " + floatRegExp + "% South: " + floatRegExp + "% East: " + floatRegExp + "% West: " + floatRegExp + "%\\s*";
        var stats = testOutStream.toString();
        assertTrue(stats.matches(statsRegExp));
    }
}