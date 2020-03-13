package com.alevel.java.nix.homeworks.lesson13;

public class Retry<A> {

    private final int SLEEP_MULTIPLEX;

    public Retry(int sleepMultiplex) {
        SLEEP_MULTIPLEX = Math.max(sleepMultiplex, 100);
    }

    public A repeat(Block<A> block, int n) throws Exception {
        Exception exception = new Exception();
        for (int i = 0; i < n; i++) {
            try {
                A temp = block.run();
                Thread.sleep(SLEEP_MULTIPLEX * i);
                return temp;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                exception = e;
            }
        }
        throw exception;
    }
}
