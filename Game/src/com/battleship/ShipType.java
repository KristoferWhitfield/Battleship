package com.battleship;
//ship type
// hitpoints
//locations
public enum ShipType {

    CARRIER(5),
    BATTLESHIP(4),
    DESTROYER( 3),
    SUBMARINE(2),
    PATROL_BOAT(1);

    private final int size;

    ShipType(int size) {
        this.size = size;
    }

    int getSize() {
        return this.size;
    }

}
