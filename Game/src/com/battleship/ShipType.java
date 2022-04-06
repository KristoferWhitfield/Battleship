package com.battleship;

public enum ShipType {

    CARRIER(5),
    BATTLESHIP(4),
    DESTROYER( 3),
    SUBMARINE(3),
    PATROL_BOAT(2);

    private final int size;

    ShipType(int size) {
        this.size = size;
    }

    int getSize() {
        return this.size;
    }
}
