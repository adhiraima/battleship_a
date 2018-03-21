package com.battleship.enums;

public enum Orientation {
    VERTICAL("VERTICAL"), HORIZONTAL("HORIZONTAL");

    private String orientation;

    Orientation(String orientation) {
        this.orientation = orientation;
    }

    public String getOrientation() {
        return this.orientation;
    }
}
