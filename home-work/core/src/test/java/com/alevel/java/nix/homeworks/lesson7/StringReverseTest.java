package com.alevel.java.nix.homeworks.lesson7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringReverseTest {
    @Test
    void stringReverseTest() {
        String[] inputs = {"ABCDEFG", " -!", ": `>", "", " "};
        String[] expected = {"GFEDCBA", "!- ", ">` :", "", " "};
        MyString myString = new MyString();
        for(int i = 0; i < inputs.length; i++) {
            CharSequence actual = myString.reverse(inputs[i]);
            assertEquals(expected[i], actual.toString());
        }
    }
}
