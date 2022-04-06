package com.battleship;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.battleship.Marker.*;

public class BoardTest {
    Map<Integer, ArrayList<Marker>> testMap;
    Board board;

    @Before
    public void setUp() {
        testMap = createGenericMap();
        board = new Board(testMap);
    }

    @Test
    public void displayFull() {
        board.displayStrategic();
    }

    @Test
    public void displayShots() {
        board.displayShots();
    }

    public Map<Integer, ArrayList<Marker>> createGenericMap() {
        Map<Integer, ArrayList<Marker>> map = new HashMap<>();

        map.put(0, new ArrayList<Marker>
                (Arrays.asList(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BATTLESHIP, BATTLESHIP, BATTLESHIP, BATTLESHIP, EMPTY)));
        map.put(1, new ArrayList<Marker>
                (Arrays.asList(CARRIER, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)));
        map.put(2, new ArrayList<Marker>
                (Arrays.asList(CARRIER, PATROL_BOAT, PATROL_BOAT, EMPTY, MISS, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)));
        map.put(3, new ArrayList<Marker>
                (Arrays.asList(HIT, EMPTY, EMPTY, MISS, EMPTY, MISS, EMPTY, EMPTY, EMPTY, EMPTY)));
        map.put(4, new ArrayList<Marker>
                (Arrays.asList(HIT, EMPTY, EMPTY, EMPTY, MISS, EMPTY, MISS, EMPTY, EMPTY, EMPTY)));
        map.put(5, new ArrayList<Marker>
                (Arrays.asList(CARRIER, EMPTY, EMPTY, MISS, EMPTY, MISS, EMPTY, EMPTY, EMPTY, SUBMARINE)));
        map.put(6, new ArrayList<Marker>
                (Arrays.asList(EMPTY, EMPTY, EMPTY, EMPTY, MISS, EMPTY, MISS, EMPTY, EMPTY, HIT)));
        map.put(7, new ArrayList<Marker>
                (Arrays.asList(EMPTY, EMPTY, EMPTY, MISS, EMPTY, MISS, EMPTY, MISS, EMPTY, SUBMARINE)));
        map.put(8, new ArrayList<Marker>
                (Arrays.asList(EMPTY, DESTROYER, DESTROYER, DESTROYER, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)));
        map.put(9, new ArrayList<Marker>
                (Arrays.asList(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)));

        return map;
    }
}