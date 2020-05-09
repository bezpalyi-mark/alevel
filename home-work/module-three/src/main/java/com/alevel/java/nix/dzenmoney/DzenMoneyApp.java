package com.alevel.java.nix.dzenmoney;

import com.alevel.java.nix.dzenmoney.controller.ConsoleMenu;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;

public class DzenMoneyApp {
    private static final Logger logger = LoggerFactory.getLogger(DzenMoneyApp.class);

    public static void main(String[] args) {
        if (args.length < 3) {
            logger.error("Too few arguments, expected 3, gives: {}", args.length);
            return;
        }
        if (!SecurityGuard.verifyEmail(args[0])) {
            logger.error("Invalid email!");
            return;
        }
        Configuration cfg = new Configuration().configure();
        cfg.getProperties().setProperty("hibernate.connection.username", args[1]);
        cfg.getProperties().setProperty("hibernate.connection.password", args[2]);
        ConsoleMenu menu = new ConsoleMenu();
        try (SessionFactory sessionFactory = cfg.buildSessionFactory()) {
            InitDatabase.load(sessionFactory, args[0], "/init_tables.sql", InitDatabase.class);
            menu.run(sessionFactory, args[1], args[2], "./module-three/output.csv");
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new UncheckedIOException(e);
        }
    }
}
