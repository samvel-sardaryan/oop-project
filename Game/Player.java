package Game;

public class Player extends Character {
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    public Player(String name, int hp, int ac, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        super(name, hp, ac);
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public boolean attack(Character target, int sides) {
        int attackRoll = Dice.roll(sides);
        if (attackRoll + getStrength() >= target.getAC()) {
            return true;
        } else {
            return false;
        }
    }
}

