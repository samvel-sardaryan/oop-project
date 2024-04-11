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

    public void openDoor() {
        System.out.println(getName() + " attempts to break down a door using their strength.");
        if (strength >= 15) {
            System.out.println("The door breaks open");
        } else {
            System.out.println("The door holds strong against your efforts");
        }
    }

    public void pickLock() {
        System.out.println(getName() + " attempts to pick a lock using their dexterity.");
        if (dexterity >= 15) {
            System.out.println("You successfully pick the lock");
        } else {
            System.out.println("The lock proves too difficult to pick");
        }
    }

    public void resistPoison() {
        System.out.println(getName() + " attempts to resist poison using their constitution.");
        if (constitution >= 15) {
            System.out.println("You successfully resist the poison");
        } else {
            System.out.println("The poison courses through your veins");
        }
    }

    public void solvePuzzle() {
        System.out.println(getName() + " attempts to solve a puzzle using their intelligence.");
        if (intelligence >= 15) {
            System.out.println("You solve the puzzle with ease");
        } else {
            System.out.println("The puzzle remains unsolved");
        }
    }

    public void detectTraps() {
        System.out.println(getName() + " attempts to detect hidden traps using their wisdom.");
        if (wisdom >= 15) {
            System.out.println("You detect hidden traps in the area");
        } else {
            System.out.println("You do not detect any hidden traps");
        }
    }

    public void persuadeOther() {
        System.out.println(getName() + " attempts to persuade someone using their charisma.");
        if (charisma >= 15) {
            System.out.println("Your charm wins them over");
        } else {
            System.out.println("Your words fail to persuade them");
        }
    }
}

