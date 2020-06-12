package com.alevel.java.nix.todolist;

import com.alevel.java.nix.todolist.controller.ItemsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ToDoListApplicationTest {

    @Autowired
    private ItemsController controller;

    @Test
    void contextLoad() {
        assertNotNull(controller);
    }
}