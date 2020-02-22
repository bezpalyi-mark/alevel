package com.alevel.java.nix.homeworks.lesson9.implementation;

import com.alevel.java.nix.homeworks.lesson9.AbstractTicTacToe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicTacToe implements AbstractTicTacToe {

    private static final Logger log = LoggerFactory.getLogger(TicTacToe.class);

    private char[][] playingArea = new char[3][3];
    public static final int MAX_MOVES_VALUE = 9;
    private char WINNER_CHAR;

    public TicTacToe() {
        initPlayingArea();
    }

    public boolean nextMove(int playerNumber, int row, int column) {
        if (!isValidPosition(row, column) || (playerNumber != 1 && playerNumber != 2)) {
            log.info("Invalid data!");
            return false;
        }
        log.info("Player" + playerNumber + " making his move");
        playingArea[row - 1][column - 1] = playerNumber == 1 ? 'x' : '0';
        return true;
    }

    public boolean isValidPosition(int row, int column) {
        if (row > 3 || row < 1 || column > 3 || column < 1) {
            log.warn("Invalid input data!");
            return false;
        }
        return playingArea[row - 1][column - 1] == ' ';
    }

    public boolean isEnd() {
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
        switch (WINNER_CHAR) {
            case 'x':
                System.out.println("\nCongratulations! First player win!");
                log.info("Player1 won");
                break;
            case '0':
                System.out.println("\nCongratulations! Second player win!");
                log.info("Player2 won");
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

    public void setPlayingArea(char[][] playingArea) {
        this.playingArea = playingArea;
    }
}
