package com.alevel.java.nix.hangman.model;

import com.alevel.java.nix.hangman.view.PrintState;

public interface Hangman {
    boolean nextStep(char nextChar);

    boolean isEnd();

    PrintState view();

    boolean isValidChar(char character);

    String chooseWord();

    void fillProgressChars();

    boolean isLoose();

}
