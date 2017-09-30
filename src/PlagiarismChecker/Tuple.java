package PlagiarismChecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The tuple object holds a set of words, based on the tuple size provided or if it's
 * not provided, will use the default value of 3.
 */
public class Tuple {

    private String[] words;

    /**
     * The primary constructor for a Tuple, validates that the size we're passed is
     * equal to the number of words.
     *
     * @param size  The number of words that we'd like to store in the Tuple.
     * @param words The words that we want to store in the Tuple.
     */
    public Tuple(int size, ArrayList<String> words) {
        if (words.size() != size) {
            throw new IllegalArgumentException("The number of words is not equal to the size.");
        }

        this.words = new String[size];

        for (int i = 0; i < size; i++) {
            this.words[i] = words.get(i);
        }
    }

    /**
     * This function checks if a tuple is similar to another, similar in the sense that it would
     * check if there are combinations of a tuple that could match with another (if a word was
     * replaced with a synonym).
     *
     * @param t          The tuple that we're comparing to.
     * @param synonymMap The mapping of the words to its synonym group.
     * @return A boolean that determines if it is similar or if it's not.
     */
    public boolean isSimilar(Tuple t, HashMap<String, HashSet<String>> synonymMap) {

        if (synonymMap.isEmpty()) {
            throw new IllegalArgumentException("Synonym map is empty when it shouldn't be.");
        }

        for (int i = 0; i < this.words.length; i++) {
            // Checking that they do not match.
            if (!this.words[i].equals(t.words[i])) {
                if (synonymMap.containsKey(this.words[i]) && synonymMap.containsKey(t.words[i]) &&
                        synonymMap.get(this.words[i]) == synonymMap.get(t.words[i])) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * The toString representation of a Tuple, concatenating the words with a space in between them
     * to create a human-readable text.
     *
     * @return The words stored in a Tuple.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.words.length; i++) {
            sb.append(this.words[i]);
            sb.append(" ");
        }
        return sb.toString();
    }
}
