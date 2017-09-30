package FileParser;

import PlagiarismChecker.Tuple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class interacts directly with the text files that Plagiarism Checker uses.
 * It parses the files and appropriately extracts the necessary information from them.
 */
public class InputParser {

    private String filePath, fileContent;
    private List<Tuple> tuples = null;

    /**
     * The overloaded constructor for InputParser.
     *
     * @param filePath The relative/absolute path to the file.
     */
    public InputParser(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Getter for the path to the file.
     *
     * @return The path to the file.
     */
    public String getFileName() {
        return filePath;
    }

    /**
     * Getter for the tuples related to a file.
     *
     * @return A list of tuples related to the file.
     */
    public List<Tuple> retrieveTuples() {
        return this.tuples;
    }

    /**
     * Extracts the content from the file into a String, all characters are converted
     * to their lower case.
     *
     * @return
     * @throws IOException
     */
    public String extractText() throws IOException {
        this.fileContent = new String(Files.readAllBytes(Paths.get(this.filePath))).toLowerCase();
        return fileContent;
    }

    /**
     * Generates all possible tuples for a text.
     *
     * @param tupleSize The maximum number of words a tuple can hold.
     */
    public void generateAllTuples(int tupleSize) throws IOException {
        if (this.tuples == null) {
            this.tuples = new ArrayList<Tuple>();

            ArrayList<String> sanitizedText = this.sanitizeText();

            if (sanitizedText.size() == 0) {
                throw new IllegalArgumentException("No valid content found in the file that " +
                        "you provided.");
            }


            if (sanitizedText.size() < tupleSize) {
                throw new IllegalArgumentException("The tuple size that you provided is greater " +
                        "than the amount of words in the file.");
            }

            for (int i = 0; i <= sanitizedText.size() - tupleSize; i++) {
                this.tuples.add(new Tuple(tupleSize,
                        new ArrayList<String>(sanitizedText.subList(i, i + tupleSize))));
            }
        }
    }


    /**
     * This functions strips the punctuations from the text, and only extracts the words.
     * An assumption is made here that we are only concerned with the similarity between
     * words and not punctuations/numbers.
     *
     * @return Returns a list of words that are only alphabetical.
     */
    public ArrayList<String> sanitizeText() throws IOException {

        if (this.fileContent == null) {
            this.fileContent = this.extractText();
        }

        ArrayList<String> words = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\w+)");
        Matcher matcher = pattern.matcher(this.fileContent);

        while (matcher.find()) {
            words.add(matcher.group(1));
        }
        return words;
    }


}
