package com.alevel.java.nix.hangman.view;

import com.alevel.java.nix.hangman.model.PersonStates;

import java.util.Arrays;

public class ConsoleView implements PrintState {
    @Override
    public void print(String string) {
        System.out.print(string);
    }

    @Override
    public void printCurrentState(char[] chars, PersonStates state) {
        System.out.println(Arrays.toString(chars));
        System.out.println(state);
    }
}
