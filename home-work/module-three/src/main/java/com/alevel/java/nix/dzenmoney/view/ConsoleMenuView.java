package com.alevel.java.nix.dzenmoney.view;

import java.util.List;

public class ConsoleMenuView {
    public void printStartMEnu() {
        System.out.println("Select number:\n"
                + "1 - Conduct a new operation\n"
                + "2 - Export orderings to csv file\n"
                + "3 - Exit");
    }

    public void printOperationMenu() {
        System.out.println("What add to operation?\n" +
                "1 - Account (by ID)\n" +
                "2 - Operation category\n" +
                "3 - Value of operation\n" +
                "4 - Confirm operation");
    }

    public void printCategories(List<String> categories) {
        System.out.println("Choose category: ");
        System.out.println(categories);
    }
}
