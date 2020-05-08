package com.alevel.java.nix.dzenmoney;

import com.alevel.java.nix.dzenmoney.controller.HibernateDzenMoney;
import com.alevel.java.nix.dzenmoney.model.Category;
import com.alevel.java.nix.dzenmoney.model.Operation;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SecurityGuard {
    private static final Logger logger = LoggerFactory.getLogger(HibernateDzenMoney.class);

    private static final String EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
            "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?" +
            "|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}" +
            "(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
//    private static final String ID = "\\d{10}";
    private static final String PHONE_NUMBER = "^\\+?3?8?(\\d{10})$";

    public static boolean verifyEmail(String input) {
        return input.matches(EMAIL);
    }

//    public static boolean verifyID(String input) {
//        return input.matches(ID);
//    }

    public static boolean verifyPhoneNUmber(String input) {
        return input.matches(PHONE_NUMBER);
    }

    public static boolean verifyOperation(Operation operation) {
        return operation.getAccount() != null
                && operation.getValue() != null
                && operation.getInstant() != null;
    }


}
