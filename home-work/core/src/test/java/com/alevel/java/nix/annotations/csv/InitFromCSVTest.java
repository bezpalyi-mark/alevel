package com.alevel.java.nix.annotations.csv;

import com.alevel.java.nix.ionio.HandlerCSV;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InitFromCSVTest {

    @Test
    void getList() throws NoSuchFileException {
        File file = new File("./src/test/resources/person.csv");
        String absolutePath = file.getAbsolutePath();
        HandlerCSV handlerCSV = new HandlerCSV(Paths.get(absolutePath));
        InitFromCSV<TestClass> initFromCSV = new InitFromCSV<>(handlerCSV, TestClass.class);
        List<TestClass> persons = initFromCSV.getList();

        assertEquals(4, persons.size());

        assertEquals("Boris", persons.get(0).name);
        assertEquals(23, persons.get(0).age);
        assertFalse(persons.get(0).isMarried);
        assertEquals(32674L, persons.get(0).special);

        assertEquals("Vanya", persons.get(1).name);
        assertEquals(74, persons.get(1).age);
        assertTrue(persons.get(1).isMarried);
        assertEquals(99999L, persons.get(1).special);

        assertEquals("Petya", persons.get(2).name);
        assertEquals(15, persons.get(2).age);
        assertFalse(persons.get(2).isMarried);
        assertEquals(287348L, persons.get(2).special);

        assertEquals("Yulia", persons.get(3).name);
        assertEquals(22, persons.get(3).age);
        assertTrue(persons.get(3).isMarried);
        assertEquals(243234L, persons.get(3).special);
    }

    public static class TestClass {
        @FillByCSV("Name")
        public String name;

        @FillByCSV("Age")
        public int age;

        @FillByCSV("Is married")
        public boolean isMarried;

        @FillByCSV("Special")
        public long special;

        public TestClass() {

        }
    }
}