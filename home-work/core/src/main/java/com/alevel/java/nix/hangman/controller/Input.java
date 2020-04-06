package com.alevel.java.nix.hangman.controller;

public interface Input {
    char getChar();

    String[] readFileFromClasspath(String name);
}
