package com.battleship;

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.util.*;

import static com.battleship.Board.Color.*;
import static com.battleship.Marker.*;

/**
 *  This class serves as the battlefield and display for the game.
 */
class Board {
    private Map<Integer, ArrayList<Marker>> boardMap;
    private ArrayList<Ship> ships = new ArrayList<>();
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
        ShipType hitShip = null;

        if (mark == EMPTY) {
            boardMap.get(row).set(column, MISS);
        } else if (mark == CARRIER) {
            hitShip = ShipType.CARRIER;
            boardMap.get(row).set(column, HIT);
            result = true;
        } else if (mark == BATTLESHIP) {
            hitShip = ShipType.BATTLESHIP;
            boardMap.get(row).set(column, HIT);
            result = true;
        } else if (mark == DESTROYER) {
            hitShip = ShipType.DESTROYER;
            boardMap.get(row).set(column, HIT);
            result = true;
        } else if (mark == SUBMARINE) {
            hitShip = ShipType.SUBMARINE;
            boardMap.get(row).set(column, HIT);
            result = true;
        } else if (mark == PATROL_BOAT) {
            hitShip = ShipType.PATROL_BOAT;
            boardMap.get(row).set(column, HIT);
            result = true;
        }

        Console.clear();

        if (result) {
            for (Ship s: ships) {
                if (s.getShip().equals(hitShip)) {
                    s.hit();
                }
            }
        }

        shotResult(result);

        return result;
    }

    private void shotResult(boolean result) {
        Prompter prompter = new Prompter(new Scanner(System.in));

        Console.blankLines(1);

        if (result) {
            System.out.println("You've scored a hit on an enemies ship!");
        }
        else {
            System.out.println("That was a miss! Fire again on the next turn.");

        }
        prompter.prompt("Press enter to continue.");
        Console.clear();
    }

    public List<String> displayStrategic() {
        List<Marker> viewable =
                new ArrayList<>(Arrays.asList(Marker.EMPTY, Marker.HIT, Marker.CARRIER, Marker.BATTLESHIP,
                                              Marker.DESTROYER, Marker.SUBMARINE, Marker.PATROL_BOAT));

        return getLines(viewable);
    }

    public List<String> displayShots() {
        List<Marker> viewable = new ArrayList<>(Arrays.asList(Marker.EMPTY, Marker.MISS, Marker.HIT));

        return getLines(viewable);
    }

    public List<String> getLines(List<Marker> viewable) {
        ArrayList<String> lines = new ArrayList<>();
        List<Marker> ships = new ArrayList<>(Arrays.asList(Marker.CARRIER, Marker.BATTLESHIP,
                                                           Marker.DESTROYER, Marker.SUBMARINE, Marker.PATROL_BOAT));

        lines.add(" ---------------------------------------------");
        lines.add(" ---  0 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - ");

        for (Map.Entry<Integer, ArrayList<Marker>> row : boardMap.entrySet()) {
            lines.add(" ----" + WATER + " - - - - - - - - - - - - - - - - - - - - " + RESET);
            char ch = Character.toUpperCase(Character.forDigit(row.getKey() + 10,36));

            StringBuilder single = new StringBuilder(" " + ch + "  |" + WATER + " ");
            for (Marker m : row.getValue()) {
               single.append(WATER);
                if (viewable.contains(m)) {
                    if (m.equals(Marker.HIT)) {
                        single.append(RED).append(SHIP).append(m.getMark()).append(RESET).append(WATER).append(" : ").append(RESET);
                    }
                    else if (ships.contains(m)) {
                        single.append(SHIP).append(m.getMark()).append(RESET).append(WATER).append(" : ").append(RESET);
                    }
                    else if (m.equals(Marker.MISS)) {
                        single.append(BLACK).append(WATER).append(m.getMark()).append(RESET).append(WATER).append(" : ").append(RESET);
                    }
                    else {
                        single.append(m.getMark()).append(" : ");
                    }
                }
                else {
                    single.append("  : ");
                }
                single.append(RESET);
            }
            lines.add(single.toString());
        }
        lines.add(" ----" + WATER +" ----------------------------------------" + RESET);

        return lines;
    }

    public boolean stillAlive() {
        int count = 0;

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
        BLACK("\033[30m"),
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