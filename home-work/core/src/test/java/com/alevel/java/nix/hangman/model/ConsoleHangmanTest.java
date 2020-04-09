package com.alevel.java.nix.hangman.model;

import com.alevel.java.nix.hangman.controller.ConsoleInput;
import com.alevel.java.nix.hangman.controller.SimpleHangmanGame;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleHangmanTest {

    @Test
    void simpleWin() {
        assertFalse(testWord("w o r d"));
        assertFalse(testWord("w u p o x b r m d"));
    }

    @Test
    void simpleLoose() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testWord("word"));
        assertTrue(testWord("w u r d q q z z f f"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testWord("w o rd"));
    }

    boolean testWord(String word) {
        byte[] buf = word.getBytes();
        InputStream inputStream = new ByteArrayInputStream(buf);
        ConsoleInput ci = new ConsoleInput(inputStream);
        ConsoleHangman game = new ConsoleHangman(ci.readFileFromClasspath("testWords.txt"));
        SimpleHangmanGame gameStart = new SimpleHangmanGame(game, ci);
        gameStart.run();
        return game.isLoose();
    }

    @Test
    void assertAPI() {
        String word = "word";
        Hangman game = new ConsoleHangman(Collections.singletonList("word"));
        for (int i = 0; i < word.length(); i++) {
            assertTrue(game.isValidChar(word.charAt(i)));
        }

        assertEquals("word", game.chooseWord());

        assertTrue(game.nextStep('w'));
        assertFalse(game.isValidChar('w'));

        assertFalse(game.isValidChar('z'));
        assertFalse(game.nextStep('z'));

        assertTrue(game.nextStep('o'));
        assertTrue(game.nextStep('r'));
        assertTrue(game.nextStep('d'));

        assertTrue(game.isEnd());
        assertFalse(game.isLoose());
        assertFalse(game.nextStep('w'));
    }
}