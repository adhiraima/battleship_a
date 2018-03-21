package com.battleship.model;

import com.battleship.enums.BlockState;
import com.battleship.enums.ShipState;
import com.battleship.enums.ShipType;

public class Ship {
    private ShipType shipType;
    private Block[] blocks;
    private ShipState state;

    public Ship(ShipType shipType) {
        this.shipType = shipType;
        this.blocks = new Block[this.shipType.getSize()];
        this.state = ShipState.AFLOAT;
    }

    public ShipState getState(ShipState state) {
        return this.state;
    }
    public boolean isAlive() {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].getState().equals(BlockState.OCCUPIED))
                return true;
        }
        this.state = ShipState.DEAD;
        return false;
    }
}
