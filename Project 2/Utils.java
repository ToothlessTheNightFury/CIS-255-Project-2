import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * MIT License
 *
 * Copyright (c) 2021 Benson Yee
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * The Utils class contains a number of useful functions
 * common to programming. The full list of functions is below.
 * It supports int, double, float, and String data types.
 *
 * This class is meant to add functionality as well as serve as a
 * useful debugging tool. To use this class, type "Utils." followed
 * by the function name.
 *
 * Example:
 * Utils.promptNum();
 *
 * Here is the full list of functions:
 *  - fileRead
 *  - fileWrite
 *  - getDateTime
 *  - getVersion
 *  - initArray
 *  - menu
 *  - numToStringNoTrailingZeros
 *  - pause
 *  - printArray
 *  - printCredits
 *  - printMsg
 *  - printTitle
 *  - printVar
 *  - promptNum
 *  - promptNumMin
 *  - promptNumMax
 *  - promptNumPos
 *  - promptNumNeg
 *  - promptNumRange
 *  - promptStr
 *  - promptYN
 *
 * @author Benson Yee
 * @since 2021-01-23
 * @version 1.2.2
 */

public class Utils {

    public static String VERSION = "1.2.2";

    // TODO: static bool fileExists (String filename)

    /**
     * Reads a file and stores its data to a String array.
     *
     * @param  fileName the file name
     * @param  arr      the String array to store the data into
     * @return the number of lines read from the file
     * @throws FileNotFoundException if the file does not exist,
     *         is a directory rather than a regular file,
     *         or for some other reason cannot be opened for
     *         reading.
     */
    public static int fileRead (String fileName, String[] arr) {

        int index = 0;

        try {
            File myFile = new File (fileName);
            Scanner scanner = new Scanner (myFile);
            initArray(arr);

            while (scanner.hasNextLine() && index != arr.length) {
                arr[index++] = scanner.nextLine();
            }

            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.printf("ERR: Unable to open file '%s'. Is the file in the root folder of this program?\n", fileName);
            return -1;
        }
        return index;
    }

    /**
     * Writes a String array to a file. Each String element
     * is written on its own line.
     *
     * @param  fileName    the file name
     * @param  arr         the String array to write with
     * @return true if write was successful, false otherwise
     * @throws IOException if unable to write to file
     */
    public static boolean fileWrite (String fileName, String[] arr) {

        try {
            File myFile;
            myFile = new File (fileName);

            if (myFile.createNewFile()) {
                System.out.printf("File '%s' created.\n", myFile.getName());
            }

            else {
                System.out.printf("ERR: File '%s' already exists. ", myFile.getName());

                if (!promptYN("Would you like to overwrite (Y/N)?\n")) {
                    return false;
                }
            }

            FileWriter myWriter = new FileWriter(fileName);

            for (int i = 0; i < arr.length; i++) {
                myWriter.write(arr[i] + "\n");
            }

            myWriter.close();
            System.out.printf("File '%s' written to.\n\n", myFile.getName());

        } catch (IOException e) {

            System.out.printf("ERR: Unable to create file '%s'.\n", fileName);
            return false;
        }
        return true;
    }




    /**
     * Returns the date and time and returns it as a String. Format
     * is dd-MM-yyy HH:mm:ss, with HH in 24-hour time.
     *
     * Example:
     * 25-Nov-2020 10:09:32
     *
     * @return the date / time as a String
     */
    public static String getDateTime() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyy HH:mm:ss");
        return myDateObj.format(myFormatObj);
    }




    /**
     * Returns the version of the Utils program. Format
     * is Utils MAJOR.MINOR.PATCH, following Sematic
     * Versioning (https://semver.org/).
     *
     * Example:
     * Utils 1.2.0
     *
     * @return the version of Utils as a String
     */
    public static String getVersion() {
        return "Utils " + VERSION;
    }




    /**
     * Initializes an int array to 0.
     *
     * @param arr     the array to initialize
     */
    public static void initArray (int[] arr) {
        initArray(arr, 0);
    }

    /**
     * Initializes an int array to a given value.
     *
     * @param arr     the array to initialize
     * @param value   the value to initialize to
     */
    public static void initArray (int[] arr, int value) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = value;
        }
    }




    /**
     * Initializes a double array to 0.
     *
     * @param arr     the array to initialize
     */
    public static void initArray (double[] arr) {
        initArray(arr, 0);
    }

    /**
     * Initializes a double array to a given value.
     *
     * @param arr     the array to initialize
     * @param value   the value to initialize to
     */
    public static void initArray (double[] arr, double value) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = value;
        }
    }




    /**
     * Initializes a float array to 0.
     *
     * @param arr     the array to initialize
     */
    public static void initArray (float[] arr) {
        initArray(arr, 0);
    }

    /**
     * Initializes a float array to a given value.
     *
     * @param arr     the array to initialize
     * @param value   the value to initialize to
     */
    public static void initArray (float[] arr, float value) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = value;
        }
    }




    /**
     * Initializes a String array to an empty String.
     *
     * @param arr     the array to initialize
     */
    public static void initArray (String[] arr) {
        initArray(arr, "");
    }

    /**
     * Initializes a String array to a given value.
     *
     * @param arr     the array to initialize
     * @param value   the value to initialize to
     */
    public static void initArray (String[] arr, String value) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = value;
        }
    }




    /**
     * Prints a menu with no title to the user. Prompts
     * user to input number for their selection. Rejects
     * non-numbers or numbers outside the selection
     * range.
     *
     * Example:
     * 1. Start Game
     * 2. Settings
     * 3. Quit
     *
     * Requires a String array containing the possible options.
     *
     * Example:
     * String options = {
     *     "Start Game",
     *     "Settings",
     *     "Quit"
     * }
     *
     * @param options the String array containing the possible options
     * @return the user's choice
     */
    public static int menu (String[] options) {
        return menu("", options);
    }

    /**
     * Prints a menu with a title to the user. Prompts
     * user to input number for their selection. Rejects
     * non-numbers or numbers outside the selection
     * range.
     *
     * Example:
     * BATTLESHIP
     * 1. Start Game
     * 2. Settings
     * 3. Quit
     *
     * Requires a String array containing the possible options.
     *
     * Example:
     * String options = {
     *     "Start Game",
     *     "Settings",
     *     "Quit"
     * }
     *
     * @param title   the title of the menu
     * @param options the String array containing the possible options
     * @return the user's choice
     */
    public static int menu (String title, String[] options) {

        final int MIN_CHOICE = 1;
        final int MAX_CHOICE = options.length;

        printMsg(title + "\n");

        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d. %s\n", i + 1, options[i]);
        }

        int choice = (int) promptNumRange(MIN_CHOICE, MAX_CHOICE);

        System.out.println();
        return choice;
    }




    /**
     * Converts a double into a string. Removes trailing
     * zeros, if any.
     *
     * @param num   the number to convert
     * @return the number as a string, no trailing zeros
     */
    public static String numToStringNoTrailingZeros (double num) {
        return String.valueOf(num).replaceFirst("\\.0*$|(\\.\\d*?)0+$", "$1");
    }

    /**
     * Converts a float into a string. Removes trailing
     * zeros, if any.
     *
     * @param num   the number to convert
     * @return the number as a string, no trailing zeros
     */
    public static String numToStringNoTrailingZeros (float num) {
        return String.valueOf(num).replaceFirst("\\.0*$|(\\.\\d*?)0+$", "$1");
    }




    /**
     * Pauses the console. Prompts the user to press Enter
     * to continue. Waits for user to press Enter.
     */
    public static void pause() {
        pause("Press ENTER to continue . . . \n");
    }

    /**
     * Pauses the console. Prints message to console.
     * Waits for user to press Enter.
     *
     * @param message the message to prompt the user
     */
    public static void pause (String message) {
        promptStr(message);
    }




    /**
     * Prints an int array with no title.
     *
     * Example:
     * Index     Value
     * 0         1
     * 1         2
     * 2         3
     *
     * @param arr     the array to print
     */
    public static void printArray (int[] arr) {
        printArray("", arr);
    }

    /**
     * Prints an int array with a title.
     *
     * Example:
     * [arrayInt]
     * Index     Value
     * 0         1
     * 1         2
     * 2         3
     *
     * @param title   the title to print
     * @param arr     the array to print
     */
    public static void printArray (String title, int[] arr) {

        if (arr.length < 0) {
            return;
        }

        printMsg(title + "\n");

        System.out.printf("%-10s%s\n", "Index", "Value");

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%-10d%d\n", i, arr[i]);
        }

        System.out.println();
    }




    /**
     * Prints a double array with no title.
     *
     * Example:
     * Index     Value
     * 0         23231.1
     * 1         17.56784
     * 2         11.35782
     *
     * @param arr     the array to print
     */
    public static void printArray (double[] arr) {
        printArray("", arr);
    }

    /**
     * Prints a double array with a title.
     *
     * Example:
     * [arrayDouble]
     * Index     Value
     * 0         23231.1
     * 1         17.56784
     * 2         11.35782
     *
     * @param title   the title to print.
     * @param arr     the array to print
     */
    public static void printArray (String title, double[] arr) {

        if (arr.length < 0) {
            return;
        }

        printMsg(title + "\n");

        System.out.printf("%-10s%s\n", "Index", "Value");

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%-10d%s\n", i, numToStringNoTrailingZeros(arr[i]));
        }

        System.out.println();
    }




    /**
     * Prints a float array with no title.
     *
     * Example:
     * Index     Value
     * 0         1.152313
     * 1         2.234034
     * 2         3.504182
     *
     * @param arr     the array to print
     */
    public static void printArray (float[] arr) {
        printArray("", arr);
    }

    /**
     * Prints a float array with a title.
     *
     * Example:
     * [arrayFloat]
     * Index     Value
     * 0         1.152313
     * 1         2.234034
     * 2         3.504182
     *
     * @param title   the title to print
     * @param arr     the array to print
     */
    public static void printArray (String title, float[] arr) {

        if (arr.length < 0) {
            return;
        }

        printMsg(title + "\n");

        System.out.printf("%-10s%s\n", "Index", "Value");

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%-10d%s\n", i, numToStringNoTrailingZeros(arr[i]));
        }

        System.out.println();
    }




    /**
     * Prints a String array with no title.
     *
     * Example:
     * Index     Value
     * 0         This is a string
     * 1         This is a second string
     * 2         This is a third string
     *
     * @param arr     the array to print
     */
    public static void printArray (String[] arr) {
        printArray("", arr);
    }

    /**
     * Prints a String array with a title.
     *
     * Example:
     * [arrayString]
     * Index     Value
     * 0         This is a string
     * 1         This is a second string
     * 2         This is a third string
     *
     * @param title   the title to print
     * @param arr     the array to print
     */
    public static void printArray (String title, String[] arr) {

        if (arr.length < 0) {
            return;
        }

        printMsg(title + "\n");

        System.out.printf("%-10s%s\n", "Index", "Value");

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%-10d%s\n", i, arr[i]);
        }

        System.out.println();
    }




    /**
     * Prints credits. Includes author name and end time
     * of program.
     *
     * Example:
     * Programmed by Benson Yee
     * End Time: 25-Nov-2020 22:37:18
     *
     * @param authorName the author's name
     */
    public static void printCredits (String authorName) {

        System.out.printf("End Time: %s\n", getDateTime());
        System.out.printf("Programmed by %s\n", authorName);
        System.out.printf("Powered by " + getVersion() + "\n");
    }




    /**
     * Prints a message, then a line break. If message
     * is empty, does not print line break.
     *
     * @param message the message to print
     */
    public static void printMsg (String message) {

        // Prints the message if not blank
        System.out.printf("%s", message.isEmpty() || message.equals("\n") ? "" : (message));
    }




    /**
     * Prints the program name, the author name, and the
     * start time of the program.
     *
     * Example:
     * Template v1.0.0
     * Programmed by Benson Yee
     * Start Time: 25-Nov-2020 22:36:53
     *
     * @param programName the program's name
     * @param authorName  the author's name
     */
    public static void printTitle (String programName, String authorName) {

        System.out.printf("\n%s\n", programName);
        System.out.printf("Programmed by %s\n", authorName);
        System.out.printf("Start Time: %s\n\n", getDateTime());
    }




    /**
     * Prints an int variable's name and value. Useful
     * for debugging or checking a variable's value in
     * a loop.
     *
     * Example:
     * i: 1
     * i: 2
     * i: 3
     *
     * @param varName the variable's name
     * @param var     the variable
     */
    public static void printVar (String varName, int var) {
        System.out.printf("%s: %d\n", varName, var);
    }

    /**
     * Prints a double variable's name and value. Useful
     * for debugging or checking a variable's value
     * in a loop.
     *
     * Example:
     * numD: 2.5
     * numD: 2.37
     * numD: 1.04
     *
     * @param varName the variable's name
     * @param var     the variable
     */
    public static void printVar (String varName, double var) {
        System.out.printf("%s: %s\n", varName, numToStringNoTrailingZeros(var));
    }

    /**
     * Prints a float variable's name and value. Useful
     * for debugging or checking a variable's value
     * in a loop.
     *
     * Example:
     * numF: 1024
     * numF: 2048
     * numF: 4096
     *
     * @param varName the variable's name
     * @param var     the variable
     */
    public static void printVar (String varName, float var) {
        System.out.printf("%s: %s\n", varName, numToStringNoTrailingZeros(var));
    }

    /**
     * Prints a String variable's name and value. Useful
     * for debugging or checking a variable's value in a
     * loop.
     *
     * Example:
     * str1: this is a string
     * str1: THIS IS A STRING
     * str1: THIS
     *
     * @param varName the variable's name
     * @param var     the variable
     */
    public static void printVar (String varName, String var) {
        System.out.printf("%s: %s\n", varName, var);
    }




    /**
     * Prompts the user to input a double with no message.
     * Rejects any non-numeric values.
     *
     * @return the double
     */
    public static double promptNum() {
        return promptNum("");
    }

    /**
     * Prompts the user to input a double with a message.
     * Rejects any non-numeric values.
     *
     * @param message the message to prompt the user
     * @return the double
     */
    public static double promptNum (String message) {

        printMsg(message);

        double num;
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextDouble()) {

            // If user does not input a number
            System.out.print("\nPlease input a number.\n");
            scanner = new Scanner(System.in);
        }

        num = scanner.nextDouble();
        return num;
    }




    /**
     * Prompts the user to input a double with a message.
     * Rejects any non-numeric values or numbers less than
     * minimum possible input.
     *
     * @param min     the minimum possible input
     * @return the double
     */
    public static double promptNumMin (double min) {
        return promptNumMin(min, "");
    }

    /**
     * Prompts the user to input a double with a message.
     * Rejects any non-numeric values or numbers less than
     * minimum possible input.
     *
     * @param min     the minimum possible input
     * @param message the message to prompt the user
     * @return the double
     */
    public static double promptNumMin (double min, String message) {

        printMsg(message);
        double num;
        boolean validInput;

        do {
            num = promptNum();
            validInput = num >= min;

            if (!validInput) {
                System.out.printf("\nPlease input a number greater than or equal to %s.\n", numToStringNoTrailingZeros(min));
            }

        } while (!validInput);

        return num;
    }




    /**
     * Prompts the user to input a double with a message.
     * Rejects any non-numeric values or numbers greater than
     * maximum possible input.
     *
     * @param max     the minimum possible input
     * @return the double
     */
    public static double promptNumMax (double max) {
        return promptNumMax(max, "");
    }

    /**
     * Prompts the user to input a double with a message.
     * Rejects any non-numeric values or numbers greater than
     * maximum possible input.
     *
     * @param max     the minimum possible input
     * @param message the message to prompt the user
     * @return the double
     */
    public static double promptNumMax (double max, String message) {

        printMsg(message);
        double num;
        boolean validInput;

        do {
            num = promptNum();
            validInput = num <= max;

            if (!validInput) {
                System.out.printf("\nPlease input a number less than or equal to %s.\n", numToStringNoTrailingZeros(max));
            }

        } while (!validInput);

        return num;
    }




    /**
     * Prompts the user to input a double with a message.
     * Rejects any non-numeric values or non-positive
     * numbers.
     *
     * @return the double
     */
    public static double promptNumPos() {
        return promptNumPos("");
    }

    /**
     * Prompts the user to input a double with a message.
     * Rejects any non-numeric values or non-positive
     * numbers.
     *
     * @param message the message to prompt the user
     * @return the double
     */
    public static double promptNumPos (String message) {

        printMsg(message);
        double num;
        boolean validInput;

        do {
            num = promptNum();
            validInput = num > 0;

            if (!validInput) {
                System.out.printf("\nPlease input a positive number.\n");
            }

        } while (!validInput);

        return num;
    }




    /**
     * Prompts the user to input a double with a message.
     * Rejects any non-numeric values or non-negative
     * numbers.
     *
     * @return the double
     */
    public static double promptNumNeg() {
        return promptNumNeg("");
    }

    /**
     * Prompts the user to input a double with a message.
     * Rejects any non-numeric values or non-negative
     * numbers.
     *
     * @param message the message to prompt the user
     * @return the double
     */
    public static double promptNumNeg (String message) {

        printMsg(message);
        double num;
        boolean validInput;

        do {
            num = promptNum();
            validInput = num < 0;

            if (!validInput) {
                System.out.printf("\nPlease input a negative number.\n");
            }

        } while (!validInput);

        return num;
    }




    /**
     * Prompts the user to input a double with a message
     * within a specified range.
     *
     * @param min     the min, inclusive
     * @param max     the max, inclusive
     * @return the int
     */
    public static double promptNumRange (double min, double max) {
        return promptNumRange(min, max, "");
    }

    /**
     * Prompts the user to input a double with a message
     * within a specified range.
     *
     * @param min     the min, inclusive
     * @param max     the max, inclusive
     * @param message the message to prompt the user
     * @return the int
     */
    public static double promptNumRange (double min, double max, String message) {

        printMsg(message);
        double num;
        boolean validInput;

        do {
            num = promptNum();
            validInput = num >= min && num <= max;

            if (!validInput)
                System.out.printf("\nPlease input a number between %s and %s.\n", numToStringNoTrailingZeros(min), numToStringNoTrailingZeros(max));

        } while (!validInput);

        return num;
    }




    /**
     * Prompts the user to input a String with no message.
     *
     * @return the string
     */
    public static String promptStr() {
        return promptStr("");
    }

    /**
     * Prompts the user to input a string with a message.
     *
     * @param message the message to print
     * @return the string
     */
    public static String promptStr (String message) {

        printMsg(message);
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }



    /**
     * Prompts the user to input Y or N with no message.
     * Ignores case. Returns true if Y or false if N.
     *
     * @return true if Y, false otherwise
     */
    public static boolean promptYN() {
        return promptYN ("");
    }

    /**
     * Prompts the user to input Y or N with a message.
     * Ignores case. Returns true if Y or false if N.
     *
     * @param message the message to prompt the user
     * @return true if Y, false otherwise
     */
    public static boolean promptYN (String message) {

        printMsg(message);
        String line;
        boolean validInput;

        do {
            line = promptStr();
            validInput = line.equalsIgnoreCase("y") || line.equalsIgnoreCase("n");

            if (!validInput)
                System.out.print("\nPlease input either 'Y' or 'N'.\n");

        } while (!validInput);

        return line.equalsIgnoreCase("y");
    }
}
