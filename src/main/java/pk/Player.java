package pk;

import java.util.Random;
import java.util.function.BiPredicate;
import java.util.HashMap;

import org.apache.logging.log4j.*;

public class Player {

    private static final Logger LOGGER = LogManager.getLogger(Player.class);

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

    int parrotMonkey = 0;

    int setsScore = 0;
    int rollScore = 0;

    int sabersRequired = 0;
    int bonus = 0;

    boolean seaBattleMode = false;
    boolean monkeyMode = false;
    boolean quit = false;

    String strategy1 = "";
    String strategy2 = "";
    String trace = "";

    HashMap<String, Integer> diceType = new HashMap<String, Integer>();

    CardDeck cardDeck = new CardDeck(trace);

    Dice dice;

    // initialize the player's card deck and hashmap for recording their dice rolls
    public Player(Dice dice, String strategy1, String strategy2, String trace) {
        this.dice = dice;
        this.strategy1 = strategy1;
        this.strategy2 = strategy2;
        this.trace = trace;
        cardDeck.populate();
        initialValues();
    }

    // populate the dice array with all dice set to 0
    public void initialValues() {
        diceType.put(Faces.DIAMOND.toString(), 0);
        diceType.put(Faces.GOLD.toString(), 0);
        diceType.put(Faces.SABER.toString(), 0);
        diceType.put(Faces.SKULL.toString(), 0);
        diceType.put(Faces.MONKEY.toString(), 0);
        diceType.put(Faces.PARROT.toString(), 0);
    }

    // method calculates roll score based off roll face
    public void calculateRollScore(Faces rollResult) {

        // check which side dice landed on and update corresponding dice in hashmap and
        // variable
        if (rollResult.toString().equals("DIAMOND") || rollResult.toString().equals("GOLD")) {
            if (rollResult.toString().equals("DIAMOND")) {
                diamond++;
                diceType.put(Faces.DIAMOND.toString(), diceType.get(Faces.DIAMOND.toString()) + 1);
            } else if (rollResult.toString().equals("GOLD")) {
                gold++;
                diceType.put(Faces.GOLD.toString(), diceType.get(Faces.GOLD.toString()) + 1);
            }
        } else if (rollResult.toString().equals("SKULL")) {
            skulls++;
            diceType.put(Faces.SKULL.toString(), diceType.get(Faces.SKULL.toString()) + 1);
        } else if (rollResult.toString().equals("SABER")) {
            saber++;
            diceType.put(Faces.SABER.toString(), diceType.get(Faces.SABER.toString()) + 1);
        } else if (rollResult.toString().equals("MONKEY")) {
            monkey++;
            diceType.put(Faces.MONKEY.toString(), diceType.get(Faces.MONKEY.toString()) + 1);
        } else if (rollResult.toString().equals("PARROT")) {
            parrot++;
            diceType.put(Faces.PARROT.toString(), diceType.get(Faces.PARROT.toString()) + 1);
        }

        // check if sea battle mode is on,
        if (seaBattleMode) {
            // and calculate potential bonus
            bonus = cardDeck.calculateBonus(diceType.get(Faces.SABER.toString()));
            if (trace.equalsIgnoreCase("trace") && bonus > 0)
                LOGGER.info("BONUS SCORE: " + bonus);
        }
    }

    public void comboStrategy() {

        boolean firstRoll = true;

        // keep rolling while player has less than 3 skulls and a total score less than
        // 6000
        while (skulls < 3 && totalScore < 6000) {

            // check if player's first roll
            if (firstRoll) {
                if (trace.equalsIgnoreCase("trace"))
                    LOGGER.info("PLAYER'S FIRST ROLL");
                int mode = cardDeck.drawRandom();
                // if card is 2, 3, or 4, it is sea battle mode
                if (mode < 10 && mode > 0) {
                    sabersRequired = mode;
                    seaBattleMode = true;
                    if (trace.equalsIgnoreCase("trace"))
                        LOGGER.info("SEA BATTLE MODE ACTIVATED! SABERS REQUIRED: " + sabersRequired);
                    // if card is 10, it is monkey mode
                } else if (mode == 10) {
                    monkeyMode = true;
                    if (trace.equalsIgnoreCase("trace"))
                        LOGGER.info("MONKEY MODE ACTIVATED!");
                }
                diceAvailable = 8;
                // if not first roll,
            } else {
                // if it isn't sea battle mode,
                if (!seaBattleMode) {
                    // and it isn't monkey mode,
                    if (!monkeyMode) {
                        // subtract skulls, diamonds, and golds from roll as those will not be rolled
                        // again
                        numDices = 8 - skulls - diamond - gold;
                        // iterate through dice hashmap to check for sets
                        for (String key : diceType.keySet()) {
                            if (key != Faces.SKULL.toString() && key != Faces.DIAMOND.toString()
                                    && key != Faces.GOLD.toString()) {

                                // if any sets of 3 or more, do not roll those dice
                                if (diceType.get(key) >= 3) {
                                    numDices -= diceType.get(key);
                                }
                            }
                        }
                        // if monkey mode then do not reroll skulls, diamond, gold, monkey, and parrot
                        // dice
                    } else {
                        numDices = 8 - skulls - diamond - gold - monkey - parrot;
                    }
                    // if sea battle mode then don't reroll sabers and skulls
                } else {
                    numDices = 8 - saber - skulls;
                }

                // if player has more than 2 dice available to reroll and it's not sea battle
                // mode,
                if (numDices >= 2 && !seaBattleMode) {
                    // if player has 2 skulls, quit game
                    if (skulls == 2) {
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("PLAYER QUIT THEIR TURN");
                        quit = true;
                        break;
                    }
                    diceAvailable = numDices;
                    // if sea battle mode and 2 dice or more,
                } else if (numDices >= 2 && seaBattleMode) {
                    // reroll all remaining dice if sea battle mode
                    diceAvailable = numDices;
                } else {
                    // if player has less than 3 dice to reroll, calculate roll score and end turn
                    rollScore = ((diamond + gold + setsScore + bonus) * 100);
                    // if no skulls and all 8 dice used, add 500 points as a gold chest
                    if (numDices == 0 && skulls == 0) {
                        rollScore += 500;
                    }
                    // add roll score to total score
                    totalScore += rollScore;
                    rollScore = 0;
                    numRolls++;
                    break;
                }
            }

            if (trace.equalsIgnoreCase("trace"))
                LOGGER.info("REROLLING " + diceAvailable + " DICE");

            // roll dice and calculate roll score
            for (int i = 0; i < diceAvailable; i++) {
                Faces rollResult = dice.roll();
                if (trace.equalsIgnoreCase("trace"))
                    LOGGER.info("YOU ROLLED A " + rollResult);
                calculateRollScore(rollResult);
            }

            // calculate score based on sets
            setsScore = cardDeck.sets(diceType, monkeyMode);
            rollScore = ((diamond + gold + setsScore + bonus) * 100);

            // end turn if player has 3 or more skulls
            if (skulls >= 3) {
                rollCounter++;

                // only add to player's score if they got the required number of sabers if in
                // sea battle mode
                if ((diceType.get(Faces.SABER.toString()) >= sabersRequired) && !firstRoll) {
                    totalScore += rollScore;
                    if (trace.equalsIgnoreCase("trace")) {
                        LOGGER.info("PLAYER COLLECTED " + rollScore + " POINTS THIS TURN");
                        LOGGER.info("PLAYER NOW HAS: " + totalScore + " POINTS");
                    }
                    // do not add to score if player didn't get the required sabers
                } else if (seaBattleMode && (diceType.get(Faces.SABER.toString()) < sabersRequired)) {
                    totalScore -= cardDeck.bonus() * 100;
                    if (trace.equalsIgnoreCase("trace"))
                        LOGGER.info("PLAYER DID NOT COLLECT THE REQUIRED SABERS AND NOW HAS: " +
                                totalScore + " POINTS");
                }
                numRolls++;
                break;
            } else {
                rollCounter++;
                rollScore = 0;
            }

            // reset roll values
            firstRoll = false;
            diceAvailable = 0;
            numRolls++;
            rollScore = 0;
            setsScore = 0;
        }

        // if player quit at 2 skulls, calculate score and add it to total score
        if (quit) {
            setsScore = cardDeck.sets(diceType, monkeyMode);
            rollScore = ((diamond + gold + setsScore + bonus) * 100);
            totalScore += rollScore;
            if (trace.equalsIgnoreCase("trace")) {
                LOGGER.info("PLAYER COLLECTED " + rollScore + " POINTS THIS TURN");
                LOGGER.info("PLAYER NOW HAS: " + totalScore + " POINTS");
            }
        }

        // reset all values
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
        parrotMonkey = 0;
        seaBattleMode = false;
        monkeyMode = false;
        quit = false;
        initialValues();

    }

    public void randomRerollStrategy() {

        Random rand = new Random();
        boolean firstRoll = true;

        while (skulls < 3 && totalScore < 6000) {

            // if first roll, draw a random card and initiate mode type
            if (firstRoll) {
                if (trace.equalsIgnoreCase("trace"))
                    LOGGER.info("PLAYER'S FIRST ROLL");
                int mode = cardDeck.drawRandom();
                if (mode < 10 && mode > 0) {
                    sabersRequired = mode;
                    seaBattleMode = true;
                    if (trace.equalsIgnoreCase("trace"))
                        LOGGER.info("SEA BATTLE MODE ACTIVATED! SABERS REQUIRED: " + sabersRequired);
                } else if (mode == 10) {
                    monkeyMode = true;
                    if (trace.equalsIgnoreCase("trace"))
                        LOGGER.info("MONKEY MODE ACTIVATED!");
                }
                diceAvailable = 8;
            } else {
                // if not first roll, reroll a random number of dice that are not skulls
                numDices = 8 - skulls;
                diceAvailable = rand.nextInt((numDices - 2) + 1) + 2;
            }

            if (trace.equalsIgnoreCase("trace"))
                LOGGER.info("REROLLING " + diceAvailable + " DICE");

            // roll dice
            for (int i = 0; i < diceAvailable; i++) {
                Faces rollResult = dice.roll();
                if (trace.equalsIgnoreCase("trace"))
                    LOGGER.info("YOU ROLLED A " + rollResult);
                calculateRollScore(rollResult);
            }

            // calculate set score and roll score
            setsScore = cardDeck.sets(diceType, monkeyMode);
            rollScore = ((diamond + gold + setsScore + bonus) * 100);

            // end turn if 3 or more skulls
            if (skulls >= 3) {
                rollCounter++;
                // check if sea battle mode is on and appropriately add/subtract points
                if ((diceType.get(Faces.SABER.toString()) >= sabersRequired) && !firstRoll) {
                    totalScore += rollScore;
                    if (trace.equalsIgnoreCase("trace")) {
                        LOGGER.info("PLAYER COLLECTED " + rollScore + " POINTS THIS TURN");
                        LOGGER.info("PLAYER NOW HAS: " + totalScore + " POINTS");
                    }
                } else if (seaBattleMode && (diceType.get(Faces.SABER.toString()) < sabersRequired)) {
                    totalScore -= cardDeck.bonus() * 100;
                    if (trace.equalsIgnoreCase("trace"))
                        LOGGER.info("PLAYER DID NOT COLLECT THE REQUIRED SABERS AND NOW HAS: " +
                                totalScore + " POINTS");
                }
                numRolls++;
                break;
            } else {
                rollCounter++;
                rollScore = 0;
            }

            // reset values for next roll but keep count of sabers for sea battle mode
            initialValues();
            if (seaBattleMode) {
                diceType.put(Faces.SABER.toString(), saber);
            }
            firstRoll = false;
            gold = 0;
            diamond = 0;
            monkey = 0;
            parrot = 0;
            diceAvailable = 0;
            setsScore = 0;
            numRolls++;
            rollScore = 0;
            parrotMonkey = 0;
        }

        // reset all values for next turns
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
        parrotMonkey = 0;
        seaBattleMode = false;
        monkeyMode = false;
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
