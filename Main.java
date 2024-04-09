import Game.Player;
import Game.Monster;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Hero", 20, 15, 12, 14, 16, 10, 13, 15);
        Monster monster = new Monster("Goblin", 8, 12);

        int sides = 20; 
        boolean playerAttacks = player.attack(monster, sides);

        if (playerAttacks) {
            int damage = 4; 
            monster.takeDamage(damage);
        }

        boolean monsterAttacks = monster.attack(player, sides);

        if (monsterAttacks) {
            int damage = 2; 
            player.takeDamage(damage);
        }
    }
}
