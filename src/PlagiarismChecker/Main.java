package PlagiarismChecker;

import java.text.DecimalFormat;

/**
 * The main class that drives the entire application, instantiates PlagiarismChecker and invokes
 * methods from it.
 */
public class Main {

    public static void main(String[] args) {

        // Default to 3,
        int tupleSize = 3;
        double similarityScore = 0;

        // Limiting the number of arguments to be between 3 and 4. Exit code is -1.
        if (args.length < 3 || args.length > 4) {
            System.out.println("ERR: You've provided an incorrect number of arguments.");
            System.out.println("Please ensure that you have provided the following arguments.");
            System.out.println("1. File name for a list of synonyms.");
            System.out.println("2. File to check");
            System.out.println("3. File that you want to compare to #2.");
            System.out.println("4. (Optional) You can also supply a tuple size.");
            System.out.println("Example: java Main syn.txt text1.txt text2.txt 4");
            System.exit(-1);
        }

        // Ensures that we aren't parsing anything other than integers and that the integer that
        // was passed is positive.
        try {
            if(args.length == 4) {
                tupleSize = Integer.parseInt(args[3]);
            }

            PlagiarismChecker pgChecker = new PlagiarismChecker(args[0], args[1], args[2], tupleSize);
            similarityScore = pgChecker.calculateSimilarityScore();
        } catch(NumberFormatException e) {
            System.out.println("You provided an invalid value for the tuple size. Must be a " +
                    "positive integer.");
            System.exit(-1);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        } finally {
            DecimalFormat df = new DecimalFormat("###.##");
            System.out.println(df.format(similarityScore) + "%");
        }
    }
}
