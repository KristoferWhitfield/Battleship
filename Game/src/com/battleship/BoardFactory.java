package com.battleship;

import java.util.*;

import static com.battleship.Marker.*;

/**
 *
 */
class BoardFactory {
    private Scanner in = new Scanner(System.in);
    Map<Integer, ArrayList<Marker>> mapOne;
    Map<Integer, ArrayList<Marker>> mapTwo;
    private String location;
    private String input;


    public Board createCustom() {


        System.out.println("Configuring Player 1 ships.");

        mapOne = createMap();

        for (Ship ship : Ship.values()) {
            addShip(mapOne, ship);
        }

        return null;
    }

    private void addShip(Map<Integer, ArrayList<Marker>> map, Ship ship) {
        boolean validCoord = false;
        boolean validLoc = false;
        boolean validOrientation = false;

        String coord;
        String orientation;
        while (!validCoord || !validLoc) {
            System.out.print("Enter location for the ship [A0 to J9]: ");
            input = in.next();

            while (!validCoord) {
                validCoord = validateCoordinate(input);
            }
            coord = input;

            while (!validOrientation) {
                System.out.print("[V]ertical or [H]orizontal: ");
                input = in.next();
                validateOrientation(input);
            }
            orientation = input;

            while (!validLoc) {
                validLoc = validateShipLocation(coord, orientation, mapOne);
            }
        }
    }

    private boolean validateOrientation(String input) {
        return input.matches("[vV|hH]");
    }

    private boolean validateCoordinate(String input) {
        boolean success = false;

        if (input.matches("[aA-jJ][0-9]")) {
            success = true;
        }
        else {
            System.out.println("Incorrect coordinates given: ");
        }
        return success;
    }

    private boolean validateShipLocation(String coord, String orientation, Map<Integer, ArrayList<Marker>> mapOne) {
        boolean success = false;

        if (input.matches("[A-J][0-9]")) {

        }


        return success;
    }

    public Board getRandom() {
        initializeBoards();
        return null;
    }

    private void initializeBoards() {

    }

    private Map<Integer, ArrayList<Marker>> createMap() {
        Map newMap = new HashMap<Integer, ArrayList<Marker>>();

        for (int i = 0; i < 10; i++) {
            newMap.put(i, new ArrayList<Marker>
                    (Arrays.asList(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)));
        }

    }

    public static Board newInstance() {
        return null;
    }
}