package com.battleship.model;

import com.battleship.enums.BlockState;
import com.battleship.enums.ShipState;
import com.battleship.enums.ShipType;

public class Ship {
    private ShipType shipType;
    private Block[] blocks;
    private ShipState state;
    private int blocksCount;

    public Ship(ShipType shipType) {
        this.shipType = shipType;
        this.blocks = new Block[this.shipType.getSize()];
        this.state = ShipState.AFLOAT;
        this.blocksCount = 0;
    }

    public void addBlock(Block block) {
        this.blocks[this.blocksCount++] = block;
    }

    public boolean blocksFull() {
        return this.blocksCount == blocks.length;
    }

    public ShipState getState(ShipState state) {
        return this.state;
    }

    public ShipType getShipType() {
        return shipType;
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
