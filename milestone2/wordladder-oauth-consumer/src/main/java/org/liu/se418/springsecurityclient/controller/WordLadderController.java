package org.liu.se418.springsecurityclient.controller;


import org.liu.se418.springsecurityclient.wordLadder.WordLadder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WordLadderController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public WordLadder getWordLadder(@RequestParam("source") String word1, @RequestParam("destination") String word2) throws IOException {
        ClassPathResource dictionary = new ClassPathResource("SmallDict.txt");
        WordLadder wordLadder = new WordLadder(dictionary.getInputStream());
        wordLadder.findWordLadder(word1, word2);
        return wordLadder;
    }

//    @RequestMapping(value = "/api/wordLadder", method = RequestMethod.POST)
//
//    public WordLadder getWordLadder(@RequestBody JSONObject words) throws IOException, JSONException {
//        ClassPathResource dictionary = new ClassPathResource("SmallDict.txt");
//        WordLadder wordLadder = new WordLadder(dictionary.getFile().getAbsolutePath());
//        String word1 = words.get("source").toString();
//        String word2 = words.get("destination").toString();
//        wordLadder.findWordLadder(word1, word2);
//        return wordLadder;
//    }

}
