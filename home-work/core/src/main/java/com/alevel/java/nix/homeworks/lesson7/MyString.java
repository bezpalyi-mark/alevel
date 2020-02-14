package com.alevel.java.nix.homeworks.lesson7;

public class MyString {
    public CharSequence reverse(CharSequence charSequence) {
        StringBuilder stringBuilder = new StringBuilder(charSequence);
        return stringBuilder.reverse();
    }
}
