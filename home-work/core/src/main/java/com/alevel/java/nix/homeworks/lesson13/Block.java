package com.alevel.java.nix.homeworks.lesson13;

@FunctionalInterface
public interface Block<A> {
    A run() throws Exception;
}
