package com.alevel.java.nix.dzenmoney.controller;

import com.alevel.java.nix.dzenmoney.model.ExportOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JdbcDzenMoney {
    private static final Logger logger = LoggerFactory.getLogger(JdbcDzenMoney.class);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dzenmoney";

    private final String DB_USR;

    private final String DB_PASSWORD;

    private Connection connection;

    public JdbcDzenMoney(String username, String password) {
        DB_USR = username;
        DB_PASSWORD = password;
        establishConnection();
    }

    private void establishConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USR, DB_PASSWORD);
            int a = 10;
        } catch (SQLException e) {
            logger.error("Error while establish connection", e);
        }
    }

    public Map<Long, ExportOrder> getOperations(Long accountId, String dateFrom, String dateTo) {
        String query = String.format("SELECT operation.id, instant, value, oc.categories_name as category FROM operation LEFT JOIN operation_category oc ON operation.id = oc.Operation_id " +
                "LEFT JOIN account a on operation.account_id = a.id WHERE  account_id = %d " +
                "AND operation.instant > '%s' AND operation.instant < '%s';", accountId, dateFrom, dateTo);
        Map<Long, ExportOrder> ordersMap = new HashMap<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("operation.id");
                String instance = resultSet.getTimestamp("instant").toString();
                BigDecimal value = resultSet.getBigDecimal("value");
                String category = resultSet.getString("category");
                ExportOrder order = ordersMap.get(id);
                if (order == null) {
                    ordersMap.put(id, ExportOrder.of(id, instance, value, category));
                } else {
                    order.addCategory(category);
                }
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return ordersMap;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Error while closing connection", e);
        }
    }
}
