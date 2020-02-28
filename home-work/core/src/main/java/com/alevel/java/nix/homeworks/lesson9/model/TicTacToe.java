package com.alevel.java.nix.homeworks.lesson9.model;

import com.alevel.java.nix.homeworks.lesson9.view.GameView;

public interface TicTacToe {
    boolean nextMove(int row, int column);

    boolean isValidPosition(int row, int column);

    void endGame();

    boolean haveWinner();

    char[][] getPlayingArea();

    GameView view();

    void printPlayingArea();

    String toString();

    int getMaxMovesValue();

    boolean isEnd();
}
