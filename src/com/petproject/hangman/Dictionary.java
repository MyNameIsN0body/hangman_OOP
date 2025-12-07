package com.petproject.hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dictionary {
    private static final String FILE_NAME = "src/com/petproject/hangman/dictionary_words.txt";
    private final List<String> dictionaryWords = new ArrayList<>();

    public Dictionary() {
        loadDictionary();
    }

    public String getRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(dictionaryWords.size());
        return dictionaryWords.get(randomIndex);
    }

    private  void loadDictionary() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            throw new RuntimeException("Dictionary file not found: " + file.getAbsolutePath());
        }

        if (file.length() == 0) {
            throw new RuntimeException("Dictionary file is empty: " + file.getAbsolutePath());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                dictionaryWords.add(line.trim());
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading dictionary file: " + file.getAbsolutePath() +
                    ". Details: " + e.getMessage(), e);
        }
        if (dictionaryWords.isEmpty()) {
            throw new RuntimeException("No valid words loaded from: " + file.getAbsolutePath() +
                    ". Check file content and format.");
        }
    }
}
