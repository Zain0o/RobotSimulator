package RobotSim;

/**
 * This Console Canvas shows the Robot arena in the CLI 
 * Using a 2D array to show the Arena, fills in the border (#) and position of the robot (R).
 * It puts StudentID at the top of the Arena.
 */
public class ConsoleCanvas {

    private char[][] canvas;  // 2D array Displaying the arena
    private int width, height; // Size of the arena
    private String studentNumber; // Student ID displayed at the top of the arena

    /**
     * The Constructor initializes dimensions and the Student ID.
     * It adds the border which forms the arena and has the 
     * StudentID at the top (center).
     */
    public ConsoleCanvas(int width, int height, String studentNumber) {
        this.width = width + 2;  // +2 to account for the left and right borders
        this.height = height + 2;  // +2 to account for the top and bottom borders
        this.studentNumber = studentNumber;

        // Initialize the 2D array representing the arena
        canvas = new char[this.height][this.width];

        // Fill the canvas with border symbols and spaces inside
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (i == 0 || i == this.height - 1 || j == 0 || j == this.width - 1) {
                    canvas[i][j] = '#';  // Border symbol
                } else {
                    canvas[i][j] = ' ';  // Empty space inside the border
                }
            }
        }

        // Position the student number in the top border, centered horizontally
        int start = (this.width - studentNumber.length()) / 2;
        for (int i = 0; i < studentNumber.length(); i++) {
            canvas[0][start + i] = studentNumber.charAt(i);
        }
    }

    /**
     * Clears the canvas, resetting the inner cells while preserving the border.
     */
    public void clear() {
        // Clears only the inside of the canvas, leaving borders intact
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                canvas[i][j] = ' ';  // Reset to empty space
            }
        }
    }

    /**
     * Places a character symbol (e.g., 'R' for Robot) at the specified (x, y) coordinates on the canvas.
     * This method offsets the x and y coordinates by 1 to account for the border around the arena.
     * 
     * @param x The x-coordinate for placing the character within the inner arena
     * @param y The y-coordinate for placing the character within the inner arena
     * @param symbol The character symbol to place on the canvas
     */
    public void showIt(int x, int y, char symbol) {
        // Offset by 1 to place within the inner arena (inside the border)
        canvas[y + 1][x + 1] = symbol;
    }

    /**
     * Converts the entire canvas to a string representation, ready for display.
     * This method iterates over the 2D array, appending each character to form rows,
     * and includes newline characters for correct formatting.
     * 
     * @return The string representation of the canvas, with borders and student ID
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Loop through each row and column of the canvas
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(canvas[i][j]);
            }
            sb.append("\n");  // Newline after each row for proper display
        }

        return sb.toString();
    }

    /**
     * Main method to test the ConsoleCanvas class functionality.
     * This method creates a sample arena, adds a robot character ('R') at specific coordinates,
     * and displays the final layout of the canvas.
     * 
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a ConsoleCanvas with width 10, height 5, and student ID
        ConsoleCanvas c = new ConsoleCanvas(10, 5, "32019071");
        
        // Place a robot represented by 'R' at coordinates (4,3) inside the arena
        c.showIt(4, 3, 'R');
        
        // Output the canvas to the console
        System.out.println(c.toString());
    }
}
