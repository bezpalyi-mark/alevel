package com.alevel.java.nix.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ListItemNotFoundException extends ResponseStatusException {

    public ListItemNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "List Item with id " + id + " was not found");
    }
}
