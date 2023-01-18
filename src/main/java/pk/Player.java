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
            }

            for (int i = 0; i < diceAvailable; i++) {
                Faces rollResult = dice.roll();
                calculateRollScore(rollResult);
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
