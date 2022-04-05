package com.battleship;


//add values for hit X, miss O, empty ""
public enum Marker {
    HIT("X"),
    MISS("O"),
    EMPTY(" "),
    CARRIER("C"),
    BATTLESHIP("B"),
    DESTROYER("D"),
    SUBMARINE("S"),
    PATROL_BOAT("P");

    private final String p;

    Marker(String p) {
        this.p = p;
    }

    public String getMark() {
        return p;
    }
}



