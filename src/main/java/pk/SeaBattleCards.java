package pk;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SeaBattleCards {

    List<Integer> seaBattleCardDeck = new ArrayList<Integer>();
    Random rand = new Random();
    int randomKey;
    int sabersRequired = 0;

    public void populate() {
        seaBattleCardDeck.add(2);
        seaBattleCardDeck.add(2);
        seaBattleCardDeck.add(3);
        seaBattleCardDeck.add(3);
        seaBattleCardDeck.add(4);
        seaBattleCardDeck.add(4);
        for (int i = 0; i < 29; i++) {
            seaBattleCardDeck.add(0);
        }
        Collections.shuffle(seaBattleCardDeck);
    }

    public int drawRandom() {

        randomKey = new Random().nextInt(seaBattleCardDeck.size());

        sabersRequired = seaBattleCardDeck.get(randomKey);

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
