package Game;

public abstract class Character {
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

    public abstract boolean attack(Character target, int sides);

    public int takeDamage(int damage) {
        hp -= damage;
        return hp;
    }
}
