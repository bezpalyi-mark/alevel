package com.alevel.java.nix.homeworks.lesson9;

import com.alevel.java.nix.homeworks.lesson9.model.TicTacToe3x3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToe3x3Tests {
    @Test
    public void haveWinner() {
        char[][] playingArea1 = {
                {'x', '0', 'x'},
                {'0', 'x', ' '},
                {'0', ' ', 'x'}
        };
        char[][] playingArea2 = {
                {'x', '0', 'x'},
                {'0', ' ', ' '},
                {'0', ' ', 'x'}
        };
        char[][] playingArea3 = {
                {'x', '0', 'x'},
                {' ', ' ', ' '},
                {'0', ' ', 'x'}
        };
        char[][] playingArea4 = {
                {'x', '0', 'x'},
                {' ', '0', ' '},
                {'0', '0', 'x'}
        };
        char[][] playingArea5 = {
                {'x', '0', ' '},
                {'x', 'x', 'x'},
                {'0', ' ', 'x'}
        };
        char[][] playingArea6 = {
                {'x', '0', 'x'},
                {'x', '0', '0'},
                {'0', 'x', 'x'}
        };
        TicTacToe3x3 game = new TicTacToe3x3();

        game.setPlayingArea(playingArea1);
        assertTrue(game.haveWinner());
        assertTrue(game.isEnd());

        game.setPlayingArea(playingArea2);
        assertFalse(game.haveWinner());
        assertFalse(game.isEnd());

        game.setPlayingArea(playingArea3);
        assertFalse(game.haveWinner());
        assertFalse(game.isEnd());

        game.setPlayingArea(playingArea4);
        assertTrue(game.haveWinner());
        assertTrue(game.isEnd());

        game.setPlayingArea(playingArea5);
        assertTrue(game.haveWinner());
        assertTrue(game.isEnd());

        game.setPlayingArea(playingArea6);
        assertFalse(game.haveWinner());
        assertTrue(game.isEnd());
    }

    @Test
    public void isValidPositionTest() {
        char[][] playingArea = {
                {'x', '0', 'x'},
                {'0', 'x', ' '},
                {'0', ' ', 'x'}
        };
        TicTacToe3x3 game = new TicTacToe3x3();
        game.setPlayingArea(playingArea);

        assertFalse(game.isValidPosition(1, 4));
        assertFalse(game.isValidPosition(5, 2));
        assertFalse(game.isValidPosition(-1, 1));
        assertFalse(game.isValidPosition(1, 1));
        assertFalse(game.isValidPosition(1, 3));
        assertTrue(game.isValidPosition(2, 3));
        assertTrue(game.isValidPosition(3, 2));
    }

    @Test
    public void nextMoveTest() {
        char[][] playingArea = {
                {'x', '0', 'x'},
                {'0', 'x', ' '},
                {'0', ' ', 'x'}
        };
        TicTacToe3x3 game = new TicTacToe3x3();
        game.setPlayingArea(playingArea);

        assertTrue(game.nextMove(2, 3));
        assertFalse(game.nextMove(2, 3));
        assertFalse(game.nextMove( 1, 1));
        assertTrue(game.nextMove( 3, 2));
        assertFalse(game.nextMove( 3, 2));
    }
}
