package org.naimuri.codechallenge;

import org.junit.jupiter.api.Test;

import java.net.URL;

public class WordListTest {

    private WordList wordList = new WordList();

    @Test
    public void testLoad(){
        //load all words
        URL fileUrl = getClass().getClassLoader().getResource("testDictionary.txt");
        wordList.load(fileUrl, 0);
        assert(wordList.size()==14);

        wordList.load(fileUrl, 3);
        assert(wordList.size()==3);
        wordList.stream().forEach(s->{assert(s.length()==3);});

        wordList.load(fileUrl, 4);
        assert(wordList.size()==7);
        wordList.stream().forEach(s->{assert(s.length()==4);});

        wordList.load(fileUrl, 5);
        assert(wordList.size()==2);
        wordList.stream().forEach(s->{assert(s.length()==5);});

        wordList.load(fileUrl, 6);
        assert(wordList.size()==1);
        wordList.stream().forEach(s->{assert(s.length()==6);});

        wordList.load(fileUrl, 7);
        assert(wordList.size()==1);
        wordList.stream().forEach(s->{assert(s.length()==7);});
    }

    @Test
    public void testFilter(){
        //load all words
        URL fileUrl = getClass().getClassLoader().getResource("testDictionary.txt");
        wordList.load(fileUrl, 0);

        //apply filter to dictionary
        WordList filtered = wordList.filter((str)->startsWith(str, 'a'));
        System.out.println("Filtered words "+filtered.size());
        assert(filtered.size()==2);
    }

    private boolean startsWith(String str, char start){
        return str.startsWith(String.valueOf(start));
    }
}
