package com.alevel.java.nix.dzenmoney.controller;

import com.alevel.java.nix.dzenmoney.model.ExportOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JdbcDzenMoneyTest {

    @BeforeAll
    public static void setUp() throws IOException {
        HibernateDzenMoneyTest.setUp();
    }

    @Test
    void getOperations() {
        JdbcDzenMoney jdbcDzenMoney = new JdbcDzenMoney("jdbc:mysql://localhost:3306/dzen_test", "root", "123456");
        ExportOrder exportOrder1 = ExportOrder.of(1L, "2013-10-23 14:24:48.0", new BigDecimal("40000.00"), "salary");
        ExportOrder exportOrder2 = ExportOrder.of(2L, "2014-11-30 23:41:04.0", new BigDecimal("-1500.00"), "shopping");
        Map<Long, ExportOrder> expectedOrders = Map.of(1L, exportOrder1, 2L, exportOrder2);
        Map<Long, ExportOrder> expectedEmpty = Collections.emptyMap();
        Map<Long, ExportOrder> actual = jdbcDzenMoney.getOperations(1L, "2000-10-10", "2020-10-10");
        assertEquals(expectedOrders, actual);
        assertEquals(expectedEmpty, jdbcDzenMoney.getOperations(1L, "10-10-2000", "10-10-2020"));
        assertEquals(expectedEmpty, jdbcDzenMoney.getOperations(1L, "2021-10-10", "2022-10-10"));
    }

    @AfterAll
    public static void end() {
        HibernateDzenMoneyTest.end();
    }
}