package com.alevel.java.nix.homeworks.lesson9.model;

public enum GameEvents {
    FIRST_PLAYER_WON("\nCongratulations! First player won!"),
    SECOND_PLAYER_WON("\nCongratulations! Second player won!"),
    DRAW("\nDraw! Game over!");

    private String winner;

    GameEvents(String string) {
        winner = string;
    }

    public String getWinner() {
        return winner;
    }
}
