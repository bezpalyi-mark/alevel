package com.alevel.java.nix.module1;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Level2 {
    public boolean isValidString(final String string) {
        if (string.length() == 0) {
            return true;
        }
        String regexString = "\\((.*)\\)|\\{(.*)\\}|\\[(.*)\\]";
        Pattern pattern = Pattern.compile(regexString);
        Matcher matcher = pattern.matcher(string);
        StringBuilder stringBuilder = new StringBuilder(string.length());
        int groupNumber = getGroupNumber(string.charAt(0));
        while (matcher.find()) {
            stringBuilder.append(matcher.group(groupNumber));
            if (stringBuilder.length() == 0) {
                return true;
            }
            groupNumber = getGroupNumber(stringBuilder.charAt(0));
            if (groupNumber == 0) {
                return false;
            }
            if (stringBuilder.length() != 0) {
                matcher = pattern.matcher(stringBuilder.toString());
                stringBuilder.delete(0, stringBuilder.length());
            }
        }
        return false;
    }

    private int getGroupNumber(final char c) {
        if (c == '(') {
            return 1;
        } else if (c == '{') {
            return 2;
        } else if (c == '[') {
            return 3;
        }
        return 0;
    }

    public int getLengthOfBinaryTreeDescending(final TreeNode root) {
        int result = 0;
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return result;
        }
        stack.push(root);
        TreeNode currentNode;
        int counter = 0;
        while (!stack.empty()) {
            currentNode = stack.pop();
            counter--;
            while (currentNode != null) {
                if (currentNode.right != null) {
                    stack.push(currentNode.right);
                }
                currentNode = currentNode.left;
                counter++;
            }
            if (counter > result) {
                result = counter;
            }
        }
        return result;
    }

    public int getLengthOfBinaryTreeMixing(final TreeNode root) {
        int result = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;
        stack.push(root);
        int counter = 0;
        boolean getBack = false;
        do {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
                counter++;
                if((counter-1) > result && currentNode != null) {
                    result = counter - 1;
                }
                getBack = true;
            }
            currentNode = stack.pop();
            currentNode = currentNode.right;
            if(getBack) {
                counter--;
                getBack = false;
            }
        } while (!stack.isEmpty());
        return result;
    }

}
