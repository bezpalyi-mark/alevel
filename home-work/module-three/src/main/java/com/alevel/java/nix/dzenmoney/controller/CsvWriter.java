package com.alevel.java.nix.dzenmoney.controller;

import com.alevel.java.nix.dzenmoney.model.ExportOrders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Map;

public class CsvWriter {

    private static final Logger logger = LoggerFactory.getLogger(JdbcDzenMoney.class);


    private static final String headers = "id,instant,value,revenue,balance,";

    public static void writeExportOrders(Map<Long, ExportOrders> ordersMap, String path) {
        File file = new File(path);
        if(file.exists()) {
            if(!file.delete()) {
                logger.error("Cannot delete existing file");
                return;
            }
        }
        try {
            if(file.createNewFile()) {
                logger.info("file {} was created", path);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(headers).append('\n');
            for(var order : ordersMap.entrySet()) {
                 stringBuilder.append(order.getValue().getId()).append(',');
                 stringBuilder.append(order.getValue().getInstance()).append(',');
                 stringBuilder.append(order.getValue().getValue()).append(',');
                 stringBuilder.append('\n');
            }
            stringBuilder.append(" ,").append(" ,").append(" ,")
                    .append(calculateRevenue(ordersMap)).append(",").append(calculateBalance(ordersMap));
            Writer writer = new FileWriter(file);
            String s = stringBuilder.toString();
            writer.write(s);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.error("Error with work with files");
        }
    }

    private static BigDecimal calculateRevenue(Map<Long, ExportOrders> ordersMap) {
        BigDecimal result = BigDecimal.ONE;
        for (var order : ordersMap.entrySet()) {
            BigDecimal value = order.getValue().getValue();
            if(value.compareTo(BigDecimal.ZERO) > 0) {
                result = result.add(value);
            }
        }
        return result;
    }

    private static BigDecimal calculateBalance(Map<Long, ExportOrders> ordersMap) {
        BigDecimal result = BigDecimal.ONE;
        for (var order : ordersMap.entrySet()) {
            result = result.add(order.getValue().getValue());
        }
        return result;
    }
}
