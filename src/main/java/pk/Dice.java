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

    public static Faces roll() {
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

    public void rollAllDice() {
        // store roll result
        Faces rollResult = roll();
        // calculate player's turn score
        calculateTurnScore(rollResult);
        // System.out.println("Skulls: " + player.getSkull());
    }

    public void turnResult() {
        calculateTotalScore();
        // System.out.println("Total score: " + player.getTotalScore());
        resetTurnScore();
        // increase the turns player1 has by 1
        increaseNumTurns();
    }

    public void turn() {

        Random rand = new Random();

        // get the number of dice and turns both players have

        int numDices = getNumDices();
        int numTurns = getNumTurns();

        boolean firstTurn = true;

        // while player1 has not accumulated 3 skulls, keep rolling
        while (getSkull() < 3 && getTotalScore() < 6000) {
            // player1's first turn
            if (firstTurn && numTurns == 1) {

                // System.out.println("NUMBER OF DICE AVAILABLE TO ROLL: " + numDices);

                // roll each dice
                for (int i = 0; i < 8; i++) {
                    rollAllDice();
                }

                // if player has less than 3 skulls then calculate the total score otherwise
                // they score no points this turn
                if (getSkull() < 3) {
                    // calculates the total score and increases the number of turns player has
                    // played
                    turnResult();
                    numTurns = getNumTurns();
                    // update number of dices player1 will roll for every subsequent turn
                    numDices = getNumDices();
                    // after player1's first turn
                } else {
                    // System.out.println("TOTAL SCORE ONCE TERMINATED: " + player.getTotalScore());
                    turnResult();
                    endTurn();
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
                    rollAllDice();
                }

                // if player has less than 3 skulls then calculate the total score otherwise
                // they score no points this turn
                if (getSkull() < 3) {
                    // calculates the total score and increases the number of turns player has
                    // played
                    turnResult();
                    numTurns = getNumTurns();
                    // update number of dices player1 will roll for every subsequent turn
                    numDices = getNumDices();
                    // after player1's first turn
                } else {
                    turnResult();
                    // System.out.println("TOTAL SCORE ONCE TERMINATED: " + player.getTotalScore());
                    endTurn();
                    break;
                }
            }
        }

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