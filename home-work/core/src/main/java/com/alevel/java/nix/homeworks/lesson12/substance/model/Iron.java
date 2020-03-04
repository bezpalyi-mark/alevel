package com.alevel.java.nix.homeworks.lesson12.substance.model;

import com.alevel.java.nix.homeworks.lesson12.substance.view.StateView;

public class Iron implements Substance {

    private double temperature;

    private State state;

    private StateView view;

    public Iron() {
        temperature = 0;
        view = new StateView();
        determineState();
    }

    private void determineState() {
        if (temperature < 1538) {
            state = State.SOLID;
        } else if (temperature >= 2861) {
            state = State.GAS;
        } else {
            state = State.LIQUID;
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
