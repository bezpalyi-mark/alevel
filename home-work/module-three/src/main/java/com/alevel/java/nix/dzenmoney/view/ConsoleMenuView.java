package com.alevel.java.nix.dzenmoney.view;

import com.alevel.java.nix.dzenmoney.SecurityGuard;

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

    public void printExportMenu() {
        System.out.println("Your accounts: " +
                SecurityGuard.getACCEPTABLE_ACCOUNT_IDs());
        System.out.println("Enter account id: ");
    }

    public void print(String s) {
        System.out.println(s);
    }
}
