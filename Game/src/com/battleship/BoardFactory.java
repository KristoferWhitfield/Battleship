package com.battleship;

import java.util.*;

import static com.battleship.Marker.*;


public class BoardFactory {
    private Scanner in = new Scanner(System.in);
    Map<Integer, ArrayList<Marker>> mapOne;
    Map<Integer, ArrayList<Marker>> mapTwo;
    private String location;
    private String input;


    public Board createCustom() {
        System.out.println("Configuring Player 1 ships.");
        mapOne = createMap();

        for (ShipType ship : ShipType.values()) {
            addShip(mapOne, ship);
        }

        System.out.println("Configuring Player 1 ships.");
        mapTwo = createMap();

        for (ShipType ship : ShipType.values()) {
            addShip(mapOne, ship);
        }

        return new Board(mapOne, mapTwo);
    }

    private void addShip(Map<Integer, ArrayList<Marker>> map, ShipType ship) {
        boolean validCoord = false;
        boolean validLoc = false;
        boolean validOrientation = false;
        String coord = null;
        String orientation = null;

        while (!validCoord || !validLoc) {
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

            validLoc = validateShipLocation(coord, orientation, ship.getSize(), map);
        }

        addShipSuccessful(coord, orientation, ship, map);
    }

     void addShipSuccessful(String coord, String orientation, ShipType ship,
            Map<Integer, ArrayList<Marker>> map) {
        int xb = 0;
        int yb = 0;
        int ya = Character.getNumericValue(coord.charAt(0)) - 10;
        int xa = Character.getNumericValue(coord.charAt(1));

        Marker marker;
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
            default:
                throw new IllegalArgumentException();
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
            Map<Integer, ArrayList<Marker>> map) {
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
            } else {
                for (int i = xa; i < xb; i++) {
                    if (map.get(ya).get(i) != EMPTY) {
                        System.out.println("Error: ship cannot be placed on " + map.get(ya).get(i));
                        spotAvailable = false;
                        break;
                    }
                }
            }
        } else {
            yb = ya + size;
            if (yb >= 10) {
                System.out.println("Error: Ship cannot be placed out of bounds.");
                inBounds = false;
            } else {
                for (int i = ya; i < yb; i++) {
                    if (map.get(i).get(xa) != EMPTY) {
                        System.out.println("Error: ship cannot be placed on " + map.get(i).get(xa));
                        spotAvailable = false;
                        break;
                    }
                }
            }
        }

        return spotAvailable && inBounds;
    }

    public Board getRandom() {
        initializeBoards();
        return null;
    }

    private void initializeBoards() {

    }

    Map<Integer, ArrayList<Marker>> createMap() {
        Map<Integer, ArrayList<Marker>> newMap = new HashMap();

        for (int i = 0; i < 10; i++) {
            newMap.put(i, new ArrayList<Marker>
                    (Arrays.asList(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)));
        }

        return newMap;
    }

    public static Board newInstance() {
        return null;
    }
}