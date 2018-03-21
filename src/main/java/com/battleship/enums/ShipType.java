package com.battleship.enums;

public enum ShipType {
    BATTLESHIP(1, 4), CRUISER(2, 3), SUBMARINE(3, 4), DESTROYER(4, 5);

    private final int number;
    private final int size;

    ShipType(int number, int size) {
        this.number = number;
        this.size = size;
    }

    public int getNumber() {
        return this.number;
    }

    public int getSize() {
        return this.size;
    }

    public static String get(String shipType) {
        if (shipType.equalsIgnoreCase(BATTLESHIP.toString())) {
            return BATTLESHIP.toString();
        }
        if (shipType.equalsIgnoreCase(CRUISER.toString())) {
            return CRUISER.toString();
        }
        if (shipType.equalsIgnoreCase(SUBMARINE.toString())) {
            return SUBMARINE.toString();
        }
        if (shipType.equalsIgnoreCase(DESTROYER.toString())) {
            return DESTROYER.toString();
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
