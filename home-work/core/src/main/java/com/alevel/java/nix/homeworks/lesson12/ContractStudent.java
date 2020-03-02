package com.alevel.java.nix.homeworks.lesson12;

import java.text.MessageFormat;

public class ContractStudent extends Student {
    double contractCost;

    public double getContractCost() {
        return contractCost;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Name: {0}, contract cost: {1}", name, contractCost);
    }
}
