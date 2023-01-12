package pk;

import java.util.Random;

public class Dice {

    int totalScore = 0;
    int gold = 0;
    int diamond = 0;
    int skull = 0;

    int numDices = 8;
    int numTurns = 1;

    int turnScore = 0;

    int gamesWon = 0;

    public Faces roll() {
        int howManyFaces = Faces.values().length;
        // System.out.println(" (DEBUG) there are " + howManyFaces + " faces");
        // System.out.println(" (DEBUG) " + Arrays.toString(Faces.values()));
        Random bag = new Random();
        return Faces.values()[bag.nextInt(howManyFaces)];
    }

    public int calculateTurnScore(Faces rollResult) {
        if (rollResult.toString().equals("DIAMOND") || rollResult.toString().equals("GOLD")) {
            if (rollResult.toString().equals("DIAMOND")) {
                // System.out.println("score");
                diamond += 100;
            } else if (rollResult.toString().equals("GOLD")) {
                // System.out.println("score");
                gold += 100;
            }
            turnScore = diamond + gold;
        } else if (rollResult.toString().equals("SKULL")) {
            skull++;
            numDices--;
        }

        return turnScore;

    }

    public void calculateTotalScore() {
        totalScore += turnScore;

    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getTurnScore() {
        return turnScore;
    }

    public int getSkull() {
        return skull;
    }

    public int getNumDices() {
        return numDices;
    }

    public int getNumTurns() {
        return numTurns;
    }

    public void increaseNumTurns() {
        numTurns++;
    }

    public void endTurn() {
        numDices = 8;
        numTurns = 1;
        turnScore = 0;
        skull = 0;
    }

    public void resetTurnScore() {
        turnScore = 0;
        diamond = 0;
        gold = 0;
    }

    public void resetScore() {
        diamond = 0;
        gold = 0;
        totalScore = 0;
        turnScore = 0;
    }

    public int gamesWon() {
        return gamesWon;
    }

    public void wonGame() {
        gamesWon++;
    }

}