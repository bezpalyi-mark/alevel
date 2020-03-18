package com.alevel.java.nix.homeworks.lesson15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForwardLinkedListTest {

    private ForwardLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new ForwardLinkedList<>();
        list.addLast("First");
        list.addLast("Second");
        list.addLast("Third");
        list.addLast("Forth");
    }

    @Test
    void get() {
        assertEquals("Forth", list.get(3));
        assertEquals("Third", list.get(2));
        assertEquals("Second", list.get(1));
        assertEquals("First", list.get(0));
        list.removeFirst();
        assertEquals("Second", list.get(0));
    }

    @Test
    void size() {
        assertEquals(4, list.size());
        list.removeFirst();
        list.removeFirst();
        assertEquals(2, list.size());
        list.removeFirst();
        list.removeFirst();
        assertEquals(0, list.size());
        list.addLast("Element");
        assertEquals(1, list.size());
    }

    @Test
    void addFirst() {
        list.addFirst("Zero");
        assertEquals("Zero", list.get(0));
        assertEquals("First", list.get(1));
        list.removeFirst();
        assertEquals("First", list.get(0));
    }

    @Test
    void addLast() {
        list.addLast("Last");
        assertEquals("Last", list.get(4));
        assertEquals("Forth", list.get(3));
        list.removeLast();
        assertEquals("Forth", list.get(3));
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(4));
    }

    @Test
    void clear() {
        assertEquals("Third", list.get(2));
        list.clear();
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(2));
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(0));
    }

    @Test
    void removeFirst() {
        assertEquals("First", list.get(0));
        assertEquals("First", list.removeFirst());

        assertEquals("Second", list.get(0));
        assertEquals("Second", list.removeFirst());

        assertEquals("Third", list.get(0));
        assertEquals("Third", list.removeFirst());

        assertEquals("Forth", list.get(0));
        assertEquals("Forth", list.removeFirst());

        assertThrows(NullPointerException.class,
                () -> list.removeFirst(),
                "There is no elements in list!");
    }

    @Test
    void removeLast() {
        assertEquals(4, list.size());
        assertEquals("Forth", list.removeLast());

        assertEquals(3, list.size());
        assertEquals("Third", list.removeLast());

        assertEquals(2, list.size());
        assertEquals("Second", list.removeLast());

        assertEquals(1, list.size());
        assertEquals("First", list.removeLast());

        assertThrows(NullPointerException.class,
                () -> list.removeLast(),
                "There is no elements in list!");
    }
}