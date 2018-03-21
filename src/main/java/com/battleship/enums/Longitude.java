package com.battleship.enums;

public enum Longitude {
    A(0), B(1), C(2), D(3), E(4), F(5), G(6), H(7), I(8), J(9);

    private final int longitude;

    Longitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public static Longitude get(int longitude) {
        for (Longitude lon : values()) {
            if (lon.getLongitude() == longitude) {
                return lon;
            }
        }
        return null;
    }
}
