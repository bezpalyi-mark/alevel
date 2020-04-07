package com.alevel.java.nix.hangman.controller;

import com.alevel.java.nix.hangman.model.Hangman;

public class SimpleHangmanGame {
    private final Input input;

    private final Hangman model;

    public SimpleHangmanGame(Hangman hangman, Input input) {
        model = hangman;
        this.input = input;
    }

    public void run() {
        model.view().print("Welcome to game!\n");
        model.view().printCurrentState();
        while (!model.isEnd()) {
            model.view().print("Enter your character:");
            char nextChar = input.getChar();
            if(nextChar != '-') {
                if(model.nextStep(nextChar)) {
                    model.view().print("You are master!\n");
                } else {
                    model.view().print("Ooops.. NO!\n");
                }
            } else {
                model.view().print("Invalid input data! Please enter ONE character\n");
            }
            model.view().printCurrentState();
        }
        if(model.isLoose()) {
            model.view().print("You are loose! Game over!\n");
        } else {
            model.view().print("Congratulations!\n");
        }
        model.view().print("Thanks for game!");
    }
}
