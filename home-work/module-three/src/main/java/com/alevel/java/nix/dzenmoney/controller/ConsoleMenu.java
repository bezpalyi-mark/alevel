package com.alevel.java.nix.dzenmoney.controller;

import com.alevel.java.nix.dzenmoney.view.ConsoleMenuView;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ConsoleMenu {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleMenu.class);

    private final Scanner scanner = new Scanner(System.in);

    ConsoleMenuView view;

    public ConsoleMenu() {
        view = new ConsoleMenuView();
    }

    public void run(SessionFactory sessionFactory) {
        int input = 0;
        HibernateDzenMoney hiber = new HibernateDzenMoney(sessionFactory);
        while (input != 3) {
            view.printStartMEnu();
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    hiber.saveOperation();
                    break;
                case 2:
                    System.out.println(2);
                    break;
                case 3:
                    break;
                default:
                    logger.warn("Invalid input");
            }

        }
        hiber.close();
    }


}
