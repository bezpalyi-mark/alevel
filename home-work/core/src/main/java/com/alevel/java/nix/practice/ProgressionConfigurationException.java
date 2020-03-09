package com.alevel.java.nix.practice;

public class ProgressionConfigurationException extends Exception {
    private final String message;

    public ProgressionConfigurationException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
