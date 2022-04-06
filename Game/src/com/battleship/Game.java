package com.battleship;

import com.apps.util.Prompter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *  This class serves as the controller for the Battleship game.
 */
public class Game {
    private Board playerBoardOne;
    private Board playerBoardTwo;

    private Prompter prompter = new Prompter(new Scanner(System.in));
    private static Scanner in = new Scanner(System.in);

    public void start() throws FileNotFoundException {

        welcome();
        playerBoardOne = playerSetup();
        playerBoardTwo = playerSetup();

        battle();

        exit();
    }

    private void welcome() throws FileNotFoundException {
        File file = new File("welcome.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
    }

    private Board playerSetup() {
        Board board = null;
        String input = "";
        // TODO: Convert to prompter
        while (!input.matches("[yY]|[nN]")) {
            input = prompter.prompt("Would you like to play with random boards? [Y/N]: ");
        }

        if (input.matches("[yY]")) {
            board = BoardFactory.newRandom();
        }
        else {
            board = BoardFactory.newInstance();

            for (ShipType s : ShipType.values()) {
                playerInput(board, s);
            }
        }

        return board;
    }

    private void playerInput(Board board, ShipType ship) {
        boolean validLoc = false;
        String coord = null;
        String orientation = null;

        while (!validLoc) {

            coord = prompter.prompt(String.format("Enter location for the %s [A0 to J9]: ", ship),
                                    "[aA-jJ][0-9]", "" );

            orientation = prompter.prompt("[V]ertical or [H]orizontal: ", "[v|V]|[h|H]", "");

            validLoc = BoardFactory.validateShipLocation(coord, orientation, ship.getSize(), board);
        }

        BoardFactory.addShip(coord, orientation, ship, board.getMap());
    }


    // pass in the map
    private void battle() {
        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("Player 1: Take your shot.");
            gameOver = playerTurn(playerBoardOne, playerBoardTwo);

            if (!gameOver) {
                System.out.println("Player 2: Take your shot.");
                gameOver = playerTurn(playerBoardTwo, playerBoardOne);
            }
        }
    }

    private boolean playerTurn(Board playerBoard, Board opponentBoard) {
        String input;

        displayPlayerView(playerBoard, opponentBoard);

        input = prompter.prompt("Enter location to shoot at [A0 to J9]: ", "[aA-jJ][0-9]", "" );

        int row = Character.getNumericValue(input.charAt(0)) - 10;
        int column = Character.getNumericValue(input.charAt(1));

        opponentBoard.checkShot(column, row);

        return opponentBoard.stillAlive();
    }

    private void displayPlayerView(Board playerBoard, Board opponentBoard) {
        System.out.println("Your Ships");
        playerBoard.displayStrategic();
        System.out.println("Shots Taken");
        opponentBoard.displayShots();
    }

    //might not use
    private void exit() {

    }

    boolean validateCoordinate(String input) {
        return input.matches("[aA-jJ][0-9]");
    }

}