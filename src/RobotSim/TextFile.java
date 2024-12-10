package RobotSim;

import java.io.*;
import java.nio.file.*;

/**
 * Utility class for file handling operations such as reading from and writing to text files.
 * The class provides static methods to manage file I/O, specifically for saving and loading 
 * arena data for the simulation.
 */
public class TextFile {

    /**
     * Writes content to a specified file.
     * If the file exists, it will be overwritten; otherwise, a new file will be created.
     * @param filename The name (or path) of the file to write to.
     * @param content The content to write into the file.
     * @return Returns true if the file was successfully saved, or false if an error occurred.
     */
    public static boolean writeFile(String filename, String content) {
        try {
            // Convert the content string to bytes and write to the specified file path
            Files.write(Paths.get(filename), content.getBytes());
            System.out.println("Arena saved to " + filename);
            return true;  // Indicates a successful save operation
        } catch (IOException e) {
            // Outputs error message to the console if writing to the file fails
            System.err.println("Error saving file: " + e.getMessage());
            return false;  // Indicates that the save operation failed
        }
    }

    /**
     * Reads content from a specified file.
     * This method reads all bytes from the file and converts them into a string.
     * @param filename The name (or path) of the file to read from.
     * @return The content of the file as a string, or null if an error occurred.
     */
    public static String readFile(String filename) {
        try {
            // Reads all bytes from the specified file and converts them to a string
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            // Outputs error message to the console if reading from the file fails
            System.err.println("Error reading file: " + e.getMessage());
            return null;  // Returns null to indicate that reading failed
        }
    }
}
