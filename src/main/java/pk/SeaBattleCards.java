package pk;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SeaBattleCards {
    HashMap<Integer, Integer> seaBattleCard = new HashMap<Integer, Integer>();
    Random rand = new Random();

    public void populate() {
        seaBattleCard.put(2, 300);
        seaBattleCard.put(2, 300);
        seaBattleCard.put(3, 500);
        seaBattleCard.put(3, 500);
        seaBattleCard.put(4, 1000);
        seaBattleCard.put(4, 1000);
        for (int i = 0; i < 29; i++) {
            seaBattleCard.put(0, 0);
        }
    }

    public int drawRandom() {
        List<Integer> keys = new ArrayList<Integer>(seaBattleCard.keySet());
        int randomKey = keys.get(rand.nextInt(keys.size()));
        // int value = seaBattleCard.get(randomKey);

        return randomKey;
        // int randomCard = rand.nextInt((35 - 1) + 1) + 1;
    }

    public int calculateBonus(int numSabers) {
        if (numSabers == 2 || numSabers == 3 || numSabers == 4) {
            return seaBattleCard.get(numSabers);
        }
        return 0;
    }

}
