package org.liu.se418.springsecurityclient;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.liu.se418.springsecurityclient.wordLadder.WordLadder;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class WordLadderTest {
    private WordLadder wordLadder;

    @Before
    public void before() throws IOException {
        ClassPathResource dictionary = new ClassPathResource("SmallDict.txt");
        wordLadder = new WordLadder(dictionary.getFile().getAbsolutePath());
    }

    @Test
    public void testGetWordLadder() {
        wordLadder.findWordLadder("dog", "cat");
        Assert.assertEquals("From dog to cat should return List of length 4.", wordLadder.getWordLadder().size(), 4);
        Assert.assertTrue("message should be Succeed!", wordLadder.getMessage().equals("Succeed!"));

        wordLadder.findWordLadder("code", "data");
        Assert.assertEquals("From code to data should return List of length 5.", wordLadder.getWordLadder().size(), 5);
        Assert.assertTrue("message should be Succeed!", wordLadder.getMessage().equals("Succeed!"));

        wordLadder.findWordLadder("code1", "data1");
        Assert.assertEquals("From code1 to data1 should return null with message word not in dict.", wordLadder.getWordLadder().size(), 0);
        Assert.assertTrue("message should be fail!", wordLadder.getMessage().equals("code1 not in the dictionary."));

        wordLadder.findWordLadder("code", "code");
        Assert.assertEquals("From code1 to data1 should return List of length 1.", wordLadder.getWordLadder().size(), 1);

        wordLadder.findWordLadder("code", "cat");
        Assert.assertEquals("From code1 to data1 should return null with message that word lengths differ.", wordLadder.getWordLadder().size(), 0);
        Assert.assertTrue("message should be fail!", wordLadder.getMessage().equals("code and cat have different lengths."));

    }

    @Test
    public void testOneLetterDifferFrom() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method _oneLetterDifferFrom = WordLadder.class.getDeclaredMethod("oneLetterDifferFrom", String.class);
        _oneLetterDifferFrom.setAccessible(true);

        Assert.assertEquals("the number of words with one letter differ from the target word is 5", ((List<String>)_oneLetterDifferFrom.invoke(wordLadder, "data")).size(), 4);

        Assert.assertEquals("the number of words with one letter differ from the target word is 5", ((List<String>)_oneLetterDifferFrom.invoke(wordLadder, "cat")).size(), 26);

    }
}