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
        Configuration cfg = new Configuration().configure();
        ConsoleMenu menu = new ConsoleMenu();
        try(SessionFactory sessionFactory = cfg.buildSessionFactory()) {
            InitDatabase.load(sessionFactory);
            menu.run(sessionFactory);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
