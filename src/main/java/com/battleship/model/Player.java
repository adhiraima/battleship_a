package com.battleship.model;

import com.battleship.enums.*;

public class Player {
    private final String name;
    private final Grid board;
    private final Grid enemy;
    private Ship[] ships;
    private int shipCount;

    public Player(String name) {
        this.shipCount = 0;
        this.name = name;
        this.board = new Grid(GridType.SELF.getGridType()).initialize();
        this.enemy = new Grid(GridType.ENEMY.getGridType()).initialize();
        this.ships = new Ship[ShipType.BATTLESHIP.getNumber() + ShipType.CRUISER.getNumber()
                        + ShipType.DESTROYER.getNumber() + ShipType.SUBMARINE.getNumber()];
    }

    public String getName() {
        return name;
    }

    public Grid getBoard() {
        return board;
    }

    public Grid getEnemy() {
        return enemy;
    }

    public int getShipCount() {
        return this.shipCount;
    }

    public String placeShip(ShipType shipType, Latitude latitude, Longitude longitude, Orientation orientation) {
        if (this.shipCount + 1 > this.ships.length)
            return "Sorry, you cannot place anymore ships!";
        Ship ship = new Ship(shipType);
        if (checkPlacement(shipType, latitude, longitude, orientation)) {
            this.ships[this.shipCount++] = ship;
           return  "Placed " + shipType.toString() + " for " + this.name + " successfully!";
        } else {
           return  "Unable to place " + shipType.toString() + " for " + name + "!";
        }
    }

    private boolean checkPlacement(ShipType shipType, Latitude latitude, Longitude longitude, Orientation orientation) {
        if (orientation.equals(Orientation.HORIZONTAL)
                && null != Longitude.get(longitude.getLongitude() + shipType.getSize())
                && spaceFree()) {
            return true;
        }
        return false;
    }

    private boolean spaceFree() {
        return true;
    }
}
