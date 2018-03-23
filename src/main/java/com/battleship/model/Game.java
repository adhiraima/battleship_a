package com.battleship.model;

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
    private Player currentPlayer;

    public Game(String player1, String player2) {
        this.gameID = UUID.randomUUID().toString();
        this.player1 = new Player(player1);
        this.player2 = new Player(player2);
        this.currentPlayer = this.player1;
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
        System.out.println("Welcome Player: " + this.currentPlayer.getName());
        while (scanner.next().equalsIgnoreCase(ApplicationConstants.EXIT_KEYWORD)) {
            //this.currentPlayer.
        }
    }

    public void printGrids(Player player) throws IOException {
        player.getBoard().printGrid(player.getName());
        player.getEnemy().printGrid(ApplicationConstants.ENEMY);
    }

    public Block move(Player player, Latitude latitude, Longitude longitude) {
        return null;
    }

    private void updateBoards() {
        //update boards of both the players
    }

    public String addShip(Player player, Latitude latitude, Longitude longitude) throws IOException {
        Runtime.getRuntime().exec(ApplicationConstants.CLEAR_COMMAND);

        return null;
    }
}
