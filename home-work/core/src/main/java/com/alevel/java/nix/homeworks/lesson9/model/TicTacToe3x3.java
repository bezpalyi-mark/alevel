package com.alevel.java.nix.homeworks.lesson9.model;

import com.alevel.java.nix.homeworks.lesson9.view.GameView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class TicTacToe3x3 implements TicTacToe {

    private static final Logger log = LoggerFactory.getLogger(TicTacToe3x3.class);

    private char[][] playingArea = new char[3][3];

    public static final int MAX_MOVES_VALUE = 9;

    private char WINNER_CHAR = 'D';

    private GameView view;

    private int currentPlayer = 1;

    private int occupiedPositionsCount = 0;

    public TicTacToe3x3() {
        initPlayingArea();
        view = new GameView();
    }

    public boolean nextMove(int row, int column) {
        if (!isValidPosition(row, column)) {
            log.warn("Invalid data!");
            return false;
        }
        log.info("Player" + currentPlayer + " making his move");
        playingArea[row - 1][column - 1] = currentPlayer == 1 ? 'x' : '0';
        currentPlayer = ++currentPlayer % 2;
        occupiedPositionsCount++;
        return true;
    }

    private boolean checkForEnd() {
        if (occupiedPositionsCount == MAX_MOVES_VALUE || haveWinner()) {
            endGame();
            return true;
        }
        return false;
    }

    public boolean isValidPosition(int row, int column) {
        if (row > 3 || row < 1 || column > 3 || column < 1) {
            log.warn("Invalid input data!");
            return false;
        }
        return playingArea[row - 1][column - 1] == ' ';
    }

    public boolean haveWinner() {
        //check by rows
        if (playingArea[0][0] == playingArea[0][1] && playingArea[0][0] == playingArea[0][2]
                && playingArea[0][0] != ' ') { //first row
            WINNER_CHAR = playingArea[0][0];
            return true;
        } else if (playingArea[1][0] == playingArea[1][1] && playingArea[1][0] == playingArea[1][2]
                && playingArea[1][0] != ' ') { //second row
            WINNER_CHAR = playingArea[1][0];
            return true;
        } else if (playingArea[2][0] == playingArea[2][1] && playingArea[2][0] == playingArea[2][2]
                && playingArea[2][0] != ' ') { //third row
            WINNER_CHAR = playingArea[2][0];
            return true;
        }

        //check for columns
        if (playingArea[0][0] == playingArea[1][0] && playingArea[0][0] == playingArea[2][0]
                && playingArea[0][0] != ' ') { //first column
            WINNER_CHAR = playingArea[0][0];
            return true;
        } else if (playingArea[0][1] == playingArea[1][1] && playingArea[0][1] == playingArea[2][1]
                && playingArea[0][1] != ' ') { //second column
            WINNER_CHAR = playingArea[0][1];
            return true;
        } else if (playingArea[0][2] == playingArea[1][2] && playingArea[0][2] == playingArea[2][2]
                && playingArea[0][2] != ' ') { //third column
            WINNER_CHAR = playingArea[0][2];
            return true;
        }

        //check for diagonals
        if (playingArea[0][0] == playingArea[1][1] && playingArea[0][0] == playingArea[2][2]
                && playingArea[0][0] != ' ') {
            WINNER_CHAR = playingArea[0][0];
            return true;
        } else if (playingArea[0][2] == playingArea[1][1] && playingArea[0][2] == playingArea[2][0]
                && playingArea[0][2] != ' ') {
            WINNER_CHAR = playingArea[0][2];
            return true;
        }
        return false;
    }

    public void endGame() {
        GameEvents events;
        switch (WINNER_CHAR) {
            case 'x':
                events = GameEvents.FIRST_PLAYER_WON;
                view.print(events.getWinner());
                log.info("Player1 won");
                break;
            case '0':
                events = GameEvents.SECOND_PLAYER_WON;
                view.print(events.getWinner());
                log.info("Player2 won");
                break;
            case 'D':
                events = GameEvents.DRAW;
                view.print(events.getWinner());
                log.info("Draw");
                break;
        }
    }

    private void initPlayingArea() {
        for (int i = 0, rowsLength = playingArea.length; i < rowsLength; i++) {
            for (int j = 0, colsLength = playingArea[0].length; j < colsLength; j++) {
                playingArea[i][j] = ' ';
            }
        }
    }

    public char[][] getPlayingArea() {
        return playingArea;
    }

    private void recountOccupiedPositions() {
        occupiedPositionsCount = 0;
        for (char[] chars : playingArea) {
            for (int j = 0; j < playingArea[0].length; j++) {
                if (chars[j] != ' ') {
                    occupiedPositionsCount++;
                }
            }
        }
    }

    public void setPlayingArea(char[][] playingArea) {
        this.playingArea = playingArea;
        recountOccupiedPositions();
    }

    public GameView view() {
        return view;
    }

    public void printPlayingArea() {
        view.print(MessageFormat.format(
                "    1   2   3\n" +
                        "1   {0} | {1} | {2} \n" +
                        "   -----------\n" +
                        "2   {3} | {4} | {5} \n" +
                        "   -----------\n" +
                        "3   {6} | {7} | {8} \n",
                playingArea[0][0], playingArea[0][1], playingArea[0][2],
                playingArea[1][0], playingArea[1][1], playingArea[1][2],
                playingArea[2][0], playingArea[2][1], playingArea[2][2]));
    }

    public int getMaxMovesValue() {
        return MAX_MOVES_VALUE;
    }

    public boolean isEnd() {
        return checkForEnd();
    }
}
