package com.danielchan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Tuple {

    private int size;
    private String[] words;

    /**
     *
     * @param size
     */
    public Tuple(int size) {
        this.size = size;
    }

    /**
     *
     * @param size
     * @param words
     */
    public Tuple(int size, ArrayList<String> words) {
        if (words.size() != size) {
            throw new IllegalArgumentException("The number of words is not equal to the size.");
        }

        this.words = new String[size];
        this.size = size;

        for(int i = 0; i < size; i++) {
            this.words[i] = words.get(i);
        }
    }

    /**
     *
     * @return
     */
    public String[] getTupleWords() {
        return this.words;
    }

    /**
     *
     * @param t
     * @param synonymMap
     * @return
     */
    public boolean isSimilar(Tuple t, HashMap<String, HashSet<String>> synonymMap) {

        if (synonymMap.isEmpty()) {
            throw new IllegalArgumentException("Synonym map is empty when it shouldn't be.");
        }

        for(int i = 0; i < this.words.length; i++) {
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
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < this.words.length; i++) {
            sb.append(this.words[i]);
            sb.append(" ");
        }
        return sb.toString();
    }



}
