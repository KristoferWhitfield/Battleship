package com.battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.battleship.Board.Colors.*;

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

    // TODO: 4/5/2022 get the coordinates from (battle) 
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

        if (result) {
            ships.get(shipSize).hit();
        }

        return result;
    }

    public void displayStrategic() {
        List<Marker> viewable =
                new ArrayList<>(Arrays.asList(Marker.EMPTY, Marker.HIT, Marker.CARRIER, Marker.BATTLESHIP,
                                              Marker.DESTROYER, Marker.SUBMARINE, Marker.PATROL_BOAT));

        display(viewable);
    }

    public void displayShots() {
        List<Marker> viewable = new ArrayList<>(Arrays.asList(Marker.EMPTY, Marker.MISS, Marker.HIT));

        display(viewable);
    }

    private void display(List<Marker> viewable) {
        System.out.println(" ---------------------------------------------");
        System.out.println(" ---  0 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 -");
        List<Marker> ships = new ArrayList<>(Arrays.asList(Marker.CARRIER, Marker.BATTLESHIP,
                                                           Marker.DESTROYER, Marker.SUBMARINE, Marker.PATROL_BOAT));

        for (Map.Entry<Integer, ArrayList<Marker>> row : boardMap.entrySet()) {
            System.out.println(" ----" + WATER + " - - - - - - - - - - - - - - - - - - - - " + RESET);
            System.out.print(" " + row.getKey() + "  |" + WATER + " ");
            for (Marker m : row.getValue()) {
                System.out.print(WATER);
                if (viewable.contains(m)) {
                    if (m.equals(Marker.HIT)) {
                        System.out.print("" + RED + SHIP + m.getMark()  + RESET + WATER + " : " + RESET);
                    }
                    else if (ships.contains(m)) {
                        System.out.print("" + SHIP + m.getMark() + RESET + WATER + " : " + RESET);
                    }
                    else {
                        System.out.print(m.getMark() + " : ");
                    }
                }
                else {
                    System.out.print("  : ");
                }
                System.out.print(RESET);
            }

            System.out.println();
        }
        System.out.println(" ---------------------------------------------");
    }


    public Map<Integer, ArrayList<Marker>> getMap() {
        return boardMap;
    }

    enum Colors {
        RESET("\033[0m"),
        RED("\033[1;31m"),
        WATER("\033[46m"),
        SHIP("\033[40m");

        private final String code;

        Colors(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }
}