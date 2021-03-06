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

    public Ship[] getShips() {
        return this.ships;
    }

    public int getAfloatShips() {
        int count = 0;
        for (Ship ship : this.ships) {
            count = ship.isAlive() ? count + 1 : count + 0;
        }
        return count;
    }

    public int getShipTypeCount(ShipType type) {
        int count = 0;

        for (int i = 0; i < this.ships.length; i++) {
            if (this.ships[i] == null) continue;
            if (this.ships[i].getShipType().equals(type))
                count++;
        }
        return count;
    }

    public boolean canPlaceMoreShips() {
        return ApplicationConstants.SHIPS_ALLOWED > this.shipCount;
    }

    public String addShip(ShipType shipType, Latitude latitude, Longitude longitude, AxialOrientation axialOrientation) {
        if (this.getShipTypeCount(shipType) == shipType.getNumber())
            return "Sorry, you cannot place anymore ships of type " + shipType.toString()+ "!";
        if (ApplicationConstants.SHIPS_ALLOWED == this.getShipCount())
            return "Sorry, you cannot place anymore ships!";
        if (this.checkPlacement(shipType, latitude, longitude, axialOrientation)) {
            Ship ship = new Ship(shipType);
            this.ships[this.shipCount] = ship;
            this.occupyBoardSpace(ship, latitude, longitude, axialOrientation);
            this.shipCount++;
            return  "Placed " + shipType.toString() + " for " + this.name + " successfully!";
        } else {
           return  "Unable to place " + shipType.toString() + " for " + this.name + "!";
        }
    }

    private void occupyBoardSpace(Ship ship, Latitude latitude, Longitude longitude, AxialOrientation axialOrientation) {
        switch (axialOrientation) {
            case HORIZONTAL: for (int i = longitude.getLongitude(); i < ship.getShipType().getSize()
                                    + longitude.getLongitude(); i++) {
                                this.board.getBlocks()[latitude.getLatitude()][i].occupy();
                                ship.addBlock(this.board.getBlocks()[latitude.getLatitude()][i]);
                             }
                             break;
            case VERTICAL:  for (int i = latitude.getLatitude(); i < ship.getShipType().getSize()
                                    + latitude.getLatitude(); i++) {
                                this.board.getBlocks()[i][longitude.getLongitude()].occupy();
                                ship.addBlock(this.board.getBlocks()[i][longitude.getLongitude()]);
                            }
                            break;
            default: break;
        }
    }

    private boolean checkPlacement(ShipType shipType, Latitude latitude, Longitude longitude,
                                   AxialOrientation axialOrientation) {
        switch (axialOrientation) {
            case HORIZONTAL: return (null != Longitude.get(longitude.getLongitude() - 1 + shipType.getSize())
                    && spaceFree(shipType, latitude, longitude, axialOrientation));
            case VERTICAL: return (null != Latitude.get(latitude.getLatitude() - 1 + shipType.getSize())
                    && spaceFree(shipType, latitude, longitude, axialOrientation));
            default: return false;
        }
    }

    private boolean spaceFree(ShipType shipType, Latitude latitude, Longitude longitude,
                              AxialOrientation axialOrientation) {
        switch (axialOrientation) {
            case HORIZONTAL: for (int i = longitude.getLongitude(); i < shipType.getSize() - 1
                                    + longitude.getLongitude(); i++) {
                if (!this.board.getBlocks()[latitude.getLatitude()][i].getState().equals(BlockState.EMPTY))
                    return false;
            }
            return true;
            case VERTICAL:  for (int i = latitude.getLatitude(); i < shipType.getSize() - 1
                                    + longitude.getLongitude(); i++) {
                if (!this.board.getBlocks()[i][longitude.getLongitude()].getState().equals(BlockState.EMPTY))
                    return false;
            }
            return true;
            default: break;
        }
        return true;
    }
}
