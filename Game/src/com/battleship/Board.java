package com.battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  This class serves as the battlefield and display for the game.
 */
class Board {
    private Map<Integer, ArrayList<Marker>> boardMap;
    private List<Ship> ships = new ArrayList<>();
    private int liveShips;

    Board(Map<Integer, ArrayList<Marker>> map) {
        boardMap = map;
        for (ShipType s : ShipType.values()) {
            ships.add(new Ship(s, s.getSize()));
        }
    }

    public boolean checkShot(int x, int y) {
        boolean result = false;
        int shipSize = -1;
        Marker mark = boardMap.get(y).get(x - 1);

        switch (mark) {
            case EMPTY:
                boardMap.get(y).set(x - 1, Marker.MISS);
                System.out.println("MISS!");
                break;
            case CARRIER:       shipSize++;
            case BATTLESHIP:    shipSize++;
            case DESTROYER:     shipSize++;
            case SUBMARINE:     shipSize++;
            case PATROL_BOAT:   shipSize++;
                boardMap.get(y).set(x - 1, Marker.HIT);
                System.out.println("HIT!");
                result = true;
                break;
            case HIT:
            case MISS:
                System.out.println("MISS!");
                break;
        }

        //TODO: check compatibility with Kris' ship methods
        if (result) {
            ships.get(shipSize).checkShipHealth();
        }

        return result;
    }

    public void displayFull() {
        System.out.println(" --------------------------------------------");
        System.out.println(" ---  0 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 -");

        for (Map.Entry<Integer, ArrayList<Marker>> row : boardMap.entrySet()) {
            System.out.println(" ---- - - - - - - - - - - - - - - - - - - - - ");
            System.out.print(" " + row.getKey() + "  | ");
            for (Marker m : row.getValue()) {
                System.out.print(m.getMark() + " : ");
            }

            System.out.println();
        }
        System.out.println(" -----------------------------------------");
    }

    public void displayShots() {
        List<Marker> viewable = new ArrayList<>(Arrays.asList(Marker.EMPTY, Marker.MISS, Marker.HIT));

        System.out.println(" --------------------------------------------");
        System.out.println(" ---  0 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 -");

        for (Map.Entry<Integer, ArrayList<Marker>> row : boardMap.entrySet()) {
            System.out.println(" ---- - - - - - - - - - - - - - - - - - - - - ");
            System.out.print(" " + row.getKey() + "  | ");
            for (Marker m : row.getValue()) {
                if (viewable.contains(m)) {
                    System.out.print(m.getMark() + " : ");
                }
                else {
                    System.out.print("  : ");
                }
            }

            System.out.println();
        }
        System.out.println(" --------------------------------------------");
    }

    public Map<Integer, ArrayList<Marker>> getMap() {
        return boardMap;
    }
}