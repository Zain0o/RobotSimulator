package RobotSim;

import java.util.Scanner;

/**
 * The RobotInterface class provides a console-based interface to interact with the RobotArena and control robots.
 * It allows users to add robots, display arena information, simulate robot movement, create a new arena, and save/load arena states.
 * This is the main class for managing user interactions with the simulation.
 */
public class RobotInterface {

    private Scanner s;             // Scanner for reading user input
    private RobotArena myArena;    // The RobotArena instance that contains and manages the robots
    private ConsoleCanvas canvas;  // ConsoleCanvas for displaying the arena

    /**
     * Constructor for RobotInterface.
     * Initializes the Scanner for user input and creates a new RobotArena for the simulation.
     */
    public RobotInterface() {
        s = new Scanner(System.in);                    // Sets up scanner for console input
        myArena = new RobotArena(20, 6);               // Creates a 20x6 arena
        canvas = new ConsoleCanvas(20, 6, "32019071"); // Initialize the canvas with dimensions and student ID
        char ch;

        // Main loop for interacting with the user
        do {
            System.out.print("Enter (A)dd Robot, get (I)nformation, (D)isplay arena, (M)ove robots, (S)imulate, (N)ew arena, (L)oad, (W)rite or e(X)it > ");
            ch = s.next().charAt(0); // Read the first character of user input
            s.nextLine(); // Clear the input buffer

            switch (ch) {
                case 'A': // Add a robot if 'A' or 'a' is entered
                case 'a':
                    myArena.addRobot();
                    printRobotInfo(); // Display updated robot information after adding
                    break;
                case 'I': // Display robot information if 'I' or 'i' is entered
                case 'i':
                    printRobotInfo();
                    break;
                case 'D': // Display the arena with robots if 'D' or 'd' is entered
                case 'd':
                    displayArena();
                    break;
                case 'M': // Move all robots if 'M' or 'm' is entered
                case 'm':
                    myArena.moveAllRobots(); // Move all robots
                    displayArena();          // Display the updated arena after moving robots
                    printRobotInfo();         // Display updated robot information
                    break;
                case 'S': // Simulate robot movements if 'S' or 's' is entered
                case 's':
                    simulate();  // Simulate robot movements 10 times with delays
                    break;
                case 'N': // Reset the arena if 'N' or 'n' is entered
                case 'n':
                    newArena();
                    break;
                case 'W': // Save the arena state if 'W' or 'w' is entered
                case 'w':
                    saveArena();
                    break;
                case 'L': // Load the arena state if 'L' or 'l' is entered
                case 'l':
                    loadArena();
                    printRobotInfo(); // Display updated robot information after loading
                    break;
                case 'x': // Exit the program if 'X' or 'x' is entered
                case 'X':
                    ch = 'X'; // Set ch to 'X' to exit the loop
                    break;
            }
        } while (ch != 'X'); // Continue the loop until 'X' is entered

        s.close(); // Close the scanner
    }

    /**
     * Displays the arena using the current canvas.
     */
    private void displayArena() {
        canvas.clear();             // Clear previous display
        myArena.showArena(canvas);  // Pass canvas to RobotArena for rendering
    }

    /**
     * Prints the updated information of all robots in the arena in a formatted style.
     * This method iterates through all robots and displays their position and direction.
     */
    private void printRobotInfo() {
        System.out.println("\nUpdated Robot Information:");
        System.out.println("Arena Size: " + myArena.getXMax() + " x " + myArena.getYMax());

        // Iterate through all robots and print their information
        for (Robot r : myArena.getRobots()) {
            System.out.println("Robot " + r.getRobotId() + " is at (" + r.getX() + ", " + r.getY() + ") moving in direction " + r.getDirection());
        }
    }

    /**
     * Simulates robot movements for 10 steps, with a 200ms delay between each move.
     * Each step updates the robot positions, displays the arena, and prints the robot information.
     */
    private void simulate() {
        for (int i = 0; i < 10; i++) {
            printRobotInfo();       // Print robot positions before the movement
            myArena.moveAllRobots(); // Move all robots

            displayArena();         // Display the updated arena after moving robots
            printRobotInfo();       // Print robot information after the movement

            try {
                Thread.sleep(200);  // Wait for 200 milliseconds before the next move
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Resets the current arena or creates a new one with specified dimensions.
     * If the user confirms, they can input new dimensions; otherwise, the default or current dimensions are used.
     */
    private void newArena() {
        System.out.print("Do you want to specify new dimensions? (Y/N): ");
        char response = s.next().charAt(0);
        s.nextLine(); // Clear the input buffer

        if (response == 'Y' || response == 'y') {
            int newWidth = 0, newHeight = 0;

            while (true) {
                System.out.print("Enter new arena width: ");
                if (s.hasNextInt()) {
                    newWidth = s.nextInt();
                    s.nextLine(); // Clear the input buffer
                    break;
                } else {
                    System.out.println("Invalid input. Please enter an integer value for width.");
                    s.next(); // Clear the invalid input
                }
            }

            while (true) {
                System.out.print("Enter new arena height: ");
                if (s.hasNextInt()) {
                    newHeight = s.nextInt();
                    s.nextLine(); // Clear the input buffer
                    break;
                } else {
                    System.out.println("Invalid input. Please enter an integer value for height.");
                    s.next(); // Clear the invalid input
                }
            }

            myArena = new RobotArena(newWidth, newHeight);
            canvas = new ConsoleCanvas(newWidth, newHeight, "32019071"); // Reinitialize canvas with new dimensions
        } else {
            // Reset to default dimensions or keep the existing dimensions
            myArena = new RobotArena(myArena.getXMax(), myArena.getYMax());
        }
        System.out.println("New arena created.");
    }

    /**
     * Saves the current state of the arena to a text file.
     * Prompts the user to enter a filename and then writes the arena data to that file.
     */
    private void saveArena() {
        System.out.print("Enter filename to save the arena: ");
        String filename = s.nextLine(); // Get filename from user
        String arenaData = myArena.toString();
        boolean isSaved = TextFile.writeFile(filename, arenaData); // Updated method to return success
        if (isSaved) {
            System.out.println("Successfully saved arena to '" + filename + "'");
        } else {
            System.out.println("Failed to save arena to '" + filename + "'");
        }
    }

    /**
     * Loads the arena state from a text file.
     * Prompts the user for a filename and loads the arena's state if the file exists.
     */
    private void loadArena() {
        System.out.print("Enter filename to load the arena: ");
        String filename = s.nextLine(); // Get filename from user
        String arenaData = TextFile.readFile(filename);
        if (arenaData != null) {
            myArena.loadFromString(arenaData);
            System.out.println("Successfully loaded arena from '" + filename + "'");
        } else {
            System.out.println("Failed to load arena from '" + filename + "'");
        }
    }

    /**
     * Main method to start the RobotInterface program.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new RobotInterface(); // Start the RobotInterface
    }
}
