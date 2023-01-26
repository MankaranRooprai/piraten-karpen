package pk;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardDeck {

    List<Integer> cardDeck = new ArrayList<Integer>();
    Random rand = new Random();
    int randomKey;
    int sabersRequired = 0;

    public void populate() {
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
        for (int i = 0; i < 25; i++) {
            cardDeck.add(0);
        }
        Collections.shuffle(cardDeck);
    }

    public int drawRandom() {

        randomKey = new Random().nextInt(cardDeck.size());

        sabersRequired = cardDeck.get(randomKey);

        return sabersRequired;
    }

    public int calculateBonus(int numSabers) {
        if (numSabers == sabersRequired) {
            if (sabersRequired == 2) {
                return 300;
            } else if (sabersRequired == 3) {
                return 500;
            } else if (sabersRequired == 4) {
                return 1000;
            }
        }
        return 0;
    }

}
