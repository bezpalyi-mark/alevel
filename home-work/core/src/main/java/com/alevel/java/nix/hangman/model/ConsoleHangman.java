package com.alevel.java.nix.hangman.model;

import com.alevel.java.nix.hangman.view.ConsoleView;
import com.alevel.java.nix.hangman.view.PrintState;

import java.util.*;

public class ConsoleHangman implements Hangman {
    private static final int MAX_MISSES = 6;

    private final PrintState view;

    private final List<String> words;

    private final String word;

    private final char[] progressChars;

    private final Set<Character> storedChars;

    private int missesCount = 0;

    private final PersonStates[] states;

    private int step = 0;

    private boolean end = false;

    private boolean loose = false;

    public ConsoleHangman(List<String> words) {
        this.words = words;
        word = chooseWord();
        progressChars = new char[word.length()];
        fillProgressChars();
        storedChars = new HashSet<>();
        states = PersonStates.values();
        view = new ConsoleView(progressChars);
    }


    @Override
    public boolean nextStep(char nextChar) {
        boolean result = false;
        if(isEnd()) {
            return false;
        }
        if(isValidChar(nextChar)) {
            storedChars.add(nextChar);
            for(int i = 0; i < word.length(); i++) {
                if(word.charAt(i) == nextChar) {
                    progressChars[i] = nextChar;
                    step++;
                }
            }
            result = true;
        }  else if (!consistInProgress(nextChar)){
            missesCount++;
            result = false;
        }
        view.updateView(states[missesCount], progressChars);
        assertEnd();
        return result;
    }

    private void assertEnd() {
        if(step == word.length()) {
            end = true;
        }
        if(missesCount == MAX_MISSES) {
            loose = true;
            end = true;
        }
    }

    @Override
    public boolean isLoose() {
        return loose;
    }

    @Override
    public boolean isEnd() {
        return end;
    }

    @Override
    public PrintState view() {
        return view;
    }

    @Override
    public boolean isValidChar(char character) {
        if(consistInProgress(character)) {
            return false;
        }
        return consistInWord(character);
    }

    private boolean consistInProgress(char character) {
        if(storedChars.size() == 0) {
            return false;
        } else {
            return storedChars.contains(character);
        }
    }

    private boolean consistInWord(char character) {
        return word.indexOf(character) != -1;
    }

    @Override
    public String chooseWord() {
        int length = words.size() - 1;
        int choose = (int) (Math.random() * length);
        return words.get(choose);
    }

    @Override
    public void fillProgressChars() {
        for (int i = 0; i < word.length(); i++) {
            progressChars[i] = '_';
        }
    }
}
