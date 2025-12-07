package com.petproject.hangman;

public class HiddenWordLetters {
    private static final char HIDDEN_LETTER_SYMBOL = '□';
    private final String secretWord;
    private final char[] hiddenWordLetters;

    public HiddenWordLetters(String secretWord) {
        this.secretWord = secretWord;
        this.hiddenWordLetters = initHiddenWord(secretWord);
    }

    private static char[] initHiddenWord(String secretWord) {
        char[] hiddenWord = new char[secretWord.length()];
        for (int i = 0; i < secretWord.length(); i++) {
            hiddenWord[i] = HIDDEN_LETTER_SYMBOL;
        }
        return hiddenWord;
    }

    protected int getHiddenLetterCount() {
        int hiddenLetterCount = 0;
        for (char letter : hiddenWordLetters) {
            if (letter == HIDDEN_LETTER_SYMBOL) {
                hiddenLetterCount++;
            }
        }
        return hiddenLetterCount;
    }

    protected void printWordLetters() {
        for (char letter : hiddenWordLetters) {
            System.out.print(letter + " ");
        }
        System.out.println(" ");
    }

    public boolean containsChar(char playerLitter) {
        for (char litter : hiddenWordLetters) {
            if (litter == playerLitter) {
                return true;
            }
        }
        return false;
    }

    public boolean containsPlayerLitter(char playerLitter) {
        return secretWord.contains(String.valueOf(playerLitter));
    }

    public void openLetter(char playerLitter) {
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == playerLitter) {
                hiddenWordLetters[i] = playerLitter;
            }
        }
    }
}