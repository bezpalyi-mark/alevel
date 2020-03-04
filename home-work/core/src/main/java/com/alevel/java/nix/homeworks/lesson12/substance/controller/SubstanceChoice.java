package com.alevel.java.nix.homeworks.lesson12.substance.controller;

import com.alevel.java.nix.homeworks.lesson12.substance.model.Iron;
import com.alevel.java.nix.homeworks.lesson12.substance.model.Oxygen;
import com.alevel.java.nix.homeworks.lesson12.substance.model.Substance;
import com.alevel.java.nix.homeworks.lesson12.substance.model.Water;

public class SubstanceChoice {
    private Input input;

    private Substance substance;

    private String name;

    public SubstanceChoice() {
        input = new SubstanceInput();
    }

    Substance getSubstance() {
        int choice;
        do {
            System.out.println(
                    "Please, choose substance to manipulate with:\n" +
                            "1. Iron\n" +
                            "2. Oxygen\n" +
                            "3. Water\n" +
                            "0. Exit"

            );
            choice = input.getInt();
        } while (choice < 0 || choice > 3);

        switch (choice) {
            case 1:
                name = "Iron";
                return new Iron();
            case 2:
                name = "Oxygen";
                return new Oxygen();
            case 3:
                name = "Water";
                return new Water();
            case 0:
                return null;
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
