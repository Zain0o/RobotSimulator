package RobotSim;

/**
 * Represents an individual robot within the simulation arena.
 * Each robot has a unique ID, position (x, y), and a movement direction.
 * Robots can detect collisions, move within the bounds of the arena, and change direction if blocked.
 */
public class Robot {

    private int x, y;               // Position coordinates of the robot in the arena
    private int robotId;            // Unique identifier for each robot
    private Direction direction;    // Current direction of movement for the robot
    private static int robotCount = 0;  // Static counter to assign unique IDs to each robot

    /**
     * Constructs a Robot with specified position, direction, and a unique ID.
     * @param x The initial x-coordinate of the robot.
     * @param y The initial y-coordinate of the robot.
     * @param direction The initial direction the robot is facing.
     */
    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.robotId = robotCount++;  // Assigns a unique ID to each robot upon creation
    }

    /**
     * Provides a string representation of the robot's current state.
     * Includes the robot's ID, position, and direction.
     * @return A string describing the robot's ID, position, and direction.
     */
    @Override
    public String toString() {
        return "Robot " + robotId + " is at (" + x + ", " + y + ") facing " + direction;
    }

    /**
     * Gets the current x-coordinate of the robot.
     * @return The x-coordinate of the robot.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the current y-coordinate of the robot.
     * @return The y-coordinate of the robot.
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the current movement direction of the robot.
     * @return The direction the robot is facing.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Gets the unique ID of the robot.
     * @return The robot's ID.
     */
    public int getRobotId() {
        return robotId;
    }

    /**
     * Checks if the robot is currently located at the specified coordinates.
     * Useful for collision detection with other robots or arena boundaries.
     * @param sx The x-coordinate to check.
     * @param sy The y-coordinate to check.
     * @return True if the robot is at the specified location, false otherwise.
     */
    public boolean isHere(int sx, int sy) {
        return this.x == sx && this.y == sy;
    }

    /**
     * Attempts to move the robot in its current direction.
     * If the robot encounters a boundary or another robot, it changes direction.
     * @param arena The RobotArena in which the robot moves, used to check valid positions.
     */
    public void tryToMove(RobotArena arena) {
        int newX = x;
        int newY = y;

        // Determines the new position based on the current direction
        switch (direction) {
            case NORTH: newY--; break;
            case EAST:  newX++; break;
            case SOUTH: newY++; break;
            case WEST:  newX--; break;
        }

        // Checks if the new position is valid (no collision and within arena bounds)
        if (arena.canMoveHere(newX, newY)) {
            x = newX;  // Updates the robot's position if valid
            y = newY;
        } else {
            direction = direction.next();  // Changes direction if movement is blocked
        }
    }

    /**
     * Displays the robot on the specified ConsoleCanvas by marking its position with 'R'.
     * @param canvas The ConsoleCanvas on which to display the robot.
     */
    public void displayRobot(ConsoleCanvas canvas) {
        canvas.showIt(x, y, 'R');  // Displays the robot at its current location on the canvas
    }
}
