package com.battleship;

import java.util.*;

import static com.battleship.Marker.*;


public class BoardFactory {
    private Scanner in = new Scanner(System.in);
    Map<Integer, ArrayList<Marker>> boardMap;
    private String input;


/*    public Board createCustom() {
        boardMap = createMap();

        for (ShipType ship : ShipType.values()) {
            addShip(boardMap, ship);
        }

        return new Board(boardMap);
    }*/

/*    private void addShip(Map<Integer, ArrayList<Marker>> map, ShipType ship) {
        boolean validCoord = false;
        boolean validLoc = false;
        boolean validOrientation = false;
        String coord = null;
        String orientation = null;

        while (!validLoc) {
            do {
                System.out.print("Enter location for the ship [A0 to J9]: ");
                input = in.next();
                validCoord = validateCoordinate(input);
            } while (!validCoord);
            coord = input;

            do {
                System.out.print("[V]ertical or [H]orizontal: ");
                input = in.next();
                validOrientation = validateOrientation(input);
            } while (!validOrientation);
            orientation = input;

           // validLoc = validateShipLocation(coord, orientation, ship.getSize(), boardMap);
        }

        addShip(coord, orientation, ship, map);
    }*/

     void addShip(String coord, String orientation, ShipType ship,
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

    boolean validateOrientation(String input) {
        return input.matches("[v|V]|[h|H]");
    }

    boolean validateCoordinate(String input) {
        return input.matches("[aA-jJ][0-9]");
    }

    boolean validateShipLocation(String coord, String orientation, int size,
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
                System.out.println("Error: Ship cannot be placed out of bounds.");
                inBounds = false;
            }
            else {
                for (int i = xa; i < xb; i++) {
                    if (board.getMap().get(ya).get(i) != EMPTY) {
                        System.out.println("Error: ship cannot be placed on " + board.getMap().get(ya).get(i));
                        spotAvailable = false;
                        break;
                    }
                }
            }
        }
        else {
            yb = ya + size;

            if (yb >= 10) {
                System.out.println("Error: Ship cannot be placed out of bounds.");
                inBounds = false;
            }
            else {
                for (int i = ya; i < yb; i++) {
                    if (board.getMap().get(i).get(xa) != EMPTY) {
                        System.out.println("Error: ship cannot be placed on " + board.getMap().get(i).get(xa));
                        spotAvailable = false;
                        break;
                    }
                }
            }
        }

        return spotAvailable && inBounds;
    }

/*    public Board newRandom() {
        boolean validCoord = false;
        boolean validLoc = false;
        boolean validOrientation = false;
        String input;
        String coord = null;
        String orientation = null;

        Map<Integer, ArrayList<Marker>> randMap = createMap();
        int rand1;
        int rand2;
        while (!validLoc) {
            do {
                System.out.print("Enter location for the ship [A0 to J9]: ");
                input = in.next();
                validCoord = validateCoordinate(input);
            } while (!validCoord);
            coord = input;

            do {
                System.out.print("[V]ertical or [H]orizontal: ");
                input = in.next();
                validOrientation = factory.validateOrientation(input);
            } while (!validOrientation);
            orientation = input;

            validLoc = factory.validateShipLocation(coord, orientation, ship.getSize(), board);
        }


        return null;
    }*/

    static Map<Integer, ArrayList<Marker>> createMap() {
        Map<Integer, ArrayList<Marker>> newMap = new HashMap();

        for (int i = 0; i < 10; i++) {
            newMap.put(i, new ArrayList<Marker>
                    (Arrays.asList(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)));
        }

        return newMap;
    }

    public static Board newInstance() {
        return new Board(createMap());
    }
}