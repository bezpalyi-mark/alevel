package com.alevel.java.nix.homeworks.lesson9;

public interface AbstractTicTacToe {
    boolean nextMove(int playerNumber, int row, int column);

    boolean isValidPosition(int row, int column);

    void endGame();
}
