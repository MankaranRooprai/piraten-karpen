package pk;

import java.util.Random;
import pk.Player;

public class Dice {

    public Faces roll() {
        int howManyFaces = Faces.values().length;
        // System.out.println(" (DEBUG) there are " + howManyFaces + " faces");
        // System.out.println(" (DEBUG) " + Arrays.toString(Faces.values()));
        Random bag = new Random();
        return Faces.values()[bag.nextInt(howManyFaces)];
    }

    // public void rollAllDice() {
    // Faces rollResult = roll();
    // // player.calculateTurnScore(rollResult);
    // System.out.println("Skulls: " + getSkull());
    // System.out.println("Turn score: " + turnScore);
    // }

    // public void turnResult() {
    // System.out.println("Total score: " + getTotalScore());
    // resetTurnScore();
    // increaseNumTurns();
    // }

    // public void calculateTotalScore() {
    // totalScore += turnScore;
    // }

    // public int getTotalScore() {
    // return totalScore;
    // }

    // public int getTurnScore() {
    // return turnScore;
    // }

    // public int getSkull() {
    // return skull;
    // }

    // public int getNumDices() {
    // return numDices;
    // }

    // public int getNumTurns() {
    // return numTurns;
    // }

    // public void increaseNumTurns() {
    // numTurns++;
    // }

    // public void endTurn() {
    // numDices = 8;
    // numTurns = 1;
    // turnScore = 0;
    // skull = 0;
    // }

    // public void resetTurnScore() {
    // diamond = 0;
    // gold = 0;
    // turnScore = 0;
    // }

    // public void resetScore() {
    // diamond = 0;
    // gold = 0;
    // totalScore = 0;
    // turnScore = 0;
    // }

    // public int gamesWon() {
    // return gamesWon;
    // }

    // public void wonGame() {
    // gamesWon++;
    // }

}