package com.alevel.java.nix.homeworks.lesson12;

import java.text.MessageFormat;

public class Student {
    String name;

    int age;

    @Override
    public String toString() {
        return MessageFormat.format("Name: {0}, age: {1}", name, age);
    }
}
