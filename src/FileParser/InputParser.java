package FileParser;

import com.danielchan.Tuple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Daniel Chan on 29/9/17.
 */
public class InputParser {

    private String filePath, fileContent;
    private List<Tuple> tuples = null;

    // Empty constructor.
    public InputParser() {
    }

    /**
     *
     * @param fileName
     */
    public InputParser(String fileName) {
        this.filePath = fileName;
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return filePath;
    }

    public List<Tuple> retrieveTuples() {
        return this.tuples;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public String extractText() throws IOException {
        this.fileContent = new String(Files.readAllBytes(Paths.get(this.filePath)));
        return fileContent;
    }

    /**
     *
     * @param tupleSize
     * @return
     */
    public void generateAllTuples(int tupleSize) throws IOException {
        if(this.tuples == null) {
            this.tuples = new ArrayList<Tuple>();

            ArrayList<String> sanitizedText = this.sanitizeText();

            if (sanitizedText.size() == 0) {
                throw new IllegalArgumentException("No valid content found in the file that you provided.");
            }


            if (sanitizedText.size() < tupleSize) {
                throw new IllegalArgumentException("The tuple size that you provided is greater than " +
                        "the amount of words in the file.");
            }

            for (int i = 0; i <= sanitizedText.size() - tupleSize; i++) {
                this.tuples.add(new Tuple(tupleSize,
                        new ArrayList<String>(sanitizedText.subList(i, i + tupleSize))));
            }
        }
    }


    /**
     *
     * @return
     */
    public ArrayList<String> sanitizeText() throws IOException {

        if(this.fileContent == null) {
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
