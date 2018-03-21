package com.battleship.enums;

public enum Latitude {
    ONE(0), TWO(1), THREE(2), FOUR(3), FIVE(4), SIX(5), SEVEN(6), EIGHT(7), NINE(8), TEN(9);

    private final int latitude;

    Latitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public static Latitude get(int latitude) {
        for (Latitude lat : values()) {
            if (lat.getLatitude() == latitude) {
                return lat;
            }
        }
        return null;
    }
}
