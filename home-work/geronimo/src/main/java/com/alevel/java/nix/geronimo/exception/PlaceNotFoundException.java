package com.alevel.java.nix.geronimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PlaceNotFoundException extends ResponseStatusException {
    public PlaceNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Place with id " + id + " was not found");
    }

    public PlaceNotFoundException(String name) {
        super(HttpStatus.NOT_FOUND, "Place with name " + name + " was not found");
    }
}
