package RobotSim;

import java.util.Arrays;

/**
 * The StringSplitter class provides utility methods for splitting a string
 * into an array of substrings based on a given separator. It also offers
 * methods to retrieve these substrings as either strings or integers.
 */
public class StringSplitter {

    private String[] manyStrings;  // Array to hold substrings after splitting

    /**
     * Constructor that splits the input string based on the specified separator.
     * @param input The input string to be split.
     * @param separator The character(s) to split the input string by.
     */
    public StringSplitter(String input, String separator) {
        this.manyStrings = input.split(separator);  // Splits the string based on the separator
    }

    /**
     * Returns a copy of the split strings as an array of strings.
     * This method provides a safe copy to prevent modification of the original data.
     * @return An array of strings representing the split substrings.
     */
    public String[] getStrings() {
        return Arrays.copyOf(manyStrings, manyStrings.length);  // Returns a copy to maintain encapsulation
    }

    /**
     * Converts the split strings into integers and returns them as an array.
     * If a substring cannot be converted to an integer, it defaults to 0 and prints an error message.
     * @return An array of integers converted from the split substrings.
     */
    public int[] getIntegers() {
        int[] intArray = new int[manyStrings.length];  // Array to hold integer conversions
        for (int i = 0; i < manyStrings.length; i++) {
            try {
                intArray[i] = Integer.parseInt(manyStrings[i]);  // Attempts to parse each substring as an integer
            } catch (NumberFormatException e) {
                System.err.println("Error parsing string to integer at index " + i);
                intArray[i] = 0;  // Defaults to 0 if parsing fails
            }
        }
        return intArray;
    }

    /**
     * Converts the array of split strings to a single string for easy display.
     * @return A string representation of the split substrings array.
     */
    @Override
    public String toString() {
        return Arrays.toString(manyStrings);  // Converts the array to a readable format
    }

    /**
     * Main method for testing the StringSplitter class.
     * Demonstrates usage by creating an instance, displaying the split strings, and testing modifications.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Example input to test the StringSplitter
        StringSplitter splitter = new StringSplitter("2 5 6 9", " ");
        
        System.out.println("Original Strings: " + splitter.toString());  // Displays initial split strings

        // Retrieve and display split strings
        String[] temp = splitter.getStrings();
        for (int i = 0; i < temp.length; i++) {
            System.out.print(temp[i] + "\t");
        }
        System.out.println();

        // Modify the first element in the copied array
        temp[0] = "fred";
        for (int i = 0; i < temp.length; i++) {
            System.out.print(temp[i] + "\t");
        }
        System.out.println();

        // Check if the modification affected the original array
        System.out.println("After Modification, Original Strings: " + splitter.toString());
    }
}
