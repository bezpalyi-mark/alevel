package com.alevel.java.nix.homeworks.lesson9;

import com.alevel.java.nix.homeworks.lesson9.controller.SimpleTicTacToeGame;
import com.alevel.java.nix.homeworks.lesson9.model.TicTacToe3x3;

public class TicTacToeApp {
    public static void main(String[] args) {
        var game = new SimpleTicTacToeGame(new TicTacToe3x3());
        game.beginGame();
    }
}
