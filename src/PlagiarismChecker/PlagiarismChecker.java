package PlagiarismChecker;

import FileParser.InputParser;
import FileParser.SynonymParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This class instantiates InputParser and SynonymParser to calculate the similarity scores
 * between the two sets of text.
 */
public class PlagiarismChecker {

    private HashMap<String, HashSet<String>> synonymWordMap;
    private InputParser fileA, fileB;

    /**
     * The main constructor that invokes methods and instantiations from InputParser and SynonymParser.
     *
     * @param synonymFilePath The relative/absolute path to the file that contains the synonym groupings.
     * @param inputTextA      One of the texts that you'd like to check against.
     * @param inputTextB      The other text that you'd like to compare with.
     * @param tupleSize       The size of a tuple, otherwise known as how many words to check at each step.
     */
    public PlagiarismChecker(String synonymFilePath, String inputTextA, String inputTextB, int tupleSize) {

        if (tupleSize <= 0) {
            throw new IllegalArgumentException("You've provided an invalid tuple size. Must be > 0");
        }

        try {
            this.fileA = new InputParser(inputTextA);
            this.fileB = new InputParser(inputTextB);
            this.synonymWordMap = new SynonymParser(synonymFilePath).generateWordMap();

            this.fileA.generateAllTuples(tupleSize);
            this.fileB.generateAllTuples(tupleSize);

        } catch (IOException e) {
            System.out.println("ERR: One of the files that you've provided is incorrect.");
            System.exit(-1);
        }
    }

    /**
     * This function calculates the similarity score between the two texts.
     * It determines if one tuple is similar to another tuple in the other text, and returns
     * a percentage of tuples that are common between them.
     *
     * @return The percentage of similar tuples between the two texts.
     */
    public double calculateSimilarityScore() {
        double matching = 0;

        for (Tuple n : this.fileA.retrieveTuples()) {
            for (Tuple k : this.fileB.retrieveTuples()) {
                if (n.isSimilar(k, this.synonymWordMap)) {
                    matching++;
                    break;
                }
            }
        }
        return Math.round((matching / this.fileA.retrieveTuples().size()) * 100.0);
    }
}
