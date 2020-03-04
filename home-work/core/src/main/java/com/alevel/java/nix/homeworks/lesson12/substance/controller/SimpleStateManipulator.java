package com.alevel.java.nix.homeworks.lesson12.substance.controller;

import com.alevel.java.nix.homeworks.lesson12.substance.model.Substance;

import java.text.MessageFormat;

public class SimpleStateManipulator {

    private Input input;

    private SubstanceChoice choice;

    public SimpleStateManipulator() {
        input = new SubstanceInput();
        choice = new SubstanceChoice();
    }

    public void begin() {
        Substance substance = choice.getSubstance();
        if(substance == null) {
            return;
        }
        System.out.println(
                MessageFormat.format(
                        "Ok. Now {0} temperature is 20(C) and it have {1} state.",
                        choice.getName(), substance.heatUp(20)));
        double temperatureChange;
        do {
            System.out.println("Enter temperature change for " + choice.getName());
            temperatureChange = input.getDouble();
            System.out.println(MessageFormat.format(
                    "Now {0} have temperature: {2} and state: {1} ",
                    choice.getName(),
                    substance.heatUp(temperatureChange),
                    substance.getTemperature()));
        } while (temperatureChange != 0);

    }
}
