package RobotSim;

import java.util.Random;

/**
 * Enum representing the four cardinal directions a Robot can move in within the simulation.
 * This enumeration defines four possible movement directions: NORTH, EAST, SOUTH, and WEST.
 * Each direction represents a 90-degree turn from the next in a clockwise order.
 */
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    /**
     * Randomly selects one of the four directions (NORTH, EAST, SOUTH, or WEST).
     * This method is useful for setting a robot's movement direction with a random value at the start.
     *
     * @return A randomly chosen direction from the four possible values.
     */
    public static Direction getRandomDirection() {
        Random random = new Random(); // Random object to generate a random index
        return values()[random.nextInt(values().length)]; // Returns a random direction
    }

    /**
     * Determines the next direction in clockwise order relative to the current direction.
     * For example, if the current direction is NORTH, the next direction is EAST.
     * The sequence cycles back to NORTH after WEST, completing a full rotation.
     *
     * @return The next direction in clockwise order from the current one.
     */
    public Direction next() {
        return values()[(this.ordinal() + 1) % values().length];
        // Using ordinal position to get the next direction, wraps around using modulo
    }
}
