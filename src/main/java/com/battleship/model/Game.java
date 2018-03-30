package com.battleship.model;

import com.battleship.enums.BlockState;
import com.battleship.enums.Latitude;
import com.battleship.enums.Longitude;
import com.battleship.utils.ApplicationConstants;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class Game {
    private final String gameID;
    private final Player player1;
    private final Player player2;
    private final Scanner scanner;
    private boolean isGameRunning;

    public Game(String player1, String player2) {
        this.gameID = UUID.randomUUID().toString();
        this.player1 = new Player(player1);
        this.player2 = new Player(player2);
        this.isGameRunning = true;
        this.scanner = new Scanner(System.in);

    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void startGame() throws IOException {
        // ask each player for placing the battle ships
        // move in cyclic order
        while (this.isGameRunning) {
            boolean playerInput = false;
            if (!this.isGameRunning) break;
            System.out.println(ApplicationConstants.LINE_DIVIDER);
            this.printGrids(this.getPlayer1());
            System.out.println("Welcome Player " + this.getPlayer1().getName() + "!");
            String lat = ApplicationConstants.EMPTY;
            String lon = ApplicationConstants.EMPTY;
            while (!playerInput) {
                System.out.println(this.getPlayer1().getName() + " please enter the coordinates of your move (ROW and COLUMN): ");
                lat = scanner.next();
                lon = scanner.next();
                if (null != Latitude.valueOf(lat.toUpperCase())
                        && null != Longitude.valueOf(lon.toUpperCase())) {
                    playerInput = true;
                }
            }
            this.move(this.getPlayer1(), this.getPlayer2(), Latitude.valueOf(lat.toUpperCase()),
                                            Longitude.valueOf(lon.toUpperCase()));
            playerInput = false;
            this.printGrids(this.getPlayer2());
            System.out.println("Welcome Player " + this.getPlayer2().getName() + "!");
            while (!playerInput) {
                System.out.println(this.getPlayer2().getName() + " please enter the coordinates of your move (ROW and COLUMN): ");
                lat = scanner.next();
                lon = scanner.next();
                if (null != Latitude.valueOf(lat.toUpperCase())
                        && null != Longitude.valueOf(lon.toUpperCase())) {
                    playerInput = true;
                }
            }
            this.move(this.getPlayer2(), this.getPlayer1(), Latitude.valueOf(lat.toUpperCase()),
                                            Longitude.valueOf(lon.toUpperCase()));
        }
    }

    public Block move(Player player, Player enemy, Latitude latitude, Longitude longitude) throws IOException {
        Block enemyBoardBlock = player.getEnemy().getBlocks()[latitude.getLatitude()][longitude.getLongitude()];
        Block block = enemy.getBoard().getBlocks()[latitude.getLatitude()][longitude.getLongitude()];
        if (block.getState() == BlockState.OCCUPIED) {
            enemyBoardBlock.hit();
            block.hit();
            System.out.println(player.getName() + " has made a hit!");
            //check or all ships are afloat or not to decide the win
            for (Ship ship : enemy.getShips()) {
                if (!ship.isAlive()) {
                    System.out.println(player.getName() + " has sank enemy " + ship.getShipType().toString() + "!!");
                }
            }
            if (enemy.getAfloatShips() > 0)
                System.out.println("Enemy has " + enemy.getAfloatShips() + " remaining");
            else {
                this.printGrids(player);
                System.out.println("Congratulations " + player.getName() + "! you have WON!!!");
                System.exit(0);
            }

        } else if (block.getState() == BlockState.EMPTY) {
            enemyBoardBlock.miss();
            block.miss();
            System.out.println(player.getName() + ", you have missed!");
        } else if (block.getState() == BlockState.MISS) {
            enemyBoardBlock.miss();
            block.miss();
            System.out.println(player.getName() + " has missed again!");
        } else if (block.getState() == BlockState.HIT) {
            enemyBoardBlock.hit();
            block.hit();
            System.out.println(player.getName() + ", you have made a repeat hit!");
        }
        scanner.nextLine();
        return block;
    }

    private void printGrids(Player player) throws IOException {
        player.getBoard().printGrid(player.getName());
        player.getEnemy().printGrid(ApplicationConstants.ENEMY);
    }
}
