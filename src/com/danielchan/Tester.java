package com.danielchan;

import FileParser.InputParser;
import FileParser.SynonymParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Tester {

    public static void main(String[] args) throws IOException {
        System.out.println(args[0]);
        InputParser ip = new InputParser(args[0]);
        System.out.println(ip.extractText());
        System.out.println(ip.sanitizeText());
//        SynonymParser sp = new SynonymParser(args[0]);
//        HashMap<String, HashSet<String>> hm = sp.generateWordMap();
    }




}
