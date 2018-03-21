package com.battleship.model;


import com.battleship.enums.BlockState;

public class Block {
    private char latitude;
    private char longitude;
    private BlockState state;

    public Block(char latitude, char longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.state = BlockState.EMPTY;
    }

    public BlockState getState() {
        return this.state;
    }
}
