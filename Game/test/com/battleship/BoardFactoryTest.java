package com.battleship;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

public class BoardFactoryTest {
    BoardFactory factory;

    @Before
    public void setUp() {
        factory = new BoardFactory();
    }

    @Test
    public void validateOrientation_shouldReturnTrue() {
        assertTrue(factory.validateOrientation("H"));
    }

    @Test
    public void validateCoordinate_shouldReturnTrue_validInput() {
        assertTrue(factory.validateCoordinate("A9"));
    }

    @Test
    public void validateCoordinate_shouldReturnFalse_invalidInput() {
        assertFalse(factory.validateCoordinate("A15"));
    }

    @Test
    public void validateShipLocation_shouldReturnTrue_emptyGrid() {

        Board board = factory.newInstance();
        assertTrue(factory.validateShipLocation("b1", "H", 5, board));
    }

    @Test
    public void validateShipLocation_shouldReturnFalse_outOfBounds() {
        Board board = factory.newInstance();
        assertFalse(factory.validateShipLocation("c8", "H", 5, board));
    }

    @Test
    public void validateShipLocation_shouldReturnFalse_occupiedLocation() {
        Board board = factory.newInstance();
        factory.addShip("b1", "V", ShipType.CARRIER, board.getMap());
        assertFalse(factory.validateShipLocation("c0", "H", 3, board));
    }

    @Ignore
    @Test
    public void addShipSuccessful() {
        Map<Integer, ArrayList<Marker>> map = factory.createMap();
        factory.addShip("b2", "V", ShipType.CARRIER, map);
        for (var row : map.values()) {
            System.out.println(row);
        }
    }

    @Test
    public void newRandomTest() {

        Board board = factory.newRandom();

        board.displayStrategic();
    }

    @Test
    public void createMap() {
        Map<Integer, ArrayList<Marker>> map = factory.createMap();

        for (var row : map.values()) {
            System.out.println(row);
        }
    }
}