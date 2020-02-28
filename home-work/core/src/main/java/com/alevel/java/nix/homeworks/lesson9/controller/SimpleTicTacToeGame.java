package com.alevel.java.nix.homeworks.lesson9.controller;

import com.alevel.java.nix.homeworks.lesson9.model.TicTacToe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class SimpleTicTacToeGame {

    private static final Logger log = LoggerFactory.getLogger(SimpleTicTacToeGame.class);

    private TicTacToe model;

    private TicTacToeInput input;

    public SimpleTicTacToeGame(TicTacToe ticTacToe) {
        this.model = ticTacToe;
        input = new TicTacToeInput();
    }

    public void beginGame() {
        log.info("The game has began");
        model.view().print("Welcome!");
        model.printPlayingArea();
        int count = 0;
        while (!model.isEnd()) {
            int playerNumber = (count % 2) + 1;
            model.view().print(
                    MessageFormat.format("The {0} player selects a position for the move. ",
                            playerNumber == 1 ? "first" : "second"));

            model.view().print("Row: ");
            int nextRow = input.getIntegerInput();
            model.view().print("Column: ");
            int nextColumn = input.getIntegerInput();

            if(model.nextMove(nextRow, nextColumn)) {
                count++;
            }
            model.printPlayingArea();
        }
        log.info("The game has ended");
        model.view().print("\nThanks for game!");
    }
}
