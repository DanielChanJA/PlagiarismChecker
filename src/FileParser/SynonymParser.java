package FileParser;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * This class extends InputParser. It's primary role is to read the synonym file
 * that is provided and generate a mapping of words to their synonyms.
 */
public class SynonymParser extends InputParser {

    /**
     * The constructor that invokes the constructor from it's parent class, InputParser.
     *
     * @param filePath The relative/absolute path to the file.
     */
    public SynonymParser(String filePath) {
        super(filePath);
    }

    /**
     * This method generates a mapping of a word to it's set of synonyms.
     *
     * @return HashMap containing where the key is the word and the value is the synonym group.
     */
    public HashMap<String, HashSet<String>> generateWordMap() {

        HashMap<String, HashSet<String>> wordMapping = new HashMap<>();

        try (Stream<String> stream = Files.lines(Paths.get(this.getFileName()))) {
            stream.forEach(line -> {
                List<String> words = Arrays.asList(line.toLowerCase().split("\\s+"));
                HashSet<String> synonyms = new HashSet<>(words);

                // Map each word that we find in a set to it's own synonym group.
                // We also assume here that the word only belongs to one synonym group.
                words.forEach(word -> {
                    wordMapping.put(word, synonyms);
                });
            });
        } catch (IOException e) {
            System.out.println("ERR: File was not found, please check the filename/path that you've provided.");
            System.exit(-1);
        }
        return wordMapping;
    }

}
