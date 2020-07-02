package com.alevel.java.nix.geronimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CategoryNotFoundException extends ResponseStatusException {
    public CategoryNotFoundException(String categoryName) {
        super(HttpStatus.NOT_FOUND, "Category with name " + categoryName + " was not found");
    }

    public CategoryNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Category with id " + id + " was not found");
    }
}
