package com.alevel.java.nix.hangman.controller;

import com.alevel.java.nix.hangman.model.Hangman;
import com.alevel.java.nix.hangman.view.PrintState;

public class SimpleHangmanGame {
    private Input input;

    private Hangman model;

    public SimpleHangmanGame(Hangman hangman, Input input) {
        model = hangman;
        this.input = input;
    }

    public void run() {
        model.view().print("Welcome to game!\n");
        while(!model.isEnd()) {
            model.view().print("Enter your character:");
            char nextChar = input.getChar();
            model.nextStep(nextChar);
        }
        model.view().print("Thanks for game!");
    }
}
