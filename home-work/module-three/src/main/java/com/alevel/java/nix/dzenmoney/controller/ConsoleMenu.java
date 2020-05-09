package com.alevel.java.nix.dzenmoney.controller;

import com.alevel.java.nix.dzenmoney.SecurityGuard;
import com.alevel.java.nix.dzenmoney.model.ExportOrder;
import com.alevel.java.nix.dzenmoney.view.ConsoleMenuView;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ConsoleMenu {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleMenu.class);

    private final Scanner scanner = new Scanner(System.in);

    private final ConsoleMenuView view;

    public ConsoleMenu() {
        view = new ConsoleMenuView();
    }

    public void run(SessionFactory sessionFactory, String username, String password, String path, String url) {
        int input = 0;
        HibernateDzenMoney hiber = new HibernateDzenMoney(sessionFactory);
        JdbcDzenMoney jdbc = new JdbcDzenMoney(url, username, password);
        while (input != 3) {
            try {
                view.printStartMEnu();
            } catch (InputMismatchException e) {
                logger.warn("Invalid input!");
                continue;
            }
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    hiber.saveOperation();
                    break;
                case 2:
                    view.printExportMenu();
                    int accountId = scanner.nextInt();
                    if (!SecurityGuard.verifyAccountId((long) accountId)) {
                        logger.warn("Not valid account!");
                        continue;
                    }
                    view.print("Enter BEGIN date in format yyyy-MM-dd");
                    String dateFrom = scanner.next();
                    view.print("Enter END date in format yyyy-MM-dd");
                    String endDate = scanner.next();
                    Map<Long, ExportOrder> operations = jdbc.getOperations((long) accountId, dateFrom, endDate);
                    CsvWriter.writeExportOrders(operations, path);
                    break;
                case 3:
                    break;
                default:
                    logger.warn("Invalid input");
            }

        }
        hiber.close();
        jdbc.closeConnection();
    }


}
