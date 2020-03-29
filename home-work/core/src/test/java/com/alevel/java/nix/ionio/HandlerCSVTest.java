package com.alevel.java.nix.ionio;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HandlerCSVTest {

    @Test
    void getHandledCSV() throws NoSuchFileException {
        File file = new File("./src/test/resources/fileToReadCSV.csv");
        String absolutePath = file.getAbsolutePath();
        HandlerCSV handlerCSV = new HandlerCSV(Paths.get(absolutePath));

        assertEquals("23.0", handlerCSV.get(3, 4));
        assertEquals("Harvey", handlerCSV.get(15, 1));
        assertEquals("D-", handlerCSV.get(0, 8));

        assertEquals("087-65-4321", handlerCSV.get(3, "SSN"));
        assertEquals("C-", handlerCSV.get(5, "Grade"));
        String[] expectedHeaders = {
                "Last name", "First name", "SSN",
                "Test1", "Test2", "Test3",
                "Test4", "Final", "Grade"
        };
        assertArrayEquals(expectedHeaders, handlerCSV.getHeaders());

        assertEquals("", handlerCSV.get(14, "Grade"));
        assertEquals("", handlerCSV.get(15, 4));
        assertThrows(
                ArrayIndexOutOfBoundsException.class,
                () -> new HandlerCSV(Paths.get(file.getAbsolutePath())).get(16, 0)
        );
        assertThrows(
                NoSuchFileException.class,
                () -> new HandlerCSV(
                        Paths.get(absolutePath.replace("src", "sec"))
                ).get(3, 2)
        );
    }
}