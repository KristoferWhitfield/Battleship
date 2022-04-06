package com.battleship;

import java.util.*;

import static com.battleship.Marker.*;

public class BoardFactory {
    static Board board = newInstance();

    static void addShip(String coord, String orientation, ShipType ship,
            Map<Integer, ArrayList<Marker>> map) {
        int hEnd = 0;
        int vEnd = 0;
        int vStart = Character.getNumericValue(coord.charAt(0)) - 10;
        int hStart = Character.getNumericValue(coord.charAt(1));

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

        if (orientation.equalsIgnoreCase("h")) {
            hEnd = hStart + ship.getSize();

            for (int i = hStart; i < hEnd; i++) {
                map.get(vStart).set(i, marker);
            }

        } else {
            vEnd = vStart + ship.getSize();

            for (int i = vStart; i < vEnd; i++) {
                map.get(i).set(hStart, marker);
            }
        }

        board = new Board(map);
        List<String> display = board.displayStrategic();
        for (String s: display) {
            System.out.println(s);
        }
        System.out.println();
    }

    static boolean validateShipLocation(String coord, String orientation, int size,
            Board board) {
        boolean spotAvailable = true;
        boolean inBounds = true;
        int hEnd = 0;
        int vEnd = 0;

        int vStart = Character.getNumericValue(coord.charAt(0)) - 10;
        int hStart = Character.getNumericValue(coord.charAt(1));

        if (orientation.equalsIgnoreCase("h")) {
            hEnd = hStart + size - 1;

            if (hEnd >= 10) {
                inBounds = false;
            }
            else {
                for (int i = hStart; i <= hEnd; i++) {
                    if (board.getMap().get(vStart).get(i) != EMPTY) {
                        spotAvailable = false;
                    }
                }
            }
        }
        else {
            vEnd = vStart + size - 1;

            if (vEnd >= 10) {
                inBounds = false;
            }
            else {
                for (int i = vStart; i <= vEnd; i++) {
                    Marker markTest = board.getMap().get(i).get(hStart);
                    System.out.println("VTEST: " + markTest);
                    if (board.getMap().get(i).get(hStart) != EMPTY) {
                        spotAvailable = false;
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