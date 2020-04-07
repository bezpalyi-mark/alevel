package com.alevel.java.nix.hangman.model;

public enum PersonStates {
    BEGIN_STATE (
            "------\n" +
            "|\n" +
            "|\n" +
            "|\n" +
            "|\n" +
            "|\n"
    ),

    ONE_MISS (
            "------\n" +
            "|    |\n" +
            "|    O\n" +
            "|\n" +
            "|\n" +
            "|\n"
    ),

    TWO_MISSES (
            "------\n" +
            "|    |\n" +
            "|    O\n" +
            "|    |\n" +
            "|    |\n" +
            "|\n"
    ),

    THREE_MISSES (
            "------\n" +
            "|    |\n" +
            "|    O\n" +
            "|  __|\n" +
            "|    |\n" +
            "|\n"
    ),

    FOUR_MISSES (
            "------\n" +
            "|    |\n" +
            "|    O\n" +
            "|  __|__\n" +
            "|    |\n" +
            "|\n"
    ),

    FIVE_MISSES (
            "------\n" +
            "|    |\n" +
            "|    O\n" +
            "|  __|__\n" +
            "|    |\n" +
            "|   /\n"
    ),

    SIX_MISSES ("------\n" +
            "|    |\n" +
            "|    O\n" +
            "|  __|__\n" +
            "|    |\n" +
            "|   / \\ \n"
    );

    private String state;

    PersonStates(String s) {
        state = s;
    }

    public String getState() {
        return state;
    }
}
