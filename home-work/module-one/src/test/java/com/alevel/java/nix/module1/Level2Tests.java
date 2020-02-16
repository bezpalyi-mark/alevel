package com.alevel.java.nix.module1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Level2Tests {
    @Test
    public void assertIsValidString() {
        String[] inputs = {"{[([])]}", "{([)]}", "", "{())}", "[(]", "{}}}", "()", "}{", "[](){}", "abc"};
        boolean[] expected = {true, false, true, false, false, false, true, false, true, false};
        Level2 level2 = new Level2();
        for (int i = 0; i < expected.length; i++) {
            boolean actual = level2.isValidString(inputs[i]);
            assertEquals(expected[i], actual);
        }
    }

    @Test
    public void assertGetLengthOfBinaryTree() {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.left.left.right.left = new TreeNode(7);
        root.left.left.right.right = new TreeNode(8);

        root.right = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(11);

        Level2 level2 = new Level2();
        final int expected1 = 4;
        final int actual1 = level2.getLengthOfBinaryTreeMixing(root);
        assertEquals(expected1, actual1);

        root.right.right.right = new TreeNode(12);
        root.right.right.right.right = new TreeNode(13);
        root.right.right.right.left = new TreeNode(14);
        root.right.right.right.left.left = new TreeNode(15);
        root.right.right.right.left.left.right = new TreeNode(16);

        final int expected2 = 6;
        final int actual2 = level2.getLengthOfBinaryTreeMixing(root);
        assertEquals(expected2, actual2);
    }
}