package com.battleship;

import java.util.Scanner;

/**
 *  This class serves as the controller for the Battleship game.
 */
public class Game {
    private int playerCount;

    private Player playerOne;
    private Player playerTwo;

    private Board playerBoardOne;
    private Board playerBoardTwo;

    private static Scanner in = new Scanner(System.in);

    public void start() {

        welcome();
        playerBoardOne = playerSetup();
        playerBoardOne = playerSetup();

        battle();

        exit();
    }

    private void welcome() {
        System.out.println(
                "          _      _                                        ______      \n" +
                "          |  |  /          /                                /         \n" +
                "        --|-/|-/-----__---/----__----__---_--_----__-------/-------__-\n" +
                "          |/ |/    /___) /   /   ' /   ) / /  ) /___)     /      /   )\n" +
                "        __/__|____(___ _/___(___ _(___/_/_/__/_(___ _____/______(___/_");
        System.out.println(
                "__________________________________________________________________________________\n" +
                "    ____     __   ______ ______   _      _____      __     _     _     __   ____  \n" +
                "    /   )    / |    /      /      /      /    '   /    )   /    /      /    /    )\n" +
                "---/__ /----/__|---/------/------/------/__-------\\-------/___ /------/----/____/-\n" +
                "  /    )   /   |  /      /      /      /           \\     /    /      /    /       \n" +
                "_/____/___/____|_/______/______/____/_/____ ___(____/___/____/____ _/_ __/________");
        System.out.println("\n");
        System.out.print("1 or 2 player game? ");
        playerCount = Integer.parseInt(in.nextLine());
    }

    private Board playerSetup() {
        Board board = BoardFactory.newInstance();

        for (ShipType s : ShipType.values()) {
            playerInput(board, s);
        }

        return null;
    }

    private void playerInput(Board board, ShipType ship) {
        BoardFactory factory = new BoardFactory();
        boolean validCoord = false;
        boolean validLoc = false;
        boolean validOrientation = false;
        String input;
        String coord = null;
        String orientation = null;

        while (!validLoc) {
            do {
                System.out.print("Enter location for the ship [A0 to J9]: ");
                input = in.next();
                validCoord = factory.validateCoordinate(input);
            } while (!validCoord);
            coord = input;

            do {
                System.out.print("[V]ertical or [H]orizontal: ");
                input = in.next();
                validOrientation = factory.validateOrientation(input);
            } while (!validOrientation);
            orientation = input;

            validLoc = factory.validateShipLocation(coord, orientation, ship.getSize(), board);
        }

        factory.addShip(coord, orientation, ship, board.getMap());
    }


    private void battle() {

    }

    private void exit() {
    }

}