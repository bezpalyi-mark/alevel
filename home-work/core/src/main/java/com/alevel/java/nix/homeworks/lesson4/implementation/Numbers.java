package com.alevel.java.nix.homeworks.lesson4.implementation;

import com.alevel.java.nix.homeworks.lesson4.NumberHandler;

public class Numbers implements NumberHandler {

    @Override
    public String fizzBuzzOfNumber(final long number, boolean inverse) {
        final int length = (int) (Math.log10(number) + 1);
        final int capacity = 5000;
        StringBuilder stringBuilder = new StringBuilder(capacity);
        long workNumber = number;
        if (inverse) {
            for (int i = 0; i < length; i++) {
                stringBuilder.append(handleDigit((int) workNumber));
                workNumber /= 10;
            }
        } else {
            int tempLength = length - 1;
            for (int i = 0; i < length; i++) {
                workNumber /= Math.pow(10, tempLength);
                stringBuilder.append(handleDigit((int) workNumber));
                tempLength--;
                workNumber = number;
            }
        }
        return stringBuilder.toString();
    }

    private String handleDigit(int digit) {
        if (((digit % 10) % 2 == 0) && ((digit % 10) % 3 == 0)) {
            return "fizzbuzz ";
        } else if ((digit % 10) % 2 == 0) {
            return "fizz ";
        } else if ((digit % 10) % 3 == 0) {
            return "buzz ";
        }
        return "";
    }

    @Override
    public int countBitsEstablishedToOne(long number) {
        return 0;
    }

    public static void main(String[] args) {
        Numbers numbers = new Numbers();
        System.out.println(numbers.fizzBuzzOfNumber(915762, false));
    }
}
