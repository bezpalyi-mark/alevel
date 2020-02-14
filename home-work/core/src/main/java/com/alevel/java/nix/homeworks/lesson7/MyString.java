package com.alevel.java.nix.homeworks.lesson7;

public class MyString {
    public CharSequence reverse(CharSequence charSequence) {
       StringBuilder stringBuilder = new StringBuilder(charSequence);
        for(int i = 0, j = charSequence.length() - 1; i < charSequence.length() / 2; i++, j--) {
            char one = charSequence.charAt(i);
            char two = charSequence.charAt(j);
            stringBuilder.delete(i, i+1);
            stringBuilder.delete(j-1, j);
            stringBuilder.insert(i, two);
            stringBuilder.insert(j, one);
        }
        return stringBuilder;
    }
}
