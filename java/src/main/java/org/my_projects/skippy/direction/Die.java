package org.my_projects.skippy.direction;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import static org.my_projects.skippy.direction.Direction.*;

/**
 * A 4-sided die, with each face representing a direction: North, East, South, or West.
 */
public final class Die {

    private static Die instance = null;
    private static final Direction[] directions = Direction.class.getEnumConstants();

    /**  Keep track of the number of throws in each direction so far */
    private final ConcurrentHashMap<Direction, Long> history = new ConcurrentHashMap<>();
    private final Random random = new Random(new Date().getTime());

    private Die() {
        Arrays.stream(directions).forEach(direction -> history.put(direction, 0L));
    }

    public static synchronized Die getInstance() {
        if (instance == null) {
            instance = new Die();
        }
        return instance;
    }

    /**
     * Roll the die to return a random direction.
     */
    public Direction roll() {
        var direction = directions[random.nextInt(directions.length)];
        history.computeIfPresent(direction, (k, v) -> v + 1);
        return direction;
    }

    public void printStats() {
        var stats = "Die statistics:\n";
        stats += MessageFormat.format("Total throws: {0}\n", getThrowCount());
        stats += MessageFormat.format("North: {0}% South: {1}% East: {2}% West: {3}%",
                                      getPercentageOf(NORTH), getPercentageOf(SOUTH),
                                      getPercentageOf(EAST), getPercentageOf(WEST));
        System.out.println(stats);
    }

    private Long getThrowCount() {
        return history.values().stream().mapToLong(Long::longValue).sum();
    }

    private String getPercentageOf(Direction direction) {
        var percentage = (float) (history.get(direction) * 100.0 / getThrowCount());
        return String.format("%.1f", percentage);
    }
}
