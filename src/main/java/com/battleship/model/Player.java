package com.battleship.model;

import com.battleship.enums.*;
import com.battleship.utils.ApplicationConstants;

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
        this.ships = new Ship[ApplicationConstants.SHIPS_ALLOWED];//new Ship[ShipType.BATTLESHIP.getNumber() + ShipType.CRUISER.getNumber()
                        //+ ShipType.DESTROYER.getNumber() + ShipType.SUBMARINE.getNumber()];
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

    public String placeShip(ShipType shipType, Latitude latitude, Longitude longitude, AxialOrientation axialOrientation) {
        if (ApplicationConstants.SHIPS_ALLOWED == this.ships.length)
            return "Sorry, you cannot place anymore ships!";
        if (checkPlacement(shipType, latitude, longitude, axialOrientation)) {
            Ship ship = new Ship(shipType);
            this.ships[this.shipCount++] = ship;
            occupyBoardSpace(ship, latitude, longitude, axialOrientation);
            return  "Placed " + shipType.toString() + " for " + this.name + " successfully!";
        } else {
           return  "Unable to place " + shipType.toString() + " for " + name + "!";
        }
    }

    private void occupyBoardSpace(Ship ship, Latitude latitude, Longitude longitude, AxialOrientation axialOrientation) {
        switch (axialOrientation) {
            case HORIZONTAL: for (int i = longitude.getLongitude(); i < ship.getShipType().getSize() + longitude.getLongitude(); i++) {
                                 this.board.getBlocks()[latitude.getLatitude()][i].occupy();

                             }
                             break;
            case VERTICAL:  for (int i = latitude.getLatitude(); i < ship.getShipType().getSize() + longitude.getLongitude(); i++) {
                                this.board.getBlocks()[i][longitude.getLongitude()].occupy();
                            }
                            break;
            default: break;
        }
    }

    private boolean checkPlacement(ShipType shipType, Latitude latitude, Longitude longitude, AxialOrientation axialOrientation) {
        if (axialOrientation.equals(AxialOrientation.HORIZONTAL)
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
