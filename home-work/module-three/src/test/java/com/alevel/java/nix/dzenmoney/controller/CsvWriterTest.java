package com.alevel.java.nix.dzenmoney.controller;

import com.alevel.java.nix.dzenmoney.model.ExportOrder;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CsvWriterTest {

    @Test
    void writeExportOrders() {
        ExportOrder exportOrder1 = ExportOrder.of(1L, "2013-10-23 14:24:48.0", new BigDecimal("40000"), "salary");
        ExportOrder exportOrder2 = ExportOrder.of(2L, "2014-11-30 23:41:04.0", new BigDecimal("-1500"), "shopping");
        Map<Long, ExportOrder> map = new HashMap<>();
        map.put(1L, exportOrder1);
        map.put(2L, exportOrder2);
        CsvWriter.writeExportOrders(map, "./src/test/output.cav");
        List<String> fileEntry = Arrays.asList(
                "id,instant,value,revenue,balance,",
                "1,2013-10-23 14:24:48.0,40000,",
                "2,2014-11-30 23:41:04.0,-1500,",
                " , , ,40001,38501"
                );
        int index = 0;
        try (RandomAccessFile file = new RandomAccessFile("./src/test/output.cav", "r")) {
            String line;
            while (file.getFilePointer() != file.length()) {
                line = file.readLine();
                assertEquals(fileEntry.get(index), line);
                index++;
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}