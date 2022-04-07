package com.battleship;

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
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

        instructions();

        Console.blankLines(3);
        System.out.println("PLAYER 1");

        playerBoardOne = playerSetup();

        Console.clear();
        System.out.println("PLAYER 2");

        playerBoardTwo = playerSetup();

        battle();
    }

    private void welcome() throws FileNotFoundException {
        File file = new File("data/welcome.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
    }

    private void instructions() throws FileNotFoundException {
        String response = "";
        response = prompter.prompt("Would you like to read the instructions? [Y/N]: ", "[yY]|[nN]", "");

        if (response.matches("[yY]")) {
            Console.clear();
            File file = new File("data/Instructions.txt");
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
        }
    }

    private Board playerSetup() {
        Board board = null;
        String input = "";

        input = prompter.prompt("Would you like to play with a randomly generated ship configuration? [Y/N]: ", "[yY]|[nN]", "");

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

        Console.clear();
        BoardFactory.addShip(coord, orientation, ship, board.getMap());
    }

    private void battle() throws FileNotFoundException {
        boolean gameOver = false;
        int winner = 0;
        Console.clear();

        while (!gameOver) {
            System.out.println  ("                                       Player 1: Take your shot.");
            System.out.println("                                    Your Ships    |    Shots Taken");

            gameOver = playerTurn(playerBoardOne, playerBoardTwo);
            if (gameOver) {
                winner = 1;
            }

            if (!gameOver) {
                System.out.println  ("                                       Player 2: Take your shot.");
                System.out.println("                                    Your Ships    |    Shots Taken");
                gameOver = playerTurn(playerBoardTwo, playerBoardOne);

                if(gameOver) {
                    winner = 2;
                }
            }

            if (winner > 0) {
                playerWins(winner);
            }
        }
    }

    private void playerWins(int winner) throws FileNotFoundException {
        String filepath = null;

        if (winner == 1) {
            filepath = "data/player1wins.txt";
        }
        else {
            filepath = "data/player2wins.txt";
        }

        File file = new File(filepath);
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }

        Console.blankLines(3);
        prompter.prompt("Press enter to close game.");
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
        List<String> strategicView = playerBoard.displayStrategic();
        List<String> shotView = opponentBoard.displayShots();

        for (int i = 0; i < strategicView.size(); i++) {
            System.out.println(strategicView.get(i) + "    |   " + shotView.get(i));
        }
    }
}