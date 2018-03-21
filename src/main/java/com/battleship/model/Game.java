package com.battleship.model;

import com.battleship.enums.Latitude;
import com.battleship.enums.Longitude;

import java.util.UUID;

public class Game {
    private final String gameID;
    private final Player player1;
    private final Player player2;

    public Game(String player1, String player2) {
        this.gameID = UUID.randomUUID().toString();
        this.player1 = new Player(player1);
        this.player2 = new Player(player2);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void startGame() {
        // ask each player for placing the battle ships
        // move in cyclic order
    }

    public Block move(Player player, Latitude latitude, Longitude longitude) {
        return null;
    }

    private void updateBoards() {
        //update boards of both the players
    }

    public String addShip(Latitude latitude, Longitude longitude) {
        return null;
    }
}
