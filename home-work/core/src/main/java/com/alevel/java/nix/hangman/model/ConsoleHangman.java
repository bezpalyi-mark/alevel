package com.alevel.java.nix.hangman.model;

import com.alevel.java.nix.hangman.view.ConsoleView;
import com.alevel.java.nix.hangman.view.PrintState;

import java.util.*;

public class ConsoleHangman implements Hangman {
    private static final int MAX_MISSES = 6;

    private PrintState view;

    private String[] words;

    private String word;

    private char[] progressChars;

    private Set<Character> storedChars;

    private int missesCount = 0;

    private PersonStates[] states;

    private int step = 0;

    private boolean end = false;

    public ConsoleHangman(String[] words) {
        view = new ConsoleView();
        this.words = words;
        word = chooseWord();
        progressChars = new char[word.length()];
        storedChars = new HashSet<>();
        states = PersonStates.values();
    }


    @Override
    public boolean nextStep(char nextChar) {
        if(isEnd()) {
            return false;
        }
        view.printCurrentState(progressChars, states[missesCount]);
        if(isValidChar(nextChar)) {
            storedChars.add(nextChar);
            for(int i = 0; i < word.length(); i++) {
                if(word.charAt(i) == nextChar) {
                    progressChars[i] = nextChar;
                    step++;
                }
            }
        }  else {
            missesCount++;
        }
        assertEnd();
        return true;
    }

    private void assertEnd() {
        if(step == word.length()) {
            end = true;
        }
        if(missesCount == MAX_MISSES) {
            end = true;
        }
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
        int length = words.length - 1;
        int choose = (int) (Math.random() % length);
        return words[choose];
    }

    @Override
    public void fillProgressChars() {
        for (int i = 0; i < words.length; i++) {
            progressChars[i] = '_';
        }
    }
}
