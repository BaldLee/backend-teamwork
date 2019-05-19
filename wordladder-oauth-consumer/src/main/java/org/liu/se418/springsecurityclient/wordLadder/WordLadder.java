package org.liu.se418.springsecurityclient.wordLadder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;


@JsonIgnoreProperties
public class WordLadder {
    private Set<String> dictionary = new HashSet<String>();
    private Set<String> seenWords = new HashSet<String>();
    public String message;
    public List<String> wordLadder;

    private void getDictionary(String fileName) throws IOException {
        File inFile;
        inFile = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inFile));
        String word;
        while ((word = bufferedReader.readLine()) != null) {
            dictionary.add(word);
        }
        bufferedReader.close();
    }

    private void getDictionary(InputStream inputStream) throws IOException {
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String word;
        while ((word = bufferedReader.readLine()) != null) {
            dictionary.add(word);
        }
        bufferedReader.close();
    }


    public String getMessage(){
        return message;
    }

    public List<String> getWordLadder(){
        return wordLadder;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setWordLadder(List<String> wordLadder) {
        this.wordLadder = wordLadder;
    }


    public WordLadder(String fileName) throws IOException {
        // d.forEach(word -> word.toLowerCase());
        getDictionary(fileName);
    }

    public WordLadder(InputStream inputStream) throws IOException {
        // d.forEach(word -> word.toLowerCase());
        getDictionary(inputStream);
    }

    private List<String> oneLetterDifferFrom(String word) {
        List<String> words = new ArrayList<String>();
        for (int i = 0; i < word.length(); i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                if (word.charAt(i) != j) {
                    char[] letters = word.toCharArray();
                    letters[i] = j;
                    String wordCandidate = new String(letters);
                    if (dictionary.contains(wordCandidate) && !seenWords.contains(wordCandidate)) {
                        words.add(wordCandidate);
                    }
                }
            }
        }
        return words;
    }

    public void findWordLadder(String word1, String word2) {
        //not in the dictionary
        if(!dictionary.contains(word1)){
            message = word1 + " not in the dictionary.";
            wordLadder = new ArrayList<String>();
            return;
        }

        if(!dictionary.contains(word2)){
            message = word2 + " not in the dictionary.";
            wordLadder = new ArrayList<String>();
            return;
        }


        // different lengths
        if (word1.length() != word2.length()) {
            message = word1 + " and " + word2 + " have different lengths.";
            wordLadder = new ArrayList<String>();
            return;
        }

        wordLadder = new ArrayList<String>();
        wordLadder.add(word1);
        // same words
        if (word1.equals(word2)) {
            message = "Succeed!";
            return;
        }

        // general situation
        Queue<List<String>> wordLadderCandidates = new ArrayDeque<List<String>>();
        wordLadderCandidates.add(wordLadder);
        List<String> currentWordLadder = new ArrayList<String>();
        seenWords.add(word1);
        while (!wordLadderCandidates.isEmpty()) {
            currentWordLadder = wordLadderCandidates.poll();
            String lastWord = currentWordLadder.get(currentWordLadder.size() - 1);
            List<String> wordCandidates = oneLetterDifferFrom(lastWord);
            for (String wordCandidate : wordCandidates) {
                if (wordCandidate.equals(word2)) {
                    currentWordLadder.add(wordCandidate);
                    message = "Succeed!";
                    wordLadder = currentWordLadder;
                    return;
                }
                List<String> currentWordLadderCopy = new ArrayList<String>();
                currentWordLadderCopy.addAll(currentWordLadder);
                currentWordLadderCopy.add(wordCandidate);
                wordLadderCandidates.add(currentWordLadderCopy);
            }
        }
        message = "No word ladder exists between the two words.";
        wordLadder = new ArrayList<String>();
    }
}