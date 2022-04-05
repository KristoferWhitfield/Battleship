package com.battleship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Prompter;


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
        File file = new File("C:\StudentWork\MiniProject\Battleship\welcome.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
//        System.out.println("\n");
//        System.out.print("1 or 2 player game? ");
        playerCount = Integer.parseInt(in.nextLine());
    }

//    private HumanPlayer playerSetup() {
//
//        return null;
//    }
//
//    private ComputerPlayer computerSetup() {
//        return null;
//    }

    // prompter to input where the user will fire
    private void battle() {
        Prompter prompter = new Prompter(new Scanner(System.in));
        String fire = prompter.prompt("Please enter coordinates where to fire: ");
    }

    //might not use
    private void exit() {

    }

}