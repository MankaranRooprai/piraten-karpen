package pk;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map;

public class SeaBattleCards {

    List<Integer> sbc = new ArrayList<Integer>();

    Map<Integer, Integer> seaBattleCard = new HashMap<Integer, Integer>();
    Random rand = new Random();
    int randomKey;
    int sabersRequired = 0;

    public void populate() {
        sbc.add(2);
        sbc.add(2);
        sbc.add(3);
        sbc.add(3);
        sbc.add(4);
        sbc.add(4);
        // seaBattleCard.put(2, 300);
        // seaBattleCard.put(2, 300);
        // seaBattleCard.put(3, 500);
        // seaBattleCard.put(3, 500);
        // seaBattleCard.put(4, 1000);
        // seaBattleCard.put(4, 1000);
        for (int i = 0; i < 29; i++) {
            sbc.add(0);
            // seaBattleCard.put(0, 0);
        }
        System.out.println(sbc.size());
        // System.out.println(seaBattleCard.size());
    }

    public int drawRandom() {

        randomKey = new Random().nextInt(sbc.size());

        sabersRequired = sbc.get(randomKey);

        // List<Integer> keys = new ArrayList<Integer>(seaBattleCard.keySet());
        // int randIdx = new Random().nextInt(keys.size());

        // randomKey = keys.get(randIdx);

        // int randomKey = keys.get(rand.nextInt(keys.size()));

        // int value = seaBattleCard.get(randomKey);

        return sabersRequired;
        // int randomCard = rand.nextInt((35 - 1) + 1) + 1;
    }

    public int calculateBonus(int numSabers) {
        if (numSabers == sabersRequired) {
            // return seaBattleCard.get(numSabers);
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
