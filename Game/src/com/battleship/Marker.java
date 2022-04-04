package com.battleship;




//add values for hit X, miss O, empty ""
public enum Marker {
    HIT("X"), MISS("O"), EMPTY(" ");

    public final String marker;

    private Marker(String marker) {
        this.marker = marker;
    }


}

