package com.battleship.enums;

public enum HorizontalOrientation {
    L2R("L2R"), R2L("R2L");

    private String orientation;

    HorizontalOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getOrientation() {
        return this.orientation;
    }
}
