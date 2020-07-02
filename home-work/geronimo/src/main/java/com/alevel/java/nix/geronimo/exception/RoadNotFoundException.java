package com.alevel.java.nix.geronimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RoadNotFoundException extends ResponseStatusException {
    public RoadNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Road with id " + id + " was not found");
    }
}
