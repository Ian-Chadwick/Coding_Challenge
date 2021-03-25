package org.naimuri.codechallenge;

import java.io.InputStream;
import java.net.URL;

public class WordSquareApp {
    public static void main(String[] args) {
        if(args.length!=2){
            System.out.println("word grid size and string of character required");
            System.out.println("e.g. 4 abcdefghijklnop");
            System.exit(0);
        }
        //check for valid number and string
        int size = 0;
        try {
            size = Integer.parseInt(args[0]);
        }catch(NumberFormatException nfe){
            System.out.println("invalid size specified "+args[0]+" - needs to be an integer");
            System.exit(0);
        }
        String characters = args[1];
        //check string length is size squared
        if(characters.length()!=Math.pow(size, 2)){
            System.out.println("invalid character string "+args[0]+" - needs to be size squared");
            System.exit(0);
        }

        String dictionaryStr = "fullDictionary.txt";

        WordSquare wordSquare = new WordSquare(size, characters);
        WordList dictionary = new WordList();
//        URL fileUrl = wordSquare.getClass().getClassLoader().getResource(dictionaryStr);
//        dictionary.load(fileUrl, size);

//        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(dictionaryStr);
//        dictionary.load(fileUrl, size);
        dictionary.load(dictionaryStr, size);

        wordSquare.setWordList(dictionary);
        wordSquare.solve();
    }
}
