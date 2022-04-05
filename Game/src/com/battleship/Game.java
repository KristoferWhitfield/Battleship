package com.battleship;

import com.apps.util.Prompter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Prompter;


/**
 *  This class serves as the controller for the Battleship game.
 */
public class Game {
    private int playerCount;

    private Board playerBoardOne;
    private Board playerBoardTwo;

    private static Scanner in = new Scanner(System.in);

    public void start() throws FileNotFoundException {

        welcome();
        playerBoardOne = playerSetup();
        playerBoardOne = playerSetup();

        battle();

        exit();
    }

    private void welcome() throws FileNotFoundException {
//        System.out.println(
//                "          _      _                                        ______      \n" +
//                "          |  |  /          /                                /         \n" +
//                "        --|-/|-/-----__---/----__----__---_--_----__-------/-------__-\n" +
//                "          |/ |/    /___) /   /   ' /   ) / /  ) /___)     /      /   )\n" +
//                "        __/__|____(___ _/___(___ _(___/_/_/__/_(___ _____/______(___/_");
//        System.out.println(
//                "__________________________________________________________________________________\n" +
//                "    ____     __   ______ ______   _      _____      __     _     _     __   ____  \n" +
//                "    /   )    / |    /      /      /      /    '   /    )   /    /      /    /    )\n" +
//                "---/__ /----/__|---/------/------/------/__-------\\-------/___ /------/----/____/-\n" +
//                "  /    )   /   |  /      /      /      /           \\     /    /      /    /       \n" +
//                "_/____/___/____|_/______/______/____/_/____ ___(____/___/____/____ _/_ __/________");
        File file = new File("C:\\StudentWork\\MiniProject\\Battleship\\welcome.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
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


    // pass in the map
    private void battle() {
        ArrayList<Integer> coordinates = new ArrayList<Integer>();
        // TODO: 4/5/2022 add in validation that the user only entered a number between 0 and 9
        // TODO: 4/5/2022 convert int to string
        // TODO: 4/5/2022 add explaniation of game to notepad
        Prompter prompter = new Prompter(new Scanner(System.in));
        // tell the player what kind of coordinate 2 numbers between 0 and 9

        // both values in array list
        //x arraylist. 0 and y arraylist .1
        String xCoordinates = prompter.prompt("Please enter a number between 0 and 9 to enter the X coordinate" +
                "where you want to fire: ");
        String yCoordinates = prompter.prompt("Please enter a number between 0 and 9 to enter the Y coordinate" +
                "where you want to fire: ");

        if(Integer.valueOf(xCoordinates) > 9 || Integer.valueOf(xCoordinates) < 0){
            System.out.println("please enter a number between 0 and 9");
            xCoordinates = prompter.prompt("Please enter a number between 0 and 9 to enter the X coordinate" +
                    "where you want to fire: ");
        }

        if(Integer.valueOf(yCoordinates) > 9 || Integer.valueOf(yCoordinates) < 0){
            System.out.println("please enter a number between 0 and 9");
            yCoordinates = prompter.prompt("Please enter a number between 0 and 9 to enter the X coordinate" +
                    "where you want to fire: ");
        }

        coordinates.add(Integer.valueOf(xCoordinates));
        coordinates.add(Integer.valueOf(yCoordinates));
    }

    //might not use
    private void exit() {

    }

}