import java.util.Random;

import pk.Dice;
import pk.Faces;

public class PiratenKarpen {

    int totalTurns = 0;

    public static void rollAllDice(Dice player) {
        // store roll result
        Faces rollResult = player.roll();
        // calculate player's turn score
        player.calculateTurnScore(rollResult);
        // System.out.println("Skulls: " + player.getSkull());
    }

    public static void turnResult(Dice player) {
        player.calculateTotalScore();
        // System.out.println("Total score: " + player.getTotalScore());
        player.resetTurnScore();
        // increase the turns player1 has by 1
        player.increaseNumTurns();
    }

    public static void turn(Dice player) {

        Random rand = new Random();

        // get the number of dice and turns both players have

        int numDices = player.getNumDices();
        int numTurns = player.getNumTurns();

        boolean firstTurn = true;

        // while player1 has not accumulated 3 skulls, keep rolling
        while (player.getSkull() < 3 && player.getTotalScore() < 6000) {
            // player1's first turn
            if (firstTurn && numTurns == 1) {

                // System.out.println("NUMBER OF DICE AVAILABLE TO ROLL: " + numDices);

                // roll each dice
                for (int i = 0; i < 8; i++) {
                    rollAllDice(player);
                }

                // if player has less than 3 skulls then calculate the total score otherwise
                // they score no points this turn
                if (player.getSkull() < 3) {
                    // calculates the total score and increases the number of turns player has
                    // played
                    turnResult(player);
                    numTurns = player.getNumTurns();
                    // update number of dices player1 will roll for every subsequent turn
                    numDices = player.getNumDices();
                    // after player1's first turn
                } else {
                    // System.out.println("TOTAL SCORE ONCE TERMINATED: " + player.getTotalScore());
                    player.endTurn();
                    break;
                }

                firstTurn = false;

            } else {

                // select dice to roll from at least 2 to a random number less than the number
                // of dice available
                // System.out.println("NUMBER OF DICE AVAILABLE TO ROLL: " + numDices);
                numDices = rand.nextInt((numDices - 2) + 1) + 2;
                // System.out.println("HOW MANY DICE I AM PICKING: " + numDices);

                // roll each dice
                for (int i = 0; i < numDices; i++) {
                    rollAllDice(player);
                }

                // if player has less than 3 skulls then calculate the total score otherwise
                // they score no points this turn
                if (player.getSkull() < 3) {
                    // calculates the total score and increases the number of turns player has
                    // played
                    turnResult(player);
                    numTurns = player.getNumTurns();
                    // update number of dices player1 will roll for every subsequent turn
                    numDices = player.getNumDices();
                    // after player1's first turn
                } else {
                    // System.out.println("TOTAL SCORE ONCE TERMINATED: " + player.getTotalScore());
                    player.endTurn();
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");

        int gamesPlayed = 1;

        // create objects of both players
        Dice player1 = new Dice();
        Dice player2 = new Dice();

        for (int i = 0; i < 42; i++) {
            // System.out.println(gamesPlayed);

            while (player1.getTotalScore() < 6000 || player2.getTotalScore() < 6000) {
                // System.out.println("Player 1 rolls: ");
                turn(player1);
                // System.out.println("Player 2 rolls: ");
                turn(player2);
            }

            while (player1.getTotalScore() >= 6000 && player2.getTotalScore() < 6000) {
                // System.out.println("Player 2 rolls: ");
                turn(player2);
            }

            while (player1.getTotalScore() < 6000 && player2.getTotalScore() >= 6000) {
                // System.out.println("Player 1 rolls: ");
                turn(player1);
            }

            if (player1.getTotalScore() > player2.getTotalScore()) {
                player1.wonGame();
                System.out.println("PLAYER 1 IS THE WINNER with " + player1.getTotalScore() + " points!");
                System.out.println("Player 2 had: " + player2.getTotalScore() + " points.");
                System.out.println("Player 1 had: " + player1.getNumTurns() + " turns.");
                System.out.println("Player 2 had: " + player2.getNumTurns() + " turns.");
            } else if (player1.getTotalScore() < player2.getTotalScore()) {
                player2.wonGame();
                System.out.println("PLAYER 2 IS THE WINNER with " + player2.getTotalScore() + " points!");
                System.out.println("Player 1 had: " + player1.getTotalScore() + " points.");
                System.out.println("Player 1 had: " + player1.getNumTurns() + " turns.");
                System.out.println("Player 2 had: " + player2.getNumTurns() + " turns.");
            } else {
                System.out.println("Both players scored " + player1.getTotalScore() + " points.");
                System.out.println("Tie.");
            }

            player1.resetScore();
            player2.resetScore();

            gamesPlayed++;

        }

        if (player1.gamesWon() > player2.gamesWon()) {
            System.out.println("PLAYER 1 IS THE WINNER with " + player1.gamesWon() + " games won!");
            System.out.println("PLAYER 2 IS THE WINNER with " + player2.gamesWon() + " games won!");
        } else if (player1.gamesWon() < player2.gamesWon()) {
            System.out.println("PLAYER 2 IS THE WINNER with " + player2.gamesWon() + " games won!");
            System.out.println("PLAYER 1 IS THE WINNER with " + player1.gamesWon() + " games won!");
        } else {
            System.out.println("Tie.");
        }

        double winPercentage1 = ((Double.valueOf(player1.gamesWon()) / 42) * 100);
        double winPercentage2 = ((Double.valueOf(player2.gamesWon()) / 42) * 100);

        System.out.printf("Player 1 win percentage: %.3f", winPercentage1);
        System.out.print("%");
        System.out.println();
        System.out.printf("Player 2 win percentage: %.3f", winPercentage2);
        System.out.print("%");
        System.out.println();
        System.out.println("That's all folks!");

    }

}
