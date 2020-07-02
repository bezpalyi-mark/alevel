package com.alevel.java.nix.geronimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CityNotFoundException extends ResponseStatusException {
    public CityNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "City with id " + id + " was not found");
    }

    public CityNotFoundException(String cityName) {
        super(HttpStatus.NOT_FOUND, "City with name " + cityName + " was not found");
    }
}
