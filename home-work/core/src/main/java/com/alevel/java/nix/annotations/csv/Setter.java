package com.alevel.java.nix.annotations.csv;

import java.lang.reflect.Field;

@FunctionalInterface
public interface Setter {
    void set(Object target, Field field, String value) throws IllegalAccessException;
}
