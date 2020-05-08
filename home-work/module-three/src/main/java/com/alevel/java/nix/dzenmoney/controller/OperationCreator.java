package com.alevel.java.nix.dzenmoney.controller;

import com.alevel.java.nix.dzenmoney.SecurityGuard;
import com.alevel.java.nix.dzenmoney.model.Category;
import com.alevel.java.nix.dzenmoney.model.Consumption;
import com.alevel.java.nix.dzenmoney.model.Operation;
import com.alevel.java.nix.dzenmoney.model.Profit;
import com.alevel.java.nix.dzenmoney.view.ConsoleMenuView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OperationCreator {
    private static final Logger logger = LoggerFactory.getLogger(OperationCreator.class);


    private Operation operation;

    ConsoleMenuView view;

    private final Scanner scanner;

    public OperationCreator() {
        view = new ConsoleMenuView();
        scanner = new Scanner(System.in);
    }

    private Operation updateBalance(List<String> inputCategories, HibernateDzenMoney hiber) {
        if (SecurityGuard.verifyOperation(operation) || inputCategories.size() == 0) {
            if (operation.getValue().compareTo(BigDecimal.ZERO) > 0) {
                if (hiber.containsCategories(inputCategories)) {
                    BigDecimal temp = BigDecimal.ZERO;
                    temp = temp.add(operation.getAccount().getBalance());
                    for (String category : inputCategories) {
                        Category newCategory = hiber.getCategory(category, Profit.class);
                        if (newCategory == null) {
                            logger.error("Selected different category types");
                            return null;
                        }
                        operation.addCategory(newCategory);
                    }
                    operation.getAccount().setBalance(temp.add(operation.getValue()));
                }
            } else if (operation.getValue().compareTo(BigDecimal.ZERO) < 0) {
                if (hiber.containsCategories(inputCategories)) {
                    BigDecimal previous = BigDecimal.ZERO;
                    previous = previous.add(operation.getAccount().getBalance());
                    BigDecimal current = previous.add(operation.getValue());
                    if (current.compareTo(BigDecimal.ZERO) >= 0) {
                        operation.getAccount().setBalance(current);
                        for (String category : inputCategories) {
                            operation.addCategory(hiber.getCategory(category, Consumption.class));
                        }
                    } else {
                        logger.error("Not enough money on balance: \n Current: {} need: {} ",
                                previous, operation.getValue().abs());
                        return null;
                    }
                }
            } else {
                logger.error("Invalid operation");
                return null;
            }
            return operation;
        } else {
            logger.warn("Not all fields are entered");
            return null;
        }
    }

    public Operation createOperation(HibernateDzenMoney hiber) {
        operation = new Operation();
        operation.setInstant(Instant.now());
        List<String> inputCategories = new ArrayList<>();
        int choice;
        while (true) {
            view.printOperationMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    operation.setAccount(hiber.getAccount(readId(scanner)));
                    break;
                case 2:
                    view.printCategories(hiber.getCategories());
                    System.out.println("Enter payment appointment:");
                    inputCategories.add(new Scanner(System.in).nextLine());
                    break;
                case 3:
                    operation.setValue(readValue(scanner));
                    break;
                case 4:
                    return updateBalance(inputCategories, hiber);
            }
        }
    }

    public Long readId(Scanner scanner) {
        view.print("Your accounts Ids: " + SecurityGuard.getACCEPTABLE_ACCOUNT_IDs());
        System.out.println("Enter account ID:");
        boolean acceptable = false;
        long value = 0L;
        while (!acceptable) {
            try {
                value = scanner.nextLong();
                if (SecurityGuard.verifyAccountId(value)) {
                    acceptable = true;
                } else {
                    logger.warn("Not acceptable account!");
                }
            } catch (InputMismatchException e) {
                logger.warn("Incorrect input");
            }
        }
        return value;
    }

    public BigDecimal readValue(Scanner scanner) {
        System.out.println("Enter transaction value:");
        boolean acceptable = false;
        BigDecimal value = null;
        while (!acceptable) {
            try {
                value = new BigDecimal(scanner.next());
                acceptable = true;
            } catch (InputMismatchException e) {
                logger.warn("Incorrect input");
            }
        }
        return value;
    }
}
