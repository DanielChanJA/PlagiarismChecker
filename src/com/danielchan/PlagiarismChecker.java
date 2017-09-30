package com.danielchan;

import FileParser.InputParser;
import FileParser.SynonymParser;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 */
public class PlagiarismChecker {

    private HashMap<String, HashSet<String>> synonymWordMap;
    private InputParser fileA, fileB;

    /**
     *
     * @param synonymFilePath
     * @param inputTextA
     * @param inputTextB
     * @param tupleSize
     */
    public PlagiarismChecker(String synonymFilePath, String inputTextA, String inputTextB, int tupleSize) {
        try {
            this.fileA = new InputParser(inputTextA);
            this.fileB = new InputParser(inputTextB);
            this.synonymWordMap = new SynonymParser(synonymFilePath).generateWordMap();

            this.fileA.generateAllTuples(tupleSize);
            this.fileB.generateAllTuples(tupleSize);

        } catch(IOException e) {
            System.out.println("ERR: One of the files that you've provided is incorrect.");
        }
    }

    /**
     *
     * @return
     */
    public String calculateSimilarityScore() {
        double matching = 0;

        for (Tuple n : this.fileA.retrieveTuples()) {
            for (Tuple k : this.fileB.retrieveTuples()) {
                if (n.isSimilar(k, this.synonymWordMap)) {
                    matching++;
                    break;
                }
            }
        }

        double similarityScore = Math.round((matching / this.fileA.retrieveTuples().size()) * 100.0);
        DecimalFormat df = new DecimalFormat("###.##");

        return df.format(similarityScore);
    }
}
