package com.battleship.enums;

public enum BlockState {
    HIT("HIT"), MISS("MISS"), EMPTY("EMPTY"), OCCUPIED("OCCUPIED");

    public String state;

    BlockState(String state) {
        this.state = state;
    }

    String getState() {
        return this.state;
    }
}
