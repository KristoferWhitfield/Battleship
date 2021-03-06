package com.battleship;

public class Ship {
    private int shipHealth;
    private ShipType ship;
    private boolean isSunk = false;

    public Ship(ShipType ship){
        setShipType(ship);
        setShipHealth(ship.getSize());
    }

    public Ship(ShipType ship, int shipHealth){
        setShipType(ship);
        setShipHealth(shipHealth);
    }

    public void hit(){
        shipHealth--;
        if (shipHealth <= 0) {
            System.out.println(ship + " SUNK!");
            isSunk = true;
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

    public boolean isSunk() {
        return isSunk;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "shipHealth=" + shipHealth +
                ", ship=" + ship +
                '}';
    }
}