package com.petproject.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int MAX_MISTAKES = 7;

    public void startGame() {
        List<Character> wrongLetters = new ArrayList<>();
        Dictionary dictionary = new Dictionary();
        String secretWord = dictionary.getRandomWord();
        HiddenWordLetters hiddenWordLetters = new HiddenWordLetters(secretWord);
        int currentMistakeCount = 0;

        while (!isGameOver(currentMistakeCount, hiddenWordLetters.getHiddenLetterCount())) {
            printGameState(hiddenWordLetters, currentMistakeCount, wrongLetters);
            currentMistakeCount = processGuess(hiddenWordLetters, wrongLetters, currentMistakeCount);

            if (isWin(hiddenWordLetters.getHiddenLetterCount())) {
                System.out.println("\uD83C\uDFC6 Загаданное слово: " + secretWord);
                Pictures.printWinBanner();
                break;
            }
            if (isLose(currentMistakeCount)) {
                System.out.println("\uD83D\uDC80 Загаданное слово: " + secretWord);
                Pictures.printLoseBanner();
                break;
            }
        }
    }

    public void printGameState(HiddenWordLetters HiddenWordLetters, int currentMistakeCount, List<Character> wrongLetters) {
        Pictures.printHangmanStage(currentMistakeCount);
        printWrongLetters(wrongLetters);
        HiddenWordLetters.printWordLetters();
    }

    private static int processGuess(HiddenWordLetters hiddenWordLetters, List<Character> wrongLetters, int currentMistakeCount) {
        char playerLitter;

        playerLitter = readUserLetter();
        if (hiddenWordLetters.containsPlayerLitter(playerLitter)) {
            if ((hiddenWordLetters.containsChar(playerLitter))) {
                printLetterAlreadyUsed();
            } else {
                hiddenWordLetters.openLetter(playerLitter);
            }

        } else {
            if ((containsChar(wrongLetters, playerLitter))) {
                printCurrentMistakeCount(currentMistakeCount);
                printLetterAlreadyUsed();
                return currentMistakeCount;
            }
            wrongLetters.add(playerLitter);
            ++currentMistakeCount;
        }
        printCurrentMistakeCount(currentMistakeCount);
        return currentMistakeCount;

    }


    private static boolean containsChar(List<Character> list, char playerLitter) {
        return list.contains(playerLitter);
    }

    private static void printLetterAlreadyUsed() {
        System.out.println("\uD83D\uDEAB Такая буква уже была использована, попробуйте новую");
    }

    private static void printCurrentMistakeCount(int currentMistakeCount) {
        System.out.println("Ошибки: " + currentMistakeCount + "/" + MAX_MISTAKES);
    }

    private static void printWrongLetters(List<Character> wrongLetters) {
        System.out.print("\uD83D\uDCDD Список неудачных букв: ");
        for (char letter : wrongLetters) {
            System.out.print(letter + " ");
        }
        System.out.println(" ");
    }
    private static boolean isGameOver(int currentMistakeCount, int hiddenLetterCount) {
        return isLose(currentMistakeCount) || isWin(hiddenLetterCount);
    }

    private static boolean isWin(int hiddenLetterCount) {
        return hiddenLetterCount == 0;
    }

    private static boolean isLose(int currentMistakeCount) {
        return currentMistakeCount >= MAX_MISTAKES;
    }
    private static char readUserLetter() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            do {
                System.out.println("✏️ Введите русскую букву:");
                input = reader.readLine();
            } while (!isValidInput(input));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return input.toLowerCase().charAt(0);
    }

    private static boolean isValidInput(String letter) {
        if (letter == null || letter.isEmpty()) {
            return false;
        }
        if (letter.length() > 1) {
            return false;
        }
        char character = letter.toLowerCase().charAt(0);
        if ((character < 'а' || character > 'я') && character != 'ё') {
            return false;
        }

        return true;
    }


}
