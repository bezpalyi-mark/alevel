package com.alevel.java.nix.geronimo.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class for encoding passwords by sha256 standard
 */
public class PasswordController implements PasswordEncoder {

    private final Logger logger = LoggerFactory.getLogger(PasswordEncoder.class);

    /**
     * Method encodes password.
     * @param charSequence string password.
     * @return encoded password.
     */
    @Override
    public String encode(CharSequence charSequence) {
        MessageDigest sha256;    // Chose algorithm
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
            return "";
        }

        byte[] bytes = sha256.digest(charSequence.toString().getBytes()); // Get hash code
        StringBuilder builder = new StringBuilder();        // Convert to 16 number system
        for (byte b : bytes) {
            builder.append(String.format("%02X", b));
        }

        return builder.toString();
    }

    /**
     * Method for comparing passwords.
     * @param charSequence first input password.
     * @param s second input password.
     * @return is passwords equals.
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        MessageDigest sha256 = null;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
            return false;
        }

        byte[] bytes = sha256.digest(charSequence.toString().getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02X", b));
        }

        return builder.toString().equals(s);
    }
}