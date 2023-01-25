package pk;

import pk.Dice;
import pk.Faces;
import pk.SeaBattleCards;

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    int rollCounter = 0;

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

    int setsScore = 0;
    int rollScore = 0;

    int sabersRequired = 0;
    int bonus = 0;

    boolean seaBattleMode = false;

    HashMap<String, Integer> diceType = new HashMap<String, Integer>();

    SeaBattleCards cardDeck = new SeaBattleCards();

    Dice dice;

    public Player(Dice dice) {
        this.dice = dice;
        cardDeck.populate();
        initialValues();
    }

    public void initialValues() {
        diceType.put(Faces.DIAMOND.toString(), 0);
        diceType.put(Faces.GOLD.toString(), 0);
        diceType.put(Faces.SABER.toString(), 0);
        diceType.put(Faces.SKULL.toString(), 0);
        diceType.put(Faces.MONKEY.toString(), 0);
        diceType.put(Faces.PARROT.toString(), 0);
    }

    public void sets() {
        for (String key : diceType.keySet()) {
            if (key != Faces.SKULL.toString()) {
                if (diceType.get(key) == 3) {
                    System.out.println("Set of 3: " + key);
                    setsScore += 1;
                } else if (diceType.get(key) == 4) {
                    System.out.println("Set of 4: " + key);
                    setsScore += 2;
                } else if (diceType.get(key) == 5) {
                    System.out.println("Set of 5: " + key);
                    setsScore += 5;
                } else if (diceType.get(key) == 6) {
                    System.out.println("Set of 6: " + key);
                    setsScore += 10;
                } else if (diceType.get(key) == 7) {
                    System.out.println("Set of 7: " + key);
                    setsScore += 20;
                } else if (diceType.get(key) == 8) {
                    System.out.println("Set of 8: " + key);
                    setsScore += 40;
                }
            }
        }
    }

    public int calculateRollScore(Faces rollResult) {
        if (rollResult.toString().equals("DIAMOND") || rollResult.toString().equals("GOLD")) {
            if (rollResult.toString().equals("DIAMOND")) {
                System.out.println("DIAMOND BEFORE ADDING: " + diceType.get(Faces.DIAMOND.toString()));
                diceType.put(Faces.DIAMOND.toString(), diceType.get(Faces.DIAMOND.toString()) + 1);
                System.out.println("DIAMOND AFTER ADDING: " + diceType.get(Faces.DIAMOND.toString()));
                diamond++;
            } else if (rollResult.toString().equals("GOLD")) {
                System.out.println("GOLD BEFORE ADDING: " + diceType.get(Faces.GOLD.toString()));
                diceType.put(Faces.GOLD.toString(), diceType.get(Faces.GOLD.toString()) + 1);
                System.out.println("GOLD AFTER ADDING: " + diceType.get(Faces.GOLD.toString()));
                gold++;
            }
        } else if (rollResult.toString().equals("SKULL")) {
            skulls++;
            System.out.println("SKULL: " + skulls);
            numDices--;
        } else if (rollResult.toString().equals("SABER")) {
            saber++;
            System.out.println("SABER BEFORE ADDING: " + diceType.get(Faces.SABER.toString()));
            diceType.put(Faces.SABER.toString(), diceType.get(Faces.SABER.toString()) + 1);
            System.out.println("SABER AFTER ADDING: " + diceType.get(Faces.SABER.toString()));
        } else if (rollResult.toString().equals("MONKEY")) {
            monkey++;
            System.out.println("MONKEY BEFORE ADDING: " + diceType.get(Faces.MONKEY.toString()));
            diceType.put(Faces.MONKEY.toString(), diceType.get(Faces.MONKEY.toString()) + 1);
            System.out.println("MONKEY AFTER ADDING: " + diceType.get(Faces.MONKEY.toString()));
        } else if (rollResult.toString().equals("PARROT")) {
            parrot++;
            System.out.println("PARROT BEFORE ADDING: " + diceType.get(Faces.PARROT.toString()));
            diceType.put(Faces.PARROT.toString(), diceType.get(Faces.PARROT.toString()) + 1);
            System.out.println("PARROT AFTER ADDING: " + diceType.get(Faces.PARROT.toString()));
        }

        sets();
        bonus = cardDeck.calculateBonus(diceType.get(Faces.SABER.toString()));
        rollScore = ((diamond + gold + setsScore) * 100) + bonus;
        setsScore = 0;
        System.out.println("ROLL SCORE: " + rollScore);
        return rollScore;
    }

    public void comboStrategy() {

        Random rand = new Random();
        boolean firstRoll = true;

        while (skulls < 3 && totalScore < 6000) {

            if (firstRoll) {
                sabersRequired = cardDeck.drawRandom();
                System.out.println("SABERS REQUIRED");
                if (sabersRequired > 0) {
                    seaBattleMode = true;
                    System.out.println("SEA BATTLE ACTIVATED");
                } else {
                    System.out.println("NO SEA BATTLE");
                }
                diceAvailable = 8;
            } else {
                if (!seaBattleMode) {
                    numDices = 8 - skulls - diamond - gold;
                    for (String key : diceType.keySet()) {
                        if (key != Faces.SKULL.toString() && key != Faces.DIAMOND.toString()
                                && key != Faces.GOLD.toString()) {

                            System.out.println("KEY PAIR: " + diceType.get(key));

                            if (diceType.get(key) >= 3) {
                                numDices -= diceType.get(key);
                            }
                        }
                    }
                } else {
                    numDices = 8 - saber - skulls;
                }
                if (numDices >= 2 && !seaBattleMode) {
                    diceAvailable = rand.nextInt((numDices - 2) + 1) + 2;
                } else if (numDices >= 2 && seaBattleMode) {
                    diceAvailable = numDices;
                } else {
                    sets();
                    rollScore = ((diamond + gold + setsScore) * 100) + bonus;
                    totalScore += rollScore;
                    rollScore = 0;
                    System.out.println("NUM ROLLS: " + rollCounter);
                    System.out.println();
                    System.out.println("END OF TURN");
                    System.out.println();
                    System.out.println();
                    System.out.println("TURN SCORE: " + totalScore);
                    System.out.println();
                    numRolls++;
                    break;
                }
            }

            System.out.println("NUMBER OF DICE BEING ROLLED: " + diceAvailable);

            for (int i = 0; i < diceAvailable; i++) {
                Faces rollResult = dice.roll();
                System.out.println("ROLL RESULT: " + rollResult);
                calculateRollScore(rollResult);
            }

            if (skulls >= 3) {
                rollCounter++;
                System.out.println("NUM ROLLS: " + rollCounter);
                System.out.println();
                System.out.println("END OF TURN");
                System.out.println();
                if ((sabersRequired <= diceType.get(Faces.SABER.toString())) && !firstRoll) {
                    totalScore += rollScore;
                }
                System.out.println();
                System.out.println("TURN SCORE: " + totalScore);
                System.out.println();
                numRolls++;
                break;
            } else {
                System.out.println("Roll score: " + rollScore);
                System.out.println("ROLLING AGAIN");
                rollCounter++;
                System.out.println("NUM ROLLS: " + rollCounter);
                rollScore = 0;
                System.out.println("ROLL SCORE RESET: " + rollScore);
            }

            firstRoll = false;
            // gold = 0;
            // diamond = 0;
            diceAvailable = 0;
            // setsScore = 0;
            numRolls++;
            rollScore = 0;
            // initialValues();
        }

        // System.out.println("TOTAL SCORE FOR PLAYER: " + totalScore);

        numDices = 8;
        diceAvailable = 8;
        numRolls = 0;
        firstRoll = true;
        gold = 0;
        diamond = 0;
        skulls = 0;
        saber = 0;
        monkey = 0;
        parrot = 0;
        rollScore = 0;
        setsScore = 0;
        bonus = 0;
        seaBattleMode = false;
        initialValues();

    }

    public void randomRerollStrategy() {

        Random rand = new Random();
        boolean firstRoll = true;

        while (skulls < 3 && totalScore < 6000) {

            if (firstRoll) {
                diceAvailable = 8;
            } else {
                diceAvailable = rand.nextInt((numDices - 2) + 1) + 2;
            }

            System.out.println("NUMBER OF DICE BEING ROLLED: " + diceAvailable);

            for (int i = 0; i < diceAvailable; i++) {
                Faces rollResult = dice.roll();
                System.out.println("ROLL RESULT: " + rollResult);
                calculateRollScore(rollResult);
            }

            if (skulls >= 3) {
                rollCounter++;
                System.out.println("NUM ROLLS: " + rollCounter);
                System.out.println();
                System.out.println("END OF TURN");
                System.out.println();
                if (!firstRoll) {
                    totalScore += rollScore;
                }
                System.out.println();
                System.out.println("TURN SCORE: " + totalScore);
                System.out.println();
                numRolls++;
                break;
            } else {
                System.out.println("Roll score: " + rollScore);
                System.out.println("ROLLING AGAIN");
                rollCounter++;
                System.out.println("NUM ROLLS: " + rollCounter);
                rollScore = 0;
                System.out.println("ROLL SCORE RESET: " + rollScore);
            }

            firstRoll = false;
            gold = 0;
            diamond = 0;
            diceAvailable = 0;
            setsScore = 0;
            numRolls++;
            rollScore = 0;
            initialValues();
        }

        // System.out.println("TOTAL SCORE FOR PLAYER: " + totalScore);

        numDices = 8;
        diceAvailable = 8;
        numRolls = 0;
        firstRoll = true;
        gold = 0;
        diamond = 0;
        skulls = 0;
        rollScore = 0;
        setsScore = 0;
        initialValues();
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
