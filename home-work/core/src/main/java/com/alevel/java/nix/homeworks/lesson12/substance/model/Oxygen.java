package com.alevel.java.nix.homeworks.lesson12.substance.model;

import com.alevel.java.nix.homeworks.lesson12.substance.view.StateView;

public class Oxygen implements Substance {

    private double temperature;

    private State state;

    private StateView view;

    public Oxygen() {
        temperature = 0;
        view = new StateView();
        determineState();
    }

    private void determineState() {
        if (temperature <= -220) {
            state = State.SOLID;
        } else if (temperature <= -191.7 && temperature >= -193.7) {
            state = State.LIQUID;
        } else {
            state = State.GAS;
        }
    }

    @Override
    public State heatUp(double t) {
        temperature += t;
        determineState();
        return state;
    }

    @Override
    public double getTemperature() {
        return temperature;
    }

    public StateView getView() {
        return view;
    }
}
