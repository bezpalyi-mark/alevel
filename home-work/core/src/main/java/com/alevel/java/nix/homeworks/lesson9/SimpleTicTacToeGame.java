package com.alevel.java.nix.homeworks.lesson9;

import com.alevel.java.nix.homeworks.lesson9.implementation.TicTacToe;
import com.alevel.java.nix.homeworks.lesson9.implementation.TicTacToeInputOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class SimpleTicTacToeGame {

    private static final Logger log = LoggerFactory.getLogger(TicTacToe.class);
    private TicTacToe game;
    private TicTacToeInputOutput io;

    public SimpleTicTacToeGame() {
        game = new TicTacToe();
        io = new TicTacToeInputOutput();
    }

    public void beginGame() {
        log.info("The game has began");
        System.out.println("Welcome!");
        io.printPlayingArea(game.getPlayingArea());
        for (int i = 0; i < TicTacToe.MAX_MOVES_VALUE; i++) {
            int playerNumber = (i % 2) + 1;
            System.out.println(
                    MessageFormat.format("The {0} player selects a position for the move. ",
                            playerNumber == 1 ? "first" : "second"));
            System.out.print("Row: ");
            int nextRow = io.getIntegerInput();
            System.out.print("Column: ");
            int nextColumn = io.getIntegerInput();
            if (!game.nextMove(playerNumber, nextRow, nextColumn)) {
                i--;
                continue;
            }
            if (game.isEnd()) {
                game.endGame();
                log.info("The game has ended");
                io.printPlayingArea(game.getPlayingArea());
                System.out.println("Thanks for game!");
                return;
            }
            io.printPlayingArea(game.getPlayingArea());
        }
        log.info("The game has ended, draw!");
        io.printPlayingArea(game.getPlayingArea());
        System.out.println("\nDraw!");
    }
}
