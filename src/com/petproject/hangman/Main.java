package com.petproject.hangman;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            runStartMenu();
        } catch (RuntimeException e) {
            System.out.println("Unhandled error: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
            System.out.println("Program stopped.");
        }
    }

    private static void runStartMenu() {
        Pictures.printStartBanner();
        System.out.print(
                """
                        Игра 'Виселица'
                        Спасите человечка от виселицы, угадав загаданное слово по буквам \
                        быстрее, чем закончатся ваши попытки!!!
                        
                        """
        );
        while (userWantsToPlay()) {
            Game game = new Game();
            game.startGame();
        }
    }


    private static boolean userWantsToPlay() {
        System.out.println("\uD83C\uDFAE Для начала игры нажмите Enter. \uD83C\uDFC3 Для выхода - любую другую клавишу и Enter.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = reader.readLine();

            if (input.isEmpty()) {
                return true;
            } else {
                System.out.println("\uD83D\uDCAB Ждем вас снова! \uD83D\uDC4B До свидания!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

}