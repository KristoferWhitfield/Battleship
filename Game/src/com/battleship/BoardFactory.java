package com.battleship;

import java.util.*;

import static com.battleship.Marker.*;

public class BoardFactory {

    static void addShip(String coord, String orientation, ShipType ship,
            Map<Integer, ArrayList<Marker>> map) {
        int xb = 0;
        int yb = 0;
        int ya = Character.getNumericValue(coord.charAt(0)) - 10;
        int xa = Character.getNumericValue(coord.charAt(1));

        Marker marker = null;
        switch (ship) {
            case CARRIER:
                marker = CARRIER;
                break;
            case BATTLESHIP:
                marker = BATTLESHIP;
                break;
            case DESTROYER:
                marker = DESTROYER;
                break;
            case SUBMARINE:
                marker = SUBMARINE;
                break;
            case PATROL_BOAT:
                marker = PATROL_BOAT;
                break;
        }

        if (orientation.equals("H")) {
            xb = xa + ship.getSize();

            for (int i = xa; i < xb; i++) {
                map.get(ya).set(i, marker);
            }

        } else {
            yb = ya + ship.getSize();

            for (int i = ya; i < yb; i++) {
                map.get(i).set(xa, marker);
            }
        }
    }

    static boolean validateShipLocation(String coord, String orientation, int size,
            Board board) {
        boolean spotAvailable = true;
        boolean inBounds = true;
        int xb = 0;
        int yb = 0;

        int ya = Character.getNumericValue(coord.charAt(0)) - 10;
        int xa = Character.getNumericValue(coord.charAt(1));

        if (orientation.equals("H")) {
            xb = xa + size;

            if (xb >= 10) {
                inBounds = false;
            }
            else {
                for (int i = xa; i < xb; i++) {
                    if (board.getMap().get(ya).get(i) != EMPTY) {
                        spotAvailable = false;
                        break;
                    }
                }
            }
        }
        else {
            yb = ya + size;

            if (yb >= 10) {
                inBounds = false;
            }
            else {
                for (int i = ya; i < yb; i++) {
                    if (board.getMap().get(i).get(xa) != EMPTY) {
                        spotAvailable = false;
                        break;
                    }
                }
            }
        }

        return spotAvailable && inBounds;
    }

    public static Board newRandom() {
        Board board = new Board(createMap());

        for (ShipType ship : ShipType.values()) {
            addRandom(board, ship);
        }

        return board;
    }

    private static void addRandom(Board board, ShipType ship) {
        boolean validLoc = false;
        String coord = null;
        String orientation = null;

        int rand1;
        int rand2;

        while (!validLoc) {
            rand1 = (int) (Math.random() *10) + 10;
            rand2 = (int) (Math.random() *10);

            char c = Character.forDigit(rand1, 36);
            coord = "" + c + rand2;

            rand1 = (int) (Math.random() * 2);

            if (rand1 == 1) {
                orientation = "V";
            } else {
                orientation = "H";
            }

            validLoc = validateShipLocation(coord, orientation, ship.getSize(), board);
        }

        addShip(coord, orientation, ship, board.getMap());
    }

    static Map<Integer, ArrayList<Marker>> createMap() {
        Map<Integer, ArrayList<Marker>> newMap = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            newMap.put(i, new ArrayList<>
                    (Arrays.asList(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)));
        }

        return newMap;
    }

    public static Board newInstance() {
        return new Board(createMap());
    }
}