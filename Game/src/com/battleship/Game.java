package com.battleship;

import java.util.Scanner;

/**
 *  This class serves as the controller for the Battleship game.
 */
public class Game {
    private int playerCount;

    private Player playerOne;
    private Player playerTwo;

    private Board gameBoard;

    private Scanner in = new Scanner(System.in);

    public void start() {

        welcome();
        gameBoard = BoardFactory.newInstance();

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

    private HumanPlayer playerSetup() {

        return null;
    }

    private ComputerPlayer computerSetup() {
        return null;
    }

    private void battle() {

    }

    private void exit() {
    }

}