package RobotSim;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents an arena where multiple robots operate.
 * Manages robot positions, movement within boundaries, collision checking, and state saving/loading.
 * The arena's dimensions and the list of robots it contains are defined by this class.
 */
public class RobotArena {

    private int xmax, ymax;                   // Maximum dimensions of the arena
    private ArrayList<Robot> robots;          // Stores all robots within the arena
    private Random randomGenerator;           // Random generator for placing robots at random positions
  
    /**
     * Constructs a new RobotArena with specified width and height.
     * @param xmax The maximum x-coordinate (width) of the arena.
     * @param ymax The maximum y-coordinate (height) of the arena.
     */
    public RobotArena(int xmax, int ymax) {
        this.xmax = xmax;
        this.ymax = ymax;
        robots = new ArrayList<>();           // Initializes the list of robots
        randomGenerator = new Random();       // Sets up the random generator
    }

    /**
     * Getter for xmax, the width of the arena.
     * @return The maximum x-coordinate (width) of the arena.
     */
    public int getXMax() {
        return xmax;
    }

    /**
     * Getter for ymax, the height of the arena.
     * @return The maximum y-coordinate (height) of the arena.
     */
    public int getYMax() {
        return ymax;
    }

    /**
     * Getter for the list of robots currently in the arena.
     * @return An ArrayList of robots in the arena.
     */
    public ArrayList<Robot> getRobots() {
        return robots;
    }

    /**
     * Adds a new robot at a random position and direction, ensuring no overlap with existing robots.
     */
    public void addRobot() {
        int randomX, randomY;
        Direction randomDirection = Direction.getRandomDirection();  // Chooses a random initial direction
        do {
            randomX = randomGenerator.nextInt(xmax);  // Random x-coordinate within bounds
            randomY = randomGenerator.nextInt(ymax);  // Random y-coordinate within bounds
        } while (getRobotAt(randomX, randomY) != null);  // Ensures the position is unique
        robots.add(new Robot(randomX, randomY, randomDirection));  // Adds the robot to the arena
    }

    /**
     * Checks if a robot is present at a given (x, y) position.
     * @param x The x-coordinate to check.
     * @param y The y-coordinate to check.
     * @return The robot at (x, y) if one exists, null otherwise.
     */
    public Robot getRobotAt(int x, int y) {
        for (Robot r : robots) {
            if (r.isHere(x, y)) {
                return r;  // Returns the robot if found at (x, y)
            }
        }
        return null;  // No robot found at the specified location
    }

    /**
     * Checks if a robot can move to the specified (x, y) location without leaving the arena or colliding with another robot.
     * @param x The x-coordinate to move to.
     * @param y The y-coordinate to move to.
     * @return True if the position is valid and unoccupied, false otherwise.
     */
    public boolean canMoveHere(int x, int y) {
        if (x < 0 || x >= xmax || y < 0 || y >= ymax) {
            return false;  // Out of bounds
        }
        return getRobotAt(x, y) == null;  // Checks if the position is occupied
    }

    /**
     * Moves all robots in the arena by attempting to move each robot in its current direction.
     */
    public void moveAllRobots() {
        for (Robot r : robots) {
            r.tryToMove(this);  // Each robot tries to move based on its logic and arena constraints
        }
    }

    /**
     * Displays the arena with all robots in their current positions on the provided canvas.
     * @param canvas The ConsoleCanvas object used for rendering the arena.
     */
    public void showArena(ConsoleCanvas canvas) {
        canvas.clear();  // Clear canvas for fresh display
        for (Robot r : robots) {
            r.displayRobot(canvas);  // Display each robot on the given canvas
        }
        System.out.println(canvas.toString());  // Print the canvas to console
    }

    /**
     * Saves the arena's current state (robots' positions and directions) to a string format.
     * This is useful for saving and reloading the arena state.
     * @return A string representing the arena's dimensions and the state of each robot.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(xmax).append(" ").append(ymax).append("\n"); // Saves arena dimensions
        for (Robot r : robots) {
            sb.append(r.getX()).append(" ").append(r.getY()).append(" ").append(r.getDirection()).append("\n");  // Saves each robot's state
        }
        return sb.toString();
    }

    /**
     * Loads the arena's state from a string. Clears current robots and updates dimensions and robots.
     * @param data The string containing the saved arena state.
     */
    public void loadFromString(String data) {
        robots.clear();  // Clears existing robots to load new state
        String[] lines = data.split("\n");

        // Parsing arena dimensions from the first line
        StringSplitter splitter = new StringSplitter(lines[0], " ");
        int[] dimensions = splitter.getIntegers();
        if (dimensions.length == 2) {
            xmax = dimensions[0];
            ymax = dimensions[1];
        } else {
            System.err.println("Error parsing arena dimensions. Ensure the file format is correct.");
            return;
        }

        // Loading each robot's position and direction
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) {
                continue;  // Skips empty lines
            }

            splitter = new StringSplitter(line, " ");
            String[] robotData = splitter.getStrings();
            if (robotData.length == 3) {
                try {
                    int x = Integer.parseInt(robotData[0]);
                    int y = Integer.parseInt(robotData[1]);
                    String directionStr = robotData[2].toUpperCase().trim();

                    // Validates and parses the direction
                    Direction direction;
                    try {
                        direction = Direction.valueOf(directionStr);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid direction at line " + (i + 1) + ": " + directionStr);
                        continue;  // Skips invalid entries
                    }

                    robots.add(new Robot(x, y, direction));  // Adds each loaded robot
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing robot coordinates at line " + (i + 1) + ": " + e.getMessage());
                }
            } else {
                System.err.println("Unexpected format at line " + (i + 1));
            }
        }
    }
}
