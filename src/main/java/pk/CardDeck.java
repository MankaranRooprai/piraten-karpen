package pk;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import java.util.HashMap;
import org.apache.logging.log4j.*;

public class CardDeck {

    private static final Logger LOGGER = LogManager.getLogger(CardDeck.class);

    List<Integer> cardDeck = new ArrayList<Integer>();
    Random rand = new Random();
    int randomKey;
    int sabersRequired = 0;
    int setScore;

    String trace = "";

    public CardDeck(String trace) {
        this.trace = trace;
    }

    // populate the card deck with monkey cards, saber cards, and nop cards
    public void populate() {
        // 2, 3, 4 are saber cards
        cardDeck.add(2);
        cardDeck.add(2);
        cardDeck.add(3);
        cardDeck.add(3);
        cardDeck.add(4);
        cardDeck.add(4);
        // 10 represents monkey card
        cardDeck.add(10);
        cardDeck.add(10);
        cardDeck.add(10);
        cardDeck.add(10);
        // 0 is an nop card
        for (int i = 0; i < 25; i++) {
            cardDeck.add(0);
        }
        // shuffle the card deck
        Collections.shuffle(cardDeck);
    }

    // draw a random card from card deck
    public int drawRandom() {

        randomKey = new Random().nextInt(cardDeck.size());

        sabersRequired = cardDeck.get(randomKey);

        return sabersRequired;
    }

    // returns bonus player needs to get
    public int bonus() {
        if (sabersRequired == 2) {
            return 3;
        } else if (sabersRequired == 3) {
            return 5;
        } else if (sabersRequired == 4) {
            return 10;
        }
        return 0;
    }

    // calculates if player has enough sabers for bonus
    public int calculateBonus(int numSabers) {
        if (numSabers >= sabersRequired) {
            if (sabersRequired == 2) {
                return 3;
            } else if (sabersRequired == 3) {
                return 5;
            } else if (sabersRequired == 4) {
                return 10;
            }
        }
        return 0;
    }

    // calculates if player has sets and does it differently depending on monkey
    // mode
    public int sets(HashMap<String, Integer> diceType, boolean monkeyMode) {

        this.setScore = 0;

        for (String key : diceType.keySet()) {
            if (!monkeyMode) {
                if (key != Faces.SKULL.toString()) {
                    if (diceType.get(key) == 3) {
                        this.setScore += 1;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 3: " + key);
                    } else if (diceType.get(key) == 4) {
                        this.setScore += 2;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 4: " + key);
                    } else if (diceType.get(key) == 5) {
                        this.setScore += 5;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 5: " + key);
                    } else if (diceType.get(key) == 6) {
                        this.setScore += 10;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 6: " + key);
                    } else if (diceType.get(key) == 7) {
                        this.setScore += 20;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 7: " + key);
                    } else if (diceType.get(key) == 8) {
                        this.setScore += 40;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 8: " + key);
                    }
                }
            } else {
                if (key != Faces.SKULL.toString() && key != Faces.PARROT.toString() && key != Faces.MONKEY.toString()) {
                    if (diceType.get(key) == 3) {
                        this.setScore += 1;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 3: " + key);
                    } else if (diceType.get(key) == 4) {
                        this.setScore += 2;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 4: " + key);
                    } else if (diceType.get(key) == 5) {
                        this.setScore += 5;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 5: " + key);
                    } else if (diceType.get(key) == 6) {
                        this.setScore += 10;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 6: " + key);
                    } else if (diceType.get(key) == 7) {
                        this.setScore += 20;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 7: " + key);
                    } else if (diceType.get(key) == 8) {
                        this.setScore += 40;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 8: " + key);
                    }
                } else if (key == Faces.PARROT.toString() || key == Faces.MONKEY.toString()) {
                    if ((diceType.get(Faces.PARROT.toString()) + diceType.get(Faces.MONKEY.toString())) == 3) {
                        this.setScore += 1;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 3: " + key);
                    } else if ((diceType.get(Faces.PARROT.toString()) + diceType.get(Faces.MONKEY.toString())) == 4) {
                        this.setScore += 2;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 4: " + key);
                    } else if ((diceType.get(Faces.PARROT.toString()) + diceType.get(Faces.MONKEY.toString())) == 5) {
                        this.setScore += 5;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 5: " + key);
                    } else if ((diceType.get(Faces.PARROT.toString()) + diceType.get(Faces.MONKEY.toString())) == 6) {
                        this.setScore += 10;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 6: " + key);
                    } else if ((diceType.get(Faces.PARROT.toString()) + diceType.get(Faces.MONKEY.toString())) == 7) {
                        this.setScore += 20;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 7: " + key);
                    } else if ((diceType.get(Faces.PARROT.toString()) + diceType.get(Faces.MONKEY.toString())) == 8) {
                        this.setScore += 40;
                        if (trace.equalsIgnoreCase("trace"))
                            LOGGER.info("SET OF 8: " + key);
                    }
                }
            }
        }

        if (trace.equalsIgnoreCase("trace"))
            LOGGER.info("SET SCORE: " + this.setScore * 100);

        return this.setScore;

    }

}
