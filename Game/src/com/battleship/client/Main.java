package com.battleship.client;

import com.battleship.Game;

/**
 *  This launches the controller for the Battleship game.
 */
class Main {


    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.start();
    }
}