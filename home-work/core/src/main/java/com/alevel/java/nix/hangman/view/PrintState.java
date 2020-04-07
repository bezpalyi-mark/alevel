package com.alevel.java.nix.hangman.view;

import com.alevel.java.nix.hangman.model.PersonStates;

public interface PrintState {
    void print(String string);

    void printCurrentState();

    void updateView(PersonStates state, char[] chars);
}
