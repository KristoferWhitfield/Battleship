package com.battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *  This class serves as the battlefield and display for the game.
 */
class Board {
    private Map<Integer, ArrayList<Marker>> boardOne;
    private Map<Integer, ArrayList<Marker>> boardTwo;
    private Ship[] shipsOne;
    private Ship[] shipsTwo;

    Board(Map<Integer, ArrayList<Marker>> map, Map<Integer, ArrayList<Marker>> map2) {
        boardOne = map;
        boardTwo = map2;
    }

    public boolean checkShot(int x, int y) {
        return false;
    }

    public void display(Map<Integer, ArrayList<Marker>> board) {
        System.out.println(" ----------------------------------------");
        System.out.println(" ---  0 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 9 -");
        System.out.println(" 0 |    |   |   |   |   |   |   |   |   |");

        for (Map.Entry<Integer, ArrayList<Marker>> row : board.entrySet()) {
            System.out.print(" " + row.getKey() + " | ");
            for (Marker m : row.getValue()) {
                System.out.print(" | " + m + " |");
            }

            System.out.println();
        }
        System.out.println(" -----------------------------------------");

    }
}