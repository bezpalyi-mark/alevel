package com.alevel.java.nix.homeworks.lesson13;

public class Retry {

    private final int SLEEP_MULTIPLEX;

    public Retry(int sleepMultiplex) {
        SLEEP_MULTIPLEX = Math.max(sleepMultiplex, 100);
    }

    public void repeat(Block block, int n) throws Exception {
        for (int i = 0; i < n; i++) {
            try {
                block.run();
                Thread.sleep(SLEEP_MULTIPLEX * i);
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                if (i == (n - 1)) {
                    throw new Exception();
                }
            }
        }
    }
}
