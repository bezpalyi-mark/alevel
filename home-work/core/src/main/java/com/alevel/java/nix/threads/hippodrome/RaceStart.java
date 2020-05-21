package com.alevel.java.nix.threads.hippodrome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.*;

public class RaceStart {
    private static final Logger LOGGER = LoggerFactory.getLogger(RaceStart.class);

    private final static Integer HORSE_COUNT = 5;

    private final static Integer MAX_WAIT = 7;

    private static CountDownLatch counter = new CountDownLatch(HORSE_COUNT);

    private static ConcurrentHashMap<Long, Integer> finished = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int bet = getNumberChoice();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(HORSE_COUNT);
        for (int i = 0; i < HORSE_COUNT; i++) {
            executor.execute(new Horse(i + 1));
        }

        try {
            executor.awaitTermination(MAX_WAIT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("Horses got lost! We do not refund money!");
            executor.shutdown();
            return;
        }
        if(executor.getActiveCount() > 0) {
            LOGGER.error("Horses got lost! We do not refund money!");
            executor.shutdown();
            return;
        }

        Integer winner = finished.get((long) HORSE_COUNT);
        System.out.println("Winner is number: " + winner);
        if (winner.equals(bet)) {
            System.out.println("Congratulations, you win money!");
        } else {
            System.out.println("Sorry, but you loose!");
        }
        executor.shutdownNow();
    }

    private static int getNumberChoice() {
        int bet = 0;
        while (bet == 0) {
            try {
                System.out.print("Place a bet (1-5): ");
                bet = new Scanner(System.in).nextInt();
                if (bet < 1 || bet > 5) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                LOGGER.error("Ssory, we haven't horse with this number");
                bet = 0;
            }
        }
        return bet;
    }

    static class Horse implements Runnable {
        private static final Logger LOGGER = LoggerFactory.getLogger(Horse.class);

        private static final Integer FINAL_DISTANCE = 1000;

        private static final Integer MIN_DISTANCE_PER_ITERATION = 100;

        private static final Integer MAX_DISTANCE_PER_ITERATION = 200;

        private static final Integer MIN_SLEEP_TIME = 400;

        private static final Integer MAX_SLEEP_TIME = 500;

        private final Integer horseNumber;

        public Horse(Integer number) {
            this.horseNumber = number;
        }

        @Override
        public void run() {
            int currentDistance = 0;
            while (currentDistance < FINAL_DISTANCE) {
                currentDistance += ThreadLocalRandom.current()
                        .nextInt(MIN_DISTANCE_PER_ITERATION, MAX_DISTANCE_PER_ITERATION + 1);
                long sleepTime = ThreadLocalRandom.current()
                        .nextInt(MIN_SLEEP_TIME, MAX_SLEEP_TIME + 1);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    LOGGER.error("Horse interrupted while sleep");
                }
            }
            finished.put(counter.getCount(), horseNumber);
            counter.countDown();
            System.out.println("Horse number " + horseNumber + " finished!");
        }
    }
}

