import java.util.Random;

import pk.Dice;
import pk.Faces;

public class PiratenKarpen {

    public static void turn(Dice player) {

        Random rand = new Random();

        // get the number of dice and turns both players have

        int numDices = player.getNumDices();
        int numTurns = player.getNumTurns();

        // while player1 has not accumulated 3 skulls, keep rolling
        while (player.getSkull() < 3) {
            // player1's first turn
            if (numTurns == 1) {

                // roll each dice
                for (int i = 0; i < 8; i++) {
                    // store roll result
                    Faces rollResult = player.roll();
                    // calculate player's turn score
                    player.calculateTurnScore(rollResult);
                }

                // if player has less than 3 skulls then calculate the total score otherwise
                // they score no points this turn
                if (player.getSkull() < 3) {
                    player.calculateTotalScore();
                    // increase the turns player1 has by 1
                    player.increaseNumTurns();
                    numTurns = player.getNumTurns();
                    // update number of dices player1 will roll for every subsequent turn
                    numDices = player.getNumDices();
                    // after player1's first turn
                } else {
                    player.endTurn();
                    break;
                }
            } else if (numTurns > 1) {

                // select dice to roll from at least 2 to a random number less than the number
                // of dice available
                numDices -= rand.nextInt((numDices - 2) + 1) + 2;

                // roll each dice
                for (int i = 0; i < numDices; i++) {
                    // store roll result
                    Faces rollResult = player.roll();
                    // calculate player 1's score
                    player.calculateTurnScore(rollResult);
                }

                // if player has less than 3 skulls then calculate the total score otherwise
                // they score no points this turn
                if (player.getSkull() < 3) {
                    player.calculateTotalScore();
                    // increase the turns player1 has by 1
                    player.increaseNumTurns();
                    numTurns = player.getNumTurns();
                    // update number of dices player1 will roll for every subsequent turn
                    numDices = player.getNumDices();
                } else {
                    player.endTurn();
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");

        // create objects of both players
        Dice player1 = new Dice();
        Dice player2 = new Dice();

        while (player1.getTotalScore() < 6000 || player2.getTotalScore() < 6000) {
            System.out.println("Player 1 rolls: ");
            turn(player1);
            System.out.println("Player 2 rolls: ");
            turn(player2);
        }

        // while (player1.getTotalScore() >= 6000 && player2.getTotalScore() < 6000) {
        // System.out.println("Player 2 rolls: ");
        // turn(player2);
        // }

        // while (player1.getTotalScore() < 6000 && player2.getTotalScore() >= 6000) {
        // System.out.println("Player 1 rolls: ");
        // turn(player1);
        // }

        System.out.println("That's all folks!");

        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //

        // while both players scores are less than 6000 (winning score),
        // while (player1.getScore() < 6000 && player2.getScore() < 6000) {
        // if (player1.getScore() >= 6000) {

        // } else if (player2.getScore() >= 6000) {

        // } else {
        // for (int j = 0; j < 8; j++) {
        // Faces rollResult1 = player1.roll();
        // if (player1.getSkull() == 3) {
        // break;
        // } else {
        // player1.calculateScore(rollResult1);

        // }
        // // System.out.println("Player 1 rolls a: " + rollResult1);
        // }

        // for (int k = 0; k < 8; k++) {
        // Faces rollResult2 = player2.roll();
        // if (player2.getSkull() == 3) {
        // break;
        // } else {
        // player1.calculateScore(rollResult2);

        // }
        // // System.out.println("Player 2 rolls a: " + rollResult2);
        // }
        // }

        // }

        // System.out.println("That's all folks!");

    }

}
