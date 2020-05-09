package com.alevel.java.nix.dzenmoney.controller;

import com.alevel.java.nix.dzenmoney.InitDatabase;
import com.alevel.java.nix.dzenmoney.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernateDzenMoneyTest {
    private static SessionFactory sessionFactory;
    private static HibernateDzenMoney hibernate;

    @BeforeAll
    public static void setUp() throws IOException {
        File cfgFile = new File("./src/test/resources/hibernate.cfg.xml");
        sessionFactory = new Configuration().configure(cfgFile).buildSessionFactory();
        hibernate = new HibernateDzenMoney(sessionFactory);
        InitDatabase.load(sessionFactory, "user1@gmail.com", "/test_init.sql", HibernateDzenMoneyTest.class);
    }

    @Test
    void getAccount() {
        Account account1 = new Account(1L, new BigDecimal("20000.00"),
                new User(1L, new Username("BBB", "AAA", "CCC")));
        account1.getUser().setEmail("user1@gmail.com");
        account1.getUser().setPhoneNumber("+380994776352");
        account1.getUser().addAccount(account1);

        Account account3 = new Account(3L, new BigDecimal("1500.00"),
                new User(3L, new Username("Petrovich", "Nick", "Igorevich")));
        account3.getUser().setEmail("user3@gmail.com");
        account3.getUser().setPhoneNumber("+380932847593");
        account3.getUser().addAccount(account3);

        Account accountActual1 = hibernate.getAccount(1L);
        assertEquals(account1, accountActual1);
        assertEquals(account3, hibernate.getAccount(3L));
        assertNull(hibernate.getAccount(2L));
    }

    @Test
    void getUser() {
        User user1 = new User(1L, new Username("BBB", "AAA", "CCC"));
        user1.setEmail("user1@gmail.com");
        user1.setPhoneNumber("+380994776352");

        User user3 = new User(3L, new Username("Petrovich", "Nick", "Igorevich"));
        user3.setEmail("user3@gmail.com");
        user3.setPhoneNumber("+380932847593");

        assertNull(hibernate.getUser(2L));
        assertEquals(user1, hibernate.getUser(1L));
        assertEquals(user3, hibernate.getUser(3L));
    }

    @Test
    void containsCategories() {
        List<String> trueList = Arrays.asList("salary", "shopping");
        List<String> falseList = Arrays.asList("salary", "shopping", "rent");
        assertTrue(hibernate.containsCategories(trueList));
        assertFalse(hibernate.containsCategories(falseList));
    }

    @Test
    void getCategories() {
        List<String> expectedCategories = Arrays.asList("salary", "shopping");
        assertEquals(expectedCategories, hibernate.getCategories());
    }

    @Test
    void getCategory() {
        Category category1 = new Profit();
        category1.setCategoryID(new CategoryID(1L, "salary"));

        Category category2 = new Consumption();
        category2.setCategoryID(new CategoryID(2L, "shopping"));

        assertEquals(category1, hibernate.getCategory("salary", Profit.class));
        assertEquals(category2, hibernate.getCategory("shopping", Consumption.class));
        assertNull(hibernate.getCategory("rent", Consumption.class));
    }

    @AfterAll
    public static void end() {
        hibernate.close();
        sessionFactory.close();
    }
}