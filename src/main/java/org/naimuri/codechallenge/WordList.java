package org.naimuri.codechallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordList {

private List<String> words;

    /**
     * create new empty word list
     */
    public WordList(){
        words = new ArrayList<String>();
    }

    /**
     * create new word list from @param words
     */
    public WordList(List<String> words) {
        this.words = words;
    }

    /**
     * load all wordLength words from the supplied fileURL.  If wordLength is 0 all words are loaded
     * @param fileUrl
     * @param wordLength
     */
    public void load(URL fileUrl, int wordLength){
        words.clear();
        try(Stream<String> stream = Files.lines(Paths.get(fileUrl.toURI()))){
            stream.filter(s -> wordLength<=0 || s.length()==wordLength ).forEach(s->words.add(s));
        }catch(IOException | URISyntaxException e){
            e.printStackTrace();
        }

        System.out.println("Loaded "+words.size()+" words");
    }

    /**
     * load all wordLength words from the supplied file.  If wordLength is 0 all words are loaded
     * @param file
     * @param wordLength
     */
    public void load(String file, int wordLength) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                if(wordLength<=0 || line.length()==wordLength) words.add(line);
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @return stream
     */
    public Stream<String> stream(){
        return words.stream();
    }

    /**
     * Apply @param filter to this word list and
     * @return a new filtered list
     */
    public WordList filter(Filter filter){
        return new WordList(words.stream().filter(str->filter.filter(str)).collect(Collectors.toList()));
    }

    /**
     *
     * @return number of words in list
     */
    public int size() {
        return words.size();
    }
}
