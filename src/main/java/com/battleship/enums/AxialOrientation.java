package com.battleship.enums;

public enum AxialOrientation {
    VERTICAL("VERTICAL"), HORIZONTAL("HORIZONTAL");

    private String orientation;

    AxialOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getOrientation() {
        return this.orientation;
    }
}
