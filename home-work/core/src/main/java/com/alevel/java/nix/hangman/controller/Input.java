package com.alevel.java.nix.hangman.controller;

import java.util.List;

public interface Input {
    char getChar();

    List<String> readFileFromClasspath(String name);
}
