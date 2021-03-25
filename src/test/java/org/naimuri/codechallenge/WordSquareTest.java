package org.naimuri.codechallenge;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordSquareTest {

    @Test
    public void testSolve() {
        String[] expected = new String[]{"abcd", "bcde", "cdef", "defg"};
        WordList wordList = new WordList();
        URL fileUrl = getClass().getClassLoader().getResource("testDictionary.txt");
        wordList.load(fileUrl, 4);
        WordSquare wordSquare = new WordSquare(4, "abcdbcdecdefdefg");
        wordSquare.setWordList(wordList);
        wordSquare.solve();

        System.out.println(Arrays.toString(wordSquare.getSolutions().get(0)));
        for(int i=0; i<wordSquare.getSolutions().get(0).length; i++) {
            System.out.println("Compare " +wordSquare.getSolutions().get(0)[i]+" with "+expected[i]);
            assert (wordSquare.getSolutions().get(0)[i].equals(expected[i]));
        }
    }

    @Test
    public void testWordStartsWithCharFilter() {
        WordSquare wordSquare = new WordSquare();
        assert(wordSquare.startsWith("abcd", 'a'));
    }

    @Test
    public void testWordStartsWithCharsFilter() {
        WordSquare wordSquare = new WordSquare();
        assert(wordSquare.startsWith("bcde", new char[]{'a', 'b', 'c'}));
    }

    @Test
    public void testWordStartsWithStringFilter() {
        WordSquare wordSquare = new WordSquare();
        assert(wordSquare.startsWith("bcde", "bc"));
        assert(!wordSquare.startsWith("bcde", "ab"));
    }

    @Test
    public void testWordInAvailableCharsFilter(){
        WordSquare wordSquare = new WordSquare();
        assert(wordSquare.wordInAvailableChars("abcd", "abcdef"));
    }

    @Test
    public void trimAllowedChars(){
        WordSquare wordSquare = new WordSquare();
        assert("alle".equals(wordSquare.trimAllowedChars("allowed", "word")));
    }
}
