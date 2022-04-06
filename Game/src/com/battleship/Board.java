package com.battleship;

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.util.*;

import static com.battleship.Board.Color.*;

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

        liveShips = ships.size();
    }

    public boolean checkShot(int column, int row) {
        boolean result = false;
        int shipSize = -1;
        Marker mark = boardMap.get(row).get(column);

        switch (mark) {
            case EMPTY:
                boardMap.get(row).set(column, Marker.MISS);
                break;
            case CARRIER:       shipSize++;
            case BATTLESHIP:    shipSize++;
            case DESTROYER:     shipSize++;
            case SUBMARINE:     shipSize++;
            case PATROL_BOAT:   shipSize++;
                boardMap.get(row).set(column, Marker.HIT);
                result = true;
                break;
            case HIT:
            case MISS:
                break;
        }

        if (result) {
            ships.get(shipSize).hit();
        }

        shotResult(result);

        return result;
    }

    private void shotResult(boolean result) {
        Prompter prompter = new Prompter(new Scanner(System.in));
        Console.clear();
        Console.blankLines(1);

        if (result) {
            System.out.println("H H  I  TTT");
            System.out.println("HHH  I   T");
            System.out.println("H H  I   T");
        }
        else {
            System.out.println("MISS");

        }
        prompter.prompt("Press enter to continue.");
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
            char ch = Character.toUpperCase(Character.forDigit(row.getKey() + 10,36));

            System.out.print(" " + ch + "  |" + WATER + " ");
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

    public boolean stillAlive() {
        boolean result;
        int count = 0;
        //return ships.stream().filter(Ship::isSunk)
        //            .count() != liveShips;

        for(Ship s : ships) {
            if (s.isSunk()) {
                count++;
            }
        }

        return count == ships.size();
    }


    public Map<Integer, ArrayList<Marker>> getMap() {
        return boardMap;
    }

    public static enum Color {
        RESET("\033[0m"),
        RED("\033[1;31m"),
        WATER("\033[46m"),
        SHIP("\033[40m");

        private final String code;

        Color(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }
}