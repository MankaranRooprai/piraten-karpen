package pk;

import java.util.Random;
import pk.Player;

public class Dice {

    public Faces roll() {
        int howManyFaces = Faces.values().length;
        System.out.println(" (DEBUG) there are " + howManyFaces + " faces");
        System.out.println(" (DEBUG) " + Arrays.toString(Faces.values()));
        Random bag = new Random();
        return Faces.values()[bag.nextInt(howManyFaces)];
    }

}