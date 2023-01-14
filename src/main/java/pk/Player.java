package pk;

import pk.Dice;
import pk.Faces;

import java.util.Random;

public class Player {

    int totalScore = 0;

    int numDices = 8;
    int numRolls = 0;
    int diceAvailable = 8;

    int gamesWon = 0;

    int gold = 0;
    int diamond = 0;
    int skulls = 0;
    int parrot = 0;
    int monkey = 0;
    int saber = 0;

    int rollScore = 0;

    Dice dice;

    public Player(Dice dice) {
        this.dice = dice;
    }

    public int calculateRollScore(Faces rollResult) {
        if (rollResult.toString().equals("DIAMOND") || rollResult.toString().equals("GOLD")) {
            if (rollResult.toString().equals("DIAMOND")) {
                // System.out.println("score");
                diamond += 100;
            } else if (rollResult.toString().equals("GOLD")) {
                // System.out.println("score");
                gold += 100;
            }
            rollScore = diamond + gold;
        } else if (rollResult.toString().equals("SKULL")) {
            skulls++;
            numDices--;
        }

        return rollScore;

    }

    public void turn() {

        Random rand = new Random();
        boolean firstRoll = true;

        while (skulls < 3 && totalScore < 6000) {

            if (firstRoll) {
                diceAvailable = 8;
                firstRoll = false;
            } else {
                diceAvailable = rand.nextInt((numDices - 2) + 1) + 2;
                // System.out.println("Number of dice total: " + numDices);
                // System.out.println("Number of rolls: " + diceAvailable);
            }

            for (int i = 0; i < diceAvailable; i++) {
                // System.out.println("DICES: " + numDices);
                Faces rollResult = dice.roll();
                // System.out.println(rollResult);
                // System.out.println("ROLL SCORE before calculation: " + rollScore);
                calculateRollScore(rollResult);
                // System.out.println("ROLL SCORE AFTER CALCULATION: " + rollScore);
            }

            if (skulls >= 3) {
                totalScore += rollScore;
                numRolls++;
                break;
            } else {
                rollScore = 0;
            }

            gold = 0;
            diamond = 0;
            diceAvailable = 0;
            numRolls++;
        }

        System.out.println("TOTAL SCORE FOR PLAYER: " + totalScore);

        numDices = 8;
        diceAvailable = 8;
        numRolls = 0;
        firstRoll = true;
        gold = 0;
        diamond = 0;
        skulls = 0;
        rollScore = 0;
        // while (dice.getSkull() < 3 && dice.getTotalScore() < 6000) {
        // if (firstTurn && numTurns == 1) {

        // System.out.println("NUMBER OF DICE AVAILABLE TO ROLL: " + numDices);

        // // roll each dice
        // for (int i = 0; i < 8; i++) {
        // dice.rollAllDice();
        // }

        // if (dice.getSkull() < 3) {
        // turnResult();
        // numTurns = getNumTurns();
        // numDices = getNumDices();
        // } else {
        // totalScore += turnScore;
        // System.out.println("TOTAL SCORE ONCE TERMINATED: " + getTotalScore());
        // turnScore = 0;
        // break;
        // }

        // firstTurn = false;

        // } else {
        // System.out.println("NUMBER OF DICE AVAILABLE TO ROLL: " + numDices);
        // numDices = rand.nextInt((numDices - 2) + 1) + 2;
        // System.out.println("HOW MANY DICE I AM PICKING: " + numDices);

        // for (int i = 0; i < numDices; i++) {
        // rollAllDice();
        // }

        // if (getSkull() < 3) {

        // turnResult();
        // numTurns = getNumTurns();
        // numDices = getNumDices();
        // } else {
        // turnResult();
        // totalScore += turnScore;
        // System.out.println("TOTAL SCORE ONCE TERMINATED: " + getTotalScore());
        // turnScore = 0;
        // break;

        // }
        // }
        // }

    }

    public int getTotalScore() {
        return totalScore;
    }

    public void wonGame() {
        gamesWon++;
    }

    public int gamesWon() {
        return gamesWon;
    }

    public void resetScore() {
        totalScore = 0;
    }

}
