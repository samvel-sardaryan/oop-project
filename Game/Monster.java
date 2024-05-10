package Game;

public class Monster extends Character {
    public Monster(String name, int hp, int ac) {
        super(name, hp, ac);
    }

    public boolean attack(Character target, int sides) {
        int attackRoll = Dice.roll(sides);
        if (attackRoll >= target.getAC()) {
            return true;
        } else {
            return false;
        }
    }
}

