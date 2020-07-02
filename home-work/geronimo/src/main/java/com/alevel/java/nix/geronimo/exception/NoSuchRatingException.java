package com.alevel.java.nix.geronimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchRatingException extends ResponseStatusException {
    public NoSuchRatingException(String ratingName) {
        super(HttpStatus.BAD_REQUEST, "No such rating with name " + ratingName);
    }
}
