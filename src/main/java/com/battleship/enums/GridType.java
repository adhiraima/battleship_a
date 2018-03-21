package com.battleship.enums;

public enum GridType {
    SELF("SELF"), ENEMY("ENEMY");

    private final String gridType;

    GridType(String gridType) {
        this.gridType = gridType;
    }

    public String getGridType() {
        return this.gridType;
    }

    public static GridType get(String gridType) {
        if (gridType.equalsIgnoreCase(SELF.toString())) {
            return SELF;
        }
        if (gridType.equalsIgnoreCase(ENEMY.toString())) {
            return ENEMY;
        }
        return null;
    }
}
