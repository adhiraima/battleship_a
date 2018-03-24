package com.battleship.model;

import com.battleship.enums.BlockState;
import com.battleship.enums.GridType;
import com.battleship.enums.Latitude;
import com.battleship.enums.Longitude;
import com.battleship.utils.ApplicationConstants;

import java.io.IOException;

public class Grid {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    private Block[][] blocks;
    private GridType gridType;

    public Grid(String gridType) {
        this.blocks = new Block[10][10];
        this.gridType = GridType.get(gridType);
    }

    public Block[][] getBlocks() {
        return this.blocks;
    }

    public void printGrid(String name) throws IOException {
        System.out.println((this.gridType.equals(GridType.ENEMY) ? ANSI_RED_BACKGROUND : ANSI_GREEN_BACKGROUND)
                + ANSI_BLACK + (null != name ? name : this.gridType.getGridType()) + ANSI_RESET);
        for(int i = 0; i < (null != name ? name.length() : this.gridType.getGridType().length()); i++) {
            System.out.print((this.gridType.equals(GridType.ENEMY) ? ANSI_RED_BACKGROUND : ANSI_GREEN_BACKGROUND)
                    + ANSI_BLACK + ApplicationConstants.UNDERLINE + ANSI_RESET);
        }
        System.out.println(ANSI_RESET);
        for (int i = 0; i <= blocks.length; i++) {
            for (int j = 0; j <= blocks[0].length; j++) {
                if (i == 0) {
                    if (j == 0) {
                        for (int k = 0; k < ApplicationConstants.LONGEST_LATITUDE_NAME_LENGTH; k++)
                            System.out.print(ANSI_CYAN_BACKGROUND + ApplicationConstants.SPACE + ANSI_RESET);
                        System.out.print(ANSI_CYAN_BACKGROUND + ApplicationConstants.SPACE + ANSI_RESET);
                    }
                    else
                        System.out.print(ANSI_CYAN_BACKGROUND + Longitude.get(j - 1) + ApplicationConstants.SPACE + ANSI_RESET);
                } else {
                    if (j == 0) {
                        for (int k = 0; k < (ApplicationConstants.LONGEST_LATITUDE_NAME_LENGTH - Latitude.get(i - 1).toString().length()); k++)
                            System.out.print(ANSI_CYAN_BACKGROUND + ApplicationConstants.SPACE + ANSI_RESET);
                        System.out.print(ANSI_CYAN_BACKGROUND + Latitude.get(i - 1) + ApplicationConstants.SPACE + ANSI_RESET);
                    } else {
                        //Prints the block toString by logic of block state and grid type
                        switch (this.gridType) {
                            case SELF: printSelfGridBlock(blocks[i-1][j-1]); break;
                            case ENEMY: printEnemyGridBlock(blocks[i-1][j-1]); break;
                            default: break;
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printSelfGridBlock(Block block) {
        switch (block.getState()) {
            case EMPTY: System.out.print(ANSI_BLACK_BACKGROUND + ApplicationConstants.SPACE
                    + ApplicationConstants.SPACE + ANSI_RESET); break;
            case OCCUPIED: System.out.print(ANSI_WHITE_BACKGROUND + ANSI_WHITE + ApplicationConstants.SPACE
                    + ApplicationConstants.SPACE + ANSI_RESET); break;
            case HIT: System.out.print(ANSI_RED_BACKGROUND + ANSI_RED + ApplicationConstants.SPACE
                    + ApplicationConstants.SPACE + ANSI_RESET); break;
            case MISS: System.out.print(ANSI_BLACK_BACKGROUND + ANSI_WHITE + ApplicationConstants.MISSED
                    + ApplicationConstants.SPACE + ANSI_RESET); break;
            default: System.out.print(ANSI_BLACK_BACKGROUND + ApplicationConstants.SPACE
                    + ApplicationConstants.SPACE + ANSI_RESET); break;
        }
    }

    private void printEnemyGridBlock(Block block) {
        switch (block.getState()) {
            case EMPTY: System.out.print(ANSI_BLACK_BACKGROUND + ApplicationConstants.SPACE
                    + ApplicationConstants.SPACE + ANSI_RESET); break;
//            case OCCUPIED: System.out.print(ANSI_BLACK_BACKGROUND + ApplicationConstants.SPACE
//                    + ApplicationConstants.SPACE + ANSI_RESET); break; //do not show the occupied for the enemy
            case HIT: System.out.print(ANSI_BLACK_BACKGROUND + ANSI_RED + ApplicationConstants.HIT
                    + ApplicationConstants.SPACE + ANSI_RESET); break;
            case MISS: System.out.print(ANSI_BLACK_BACKGROUND + ANSI_WHITE + ApplicationConstants.MISSED
                    + ApplicationConstants.SPACE + ANSI_RESET); break;
            default: System.out.print(ANSI_BLACK_BACKGROUND + ApplicationConstants.SPACE
                    + ApplicationConstants.SPACE + ANSI_RESET);
        }
    }

    public Grid initialize() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.blocks[i][j] = new Block(Latitude.get(i).toString().toCharArray()[0] , Longitude.get(j).toString().toCharArray()[0]);
            }
        }
        return this;
    }
}
