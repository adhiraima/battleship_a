package com.battleship.enums;

public enum ShipState {
    AFLOAT("AFLOAT"), DEAD("DEAD");

    private String state;

    ShipState(String shipState) {
        this.state = shipState;
    }

    public String getState() {
        return this.state;
    }
}
