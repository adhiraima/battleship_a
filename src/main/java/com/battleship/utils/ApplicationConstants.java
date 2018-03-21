package com.battleship.utils;

import com.battleship.enums.ShipType;

public interface ApplicationConstants {
    public static final String SPACE = " ";
    public static final int LONGEST_LATITUDE_NAME_LENGTH = 5;
    public static final int SHIPS_ALLOWED = ShipType.values().length;
    public static final String UNDERLINE = "-";
    public static final String CLEAR_COMMAND = "clear";
    public static final String MISSED = "O";
    public static final String HIT = "X";
}
