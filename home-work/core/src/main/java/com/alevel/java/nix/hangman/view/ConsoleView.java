package com.alevel.java.nix.hangman.view;

import com.alevel.java.nix.hangman.model.PersonStates;

public class ConsoleView implements PrintState {
    private PersonStates currentState = PersonStates.BEGIN_STATE;

    private char[] chars;

    private String word;

    public ConsoleView(char[] chars) {
        this.chars = chars;
        convertChars();
    }

    @Override
    public void print(String string) {
        System.out.print(string);
    }

    @Override
    public void printCurrentState() {
        System.out.println(word);
        System.out.println(currentState.getState());
    }

    @Override
    public void updateView(PersonStates state, char[] chars) {
        currentState = state;
        this.chars = chars;
        convertChars();
    }

    private void convertChars() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : chars) {
            stringBuilder.append(character);
        }
        word = stringBuilder.toString();
    }
}
