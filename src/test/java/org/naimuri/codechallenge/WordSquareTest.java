package org.naimuri.codechallenge;

import org.junit.jupiter.api.Test;

public class WordSquareTest {

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
