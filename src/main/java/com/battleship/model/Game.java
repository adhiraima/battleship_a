package com.battleship.model;

import com.battleship.enums.BlockState;
import com.battleship.enums.Latitude;
import com.battleship.enums.Longitude;
import com.battleship.enums.ShipType;
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

        while (true) {
            if (!this.isGameRunning) break;
            Runtime.getRuntime().exec(ApplicationConstants.CLEAR_COMMAND);
            this.printGrids(this.getPlayer1());
            System.out.println("Welcome Player " + this.getPlayer1().getName() + "!");
            System.out.print("Please enter the coordinates of your move (ROW and COLUMN): ");
            String lat = scanner.nextLine();
            String lon = scanner.nextLine();
            this.move(this.getPlayer1(), this.getPlayer2(), Latitude.valueOf(lat.toUpperCase()),
                                            Longitude.valueOf(lon.toUpperCase()));


            this.printGrids(this.getPlayer2());
            System.out.println("Welcome Player " + this.getPlayer1().getName() + "!");
            System.out.print("Please enter the coordinates of your move (ROW and COLUMN): ");
            lat = scanner.nextLine();
            lon = scanner.nextLine();
            this.move(this.getPlayer2(), this.getPlayer1(), Latitude.valueOf(lat.toUpperCase()),
                                            Longitude.valueOf(lon.toUpperCase()));
        }
    }

    private void printGrids(Player player) throws IOException {
        player.getBoard().printGrid(player.getName());
        player.getEnemy().printGrid(ApplicationConstants.ENEMY);
    }

    public Block move(Player player, Player enemy, Latitude latitude, Longitude longitude) {
        Block enemyBoardBlock = player.getEnemy().getBlocks()[latitude.getLatitude()][longitude.getLongitude()];
        Block block = enemy.getBoard().getBlocks()[latitude.getLatitude()][longitude.getLongitude()];
        if (block.getState() == BlockState.OCCUPIED) {
            enemyBoardBlock.hit();
            block.hit();
            System.out.println(player.getName() + " has made a hit!");
        } else if (block.getState() == BlockState.EMPTY) {
            enemyBoardBlock.miss();
            block.miss();
            System.out.println(player.getName() + ", you have missed!");
        } else if (block.getState() == BlockState.MISS) {
            enemyBoardBlock.miss();
            block.miss();
            System.out.println(player.getName() + " has missed!");
        } else if (block.getState() == BlockState.HIT) {
            enemyBoardBlock.hit();
            block.hit();
            System.out.println(player.getName() + ", you have made a hit!");
        }
        return block;
    }
}
