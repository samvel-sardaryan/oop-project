package Game;

import java.util.Random;

public class Dice {
    private static Random random = new Random();

    public static int roll(int sides) {
        if (sides <= 0) {
            throw new IllegalArgumentException("Number of sides must be positive");
        }
        return random.nextInt(sides) + 1;
    }
}
