package com.danielchan;

/**
 * The main class that drives the entire application
 */
public class PlagiarismChecker {

    public static void main(String[] args) {

        // Default to 3,
        int tupleSize = 3;

        // Limiting the number of arguments to be between 3 and 4. Exit code is -1.
        if (args.length < 3 || args.length > 4) {
            System.out.println("ERR: You've provided an incorrect number of arguments.");
            System.out.println("Please ensure that you have provided the following arguments.");
            System.out.println("1. File name for a list of synonyms.");
            System.out.println("2. File to check");
            System.out.println("3. File that you want to compare to #2.");
            System.out.println("4. (Optional) You can also supply a tuple size.");
            System.out.println("Example: java PlagiarismChecker syn.txt text1.txt text2.txt 4");
            System.exit(-1);
        }

        // Ensures that we aren't parsing anything other than integers and that the integer that was passed is positive.
        try {
            if(args.length == 4 && Integer.parseInt(args[3]) > 0) {
                tupleSize = Integer.parseInt(args[3]);
            } else {
                throw new IllegalArgumentException("Negative Integer");
            }
        } catch(NumberFormatException e) {
            System.out.println("Uh-oh, you've provided an illegal argument for the tuple value. Must be a positive integer.");
        } catch(IllegalArgumentException e) {
            System.out.println("Uh-oh, you've provided an invalid integer. We need a positive integer!");
        }
    }
}
