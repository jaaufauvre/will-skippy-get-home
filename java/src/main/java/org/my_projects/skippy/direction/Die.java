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

    /** Faces of the die are directions */
    private static final Direction[] faces = Direction.class.getEnumConstants();

    /**  Keep track of the number of throws in each direction so far */
    private final ConcurrentHashMap<Direction, Long> history = new ConcurrentHashMap<>();

    /** A PRNG for the die using the current time as seed */
    private final Random random = new Random(new Date().getTime());

    private Die() {
        Arrays.stream(faces).forEach(face -> history.put(face, 0L));
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
        var direction = faces[random.nextInt(faces.length)];
        history.compute(direction, (k, v) -> v + 1);
        return direction;
    }

    /**
     * Print the die statistics.
     *
     * Die statistics:
     * Total throws: 53332
     * North: 25.2% South: 24.8% East: 25.2% West: 24.8%
     */
    public void printStats() {
        var stats = "Die statistics:\n";
        stats += MessageFormat.format("Total throws: {0}\n", computeTotalThrowCount());
        stats += MessageFormat.format("North: {0}% South: {1}% East: {2}% West: {3}%",
                                      computePercentageOf(NORTH), computePercentageOf(SOUTH),
                                      computePercentageOf(EAST), computePercentageOf(WEST));
        System.out.println(stats);
    }

    private Long computeTotalThrowCount() {
        return history.values().stream().mapToLong(Long::longValue).sum();
    }

    private String computePercentageOf(Direction direction) {
        var percentage = (float) (history.get(direction) * 100.0 / computeTotalThrowCount());
        return String.format("%.1f", percentage);
    }
}
