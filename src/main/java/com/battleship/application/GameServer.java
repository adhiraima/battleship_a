package com.battleship.application;

import com.battleship.enums.AxialOrientation;
import com.battleship.enums.Latitude;
import com.battleship.enums.Longitude;
import com.battleship.enums.ShipType;
import com.battleship.model.Game;
import com.battleship.model.Player;
import com.battleship.utils.ApplicationConstants;

import java.io.IOException;
import java.util.Scanner;

public class GameServer {
    static Scanner scanner = new Scanner(System.in);
    static ShipType[] ships = ShipType.values();

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec(ApplicationConstants.CLEAR_COMMAND);
        //take player name inputs
        System.out.print("Enter Player 1 name: ");
        String p1 = scanner.nextLine();
        System.out.println(ApplicationConstants.SPACE);
        System.out.print("Enter Player 2 name: ");
        String p2 = scanner.nextLine();
        System.out.println(ApplicationConstants.SPACE);

        //create the game
        Game game = new Game(p1, p2);
        //place the ships for each player
        placeShips(game.getPlayer1());
        placeShips(game.getPlayer2());
        //start the game
        game.startGame();
    }

    private static void placeShips(Player player) throws IOException {
        while (player.canPlaceMoreShips()) {
            Runtime.getRuntime().exec(ApplicationConstants.CLEAR_COMMAND);
            player.getBoard().printGrid(player.getName());
            int shipsToPlace = ApplicationConstants.SHIPS_ALLOWED - player.getShipCount();
            System.out.println(player.getName() + " has the following " + shipsToPlace +" Ships available to place: ");
            for (int j = 0; j < ships.length; j++) {
                System.out.println((j + 1) + ". " + ShipType.get(ships[j].toString())
                        + "(" + (ships[j].getNumber() - player.getShipTypeCount(ships[j]) + ")"));
            }

            System.out.print("Please choose a ship by number: ");
            int chosenShip = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Please select Coordinates to place a ship (ROW space COLUMN): ");
            String latitude = scanner.next();
            String longitude = scanner.next();
            scanner.nextLine();
            System.out.print("Please select Orientation (VERTICAL or HORIZONTAL): ");
            String orientation = scanner.next();
            switch (chosenShip) {
                case 1: player.addShip(ShipType.BATTLESHIP,
                                        Latitude.valueOf(latitude.toUpperCase()),
                                        Longitude.valueOf(longitude.toUpperCase()),
                                        AxialOrientation.valueOf(orientation.toUpperCase())); break;
                case 2: player.addShip(ShipType.CRUISER,
                                        Latitude.valueOf(latitude.toUpperCase()),
                                        Longitude.valueOf(longitude.toUpperCase()),
                                        AxialOrientation.valueOf(orientation.toUpperCase())); break;
                case 3: player.addShip(ShipType.SUBMARINE,
                                        Latitude.valueOf(latitude.toUpperCase()),
                                        Longitude.valueOf(longitude.toUpperCase()),
                                        AxialOrientation.valueOf(orientation.toUpperCase())); break;
                case 4: player.addShip(ShipType.DESTROYER,
                                        Latitude.valueOf(latitude.toUpperCase()),
                                        Longitude.valueOf(longitude.toUpperCase()),
                                        AxialOrientation.valueOf(orientation.toUpperCase())); break;
                default: break;
            }
            scanner.nextLine();
        }
    }
}
