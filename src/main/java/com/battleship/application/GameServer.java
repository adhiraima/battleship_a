package com.battleship.application;

import com.battleship.enums.AxialOrientation;
import com.battleship.enums.Latitude;
import com.battleship.enums.Longitude;
import com.battleship.enums.ShipType;
import com.battleship.model.Game;
import com.battleship.model.Player;
import com.battleship.utils.ApplicationConstants;
import com.battleship.utils.DisplayUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GameServer {
    static Scanner scanner = new Scanner(System.in);
    static ShipType[] ships = ShipType.values();

    public static void main(String[] args) throws IOException {
        System.out.println(ApplicationConstants.LINE_DIVIDER);
        String[] clearCommand = {"bash", ApplicationConstants.CLEAR_COMMAND};
        Runtime.getRuntime().exec(clearCommand);
        //take player name inputs
        System.out.println("Enter Player 1 name: ");
        String p1 = scanner.nextLine();
        System.out.println(ApplicationConstants.SPACE);
        System.out.println("Enter Player 2 name: ");
        String p2 = scanner.nextLine();
        System.out.println(ApplicationConstants.SPACE);
        System.out.println(ApplicationConstants.LINE_DIVIDER);
        //create the game
        Game game = new Game(p1, p2);
        //place the ships for each player
        placeShips(game.getPlayer1());
        DisplayUtils.printPage();
        System.out.println(ApplicationConstants.LINE_DIVIDER);
        placeShips(game.getPlayer2());
        DisplayUtils.printPage();
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
            boolean inputComplete = false;
            int chosenShip = Integer.MIN_VALUE;
//            String latPattern = "[1-10]|[one|ONE|One]|[two|TWO|Two]|[three|THREE|Three]|[four|FOUR|Four]|[five|FIVE|Five]|[six|SIX|Six]|[seven|SEVEN|Seven]|[eight|EIGHT|Eight]|[nine|NINE|Nine]|[ten|TEN|Ten]";
//            String lonPattern = "[a-jA-J]";
            String latitude = ApplicationConstants.EMPTY;
            String longitude = ApplicationConstants.EMPTY;
            String orientation = ApplicationConstants.EMPTY;
            while (!inputComplete) {
                System.out.println("Please choose a ship by number: ");
                chosenShip = scanner.nextInt();
                System.out.println("Please select Coordinates to place a ship (ROW space COLUMN): ");
                latitude = scanner.next();
                longitude = scanner.next();
                System.out.println("Please select Orientation (VERTICAL or HORIZONTAL): ");
                orientation = scanner.next();
                if ((chosenShip >= 1 && chosenShip <= ShipType.values().length)
                        && (null != Latitude.valueOf(latitude.toUpperCase())
                                && null != Longitude.valueOf(longitude.toUpperCase()))
                        && (orientation.equalsIgnoreCase("HORIZONTAL")
                                || orientation.equalsIgnoreCase("VERTICAL"))) {
                    inputComplete = true;
                } else {
                    System.out.println(ApplicationConstants.LINE_DIVIDER);
                    System.out.println("Wrong input.. please try again!!");
                    System.out.println(ApplicationConstants.LINE_DIVIDER);
                }
            }

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
