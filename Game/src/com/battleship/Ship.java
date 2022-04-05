package com.battleship;

public class Ship {
//location is on the map
// field is sunk
// everytime ship gets hit ship loses a hitpoint

    private int shipHealth;
    private ShipType ship;
    private boolean isSunk = false;


    public Ship(ShipType ship, int shipHealth){
        setShipType(ship);
        setShipHealth(shipHealth);
    }

    public void checkShipHealth(){
        // needs to count the amount of markers
        // needs to determine if the ship is hit based on the board markers
        // make the ship take damage change marker
        if(){
            shipHealth -= 1;
        } else if (shipHealth == 0){
            isSunk = true;
            System.out.println(getShip() + " has sunk.");
        }
    }

    private void setShipType(ShipType ship) {
        this.ship = ship;
    }

    public ShipType getShip(){
        return ship;
    }

    public int getShipHealth() {
        return shipHealth;
    }

    public void setShipHealth(int shipHealth) {
        if(ship.equals(ship.CARRIER)){
            this.shipHealth = 5;
        } else if (ship.equals(ship.BATTLESHIP)){
            this.shipHealth = 4;
        } else if (ship.equals(ship.DESTROYER)){
            this.shipHealth = 3;
        } else if (ship.equals(ship.SUBMARINE)){
            this.shipHealth = 3;
        } else if (ship.equals(ship.PATROL_BOAT)){
            this.shipHealth = 2;
        }
    }

    @Override
    public String toString() {
        return "Ship{" +
                "shipHealth=" + shipHealth +
                ", ship=" + ship +
                '}';
    }
}