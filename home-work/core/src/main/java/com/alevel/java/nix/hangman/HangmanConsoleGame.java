package com.alevel.java.nix.hangman;

import com.alevel.java.nix.hangman.controller.ConsoleInput;
import com.alevel.java.nix.hangman.controller.SimpleHangmanGame;
import com.alevel.java.nix.hangman.model.ConsoleHangman;
import com.alevel.java.nix.hangman.model.Hangman;

public class HangmanConsoleGame {
    public static void main(String[] args) {
        ConsoleInput ci = new ConsoleInput(System.in);
        Hangman game = new ConsoleHangman(ci.readFileFromClasspath("words.txt"));
        SimpleHangmanGame gameStart = new SimpleHangmanGame(game, ci);
        gameStart.run();
    }

}
