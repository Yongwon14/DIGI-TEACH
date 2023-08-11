package com.mediamega.digi_teach;

import java.util.ArrayList;
import java.util.List;

public class SentenceProvider {
    private static List<String> sentences = new ArrayList<>();

    static {
        sentences.add("Hello");
        sentences.add("Thank you");
        sentences.add("I'm sorry");
        sentences.add("Can you help me?");
        sentences.add("What's your name?");
        sentences.add("Yes");
        sentences.add("I'm happy");
        sentences.add("Goodbye");
        sentences.add("No");
    }
    public static List<String> getSentences() {
        return sentences;
    }


}

