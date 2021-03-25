package org.naimuri.codechallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class WordSquare {

    private WordList wordList;
    private String allowedCharacters;
    private int size = 0;
    private String[] solution;
    private List<String[]> solutions = new ArrayList<String[]>();
    private long startTime = 0;

    WordSquare(){
    }

    WordSquare(int size, String chars){
        allowedCharacters = chars;
        this.size = size;
    }

    /**
     * @return solutions list
     */
    public List<String[]> getSolutions(){
        return solutions;
    }

    /**
     * set word list
     * @param list
     */
    public void setWordList(WordList list){
        wordList = list;
        //filter words based on string of allowed characters
        wordList = wordList.filter(s->wordInAvailableChars(s, allowedCharacters));
        System.out.println("Found "+ wordList.size()+" words using "+allowedCharacters);
    }

    protected void solve(){
        startTime = System.currentTimeMillis();
        solve(wordList, allowedCharacters);
    }

    protected void solve(WordList list, String allowedChars){
        System.out.println("Solve from "+list.size()+" words, using allowed chars "+allowedChars);

        //find first word to try
        list.stream().forEach(firstword->{
//            System.out.println("try first word "+firstword);
            solution = new String[size];
            Arrays.fill(solution, null);
            solution[0]=firstword;

            //remove current word, trim allowed chars and filter list
            String chars = trimAllowedChars(allowedChars, firstword);
            WordList filteredList = list.filter(s->filterWord(s, firstword)).filter(s->wordInAvailableChars(s, chars));

            solveRow(1, filteredList, chars);
        });
    }

    protected void solveRow(int row, WordList mainList, String chars){
        String start = "";
        for(int i=0; i<row; i++){
            start += solution[i].substring(row,row+1);
        }
         String rowstartStr = start;//TODO bodge to pass final string to lambda
        //for next word filter dictionary to words starting with rowstart and in available chars
        //if at least 1 word available loop over each word and move to next row
        WordList rowList = mainList.filter(s->startsWith(s, rowstartStr)).filter(s->wordInAvailableChars(s, chars));
        if(rowList.size()==0){
            return;
        }else{
            if(row+1<size) {
                rowList.stream().forEach(s-> {
                    solution[row]=s;
                    solveRow(row+1, mainList, trimAllowedChars(chars, s));
                });
            }else{
                //last row so if we only have 1 word we hve a solution!!
                if(rowList.size()==1){
                    rowList.stream().forEach(s->solution[row]=s);
                    printSolution();
                    solutions.add(solution);
                    return;
                }
            }
        }
    }

    private void printSolution(){
        System.out.println("*** Solution:");
        for(String s:solution) System.out.println(s);
        System.out.println("***");
        System.out.println("found in "+(System.currentTimeMillis()-startTime)+"ms");
    }

    /**
     * Remove chars in word from allowedChars
     * @param allowedChars
     * @param word
     * @return new string of allowedChars
     */
    protected String trimAllowedChars(String allowedChars, String word) {
        //remove chars in word from allowedChars
        StringBuilder sb = new StringBuilder(allowedChars);

        Stream<Character> wordCharStream = word.chars().mapToObj(c -> (char) c);
        wordCharStream.forEach(c->{
            int index = sb.toString().indexOf(c);
            if(index!=-1){
                sb.deleteCharAt(index);
            }
        });
        return sb.toString();
    }

    /**
     * Filter single word
     * @param str
     * @param word
     * @return false if word equals str
     */
    protected boolean filterWord(String str, String word){
        return !word.equals(str);
    }

    /**
     * @param word
     * @param start
     * @return true if word starts with start char
     */
    protected boolean startsWith(String word, char start){
        return word.startsWith(String.valueOf(start));
    }

    /**
     * @param word
     * @param start
     * @return true if word starts with start string
     */
    protected boolean startsWith(String word, String start){
        return word.startsWith(start);
    }

    /**
     * @param word
     * @param start
     * @return true if word starts with any char in start
     */
    protected boolean startsWith(String word, char[] start){
        for(char c : start){
            if(word.startsWith(String.valueOf(c))){
                return true;
            }
        }
        return false;
    }

    /**
     * Test is word can be constructed from allowedChars
     * @param word
     * @param allowedChars
     * @return
     */
    protected boolean wordInAvailableChars(String word, String allowedChars){
        StringBuilder sb = new StringBuilder(word);
        //use loop rather than stream as we want to break when we have a match rather than iterate over remaining chars
        //java 8 streams does not have a doWhile or similar, need Java 9+
        for(char c:allowedChars.toCharArray()){
            int index = sb.toString().indexOf(c);
            if(index !=-1){
                sb.deleteCharAt(index);
                if(sb.length()==0) return true;
            }
        }
        return sb.length()==0;
    }
}
