package Game;

public class Character {
    protected String name;
    protected int hp;
    protected int ac;

    public Character(String name, int hp, int ac) {
        this.name = name;
        this.hp = hp;
        this.ac = ac;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return hp;
    }

    public int getAC() {
        return ac;
    }

    public boolean attack(Character target, int sides) {
        int attackRoll = Dice.roll(sides);
        if (attackRoll >= target.getAC()) {
            System.out.println(name + " hits " + target.getName());
            return true;
        } else {
            System.out.println(name + " misses " + target.getName());
            return false;
        }
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            System.out.println(name + " has been defeated");
        } else {
            System.out.println(name + " takes " + damage + " damage. HP: " + hp);
        }
    }
}
