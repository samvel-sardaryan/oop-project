package GUI;

import Game.Dice;
import Game.Monster;
import Game.Player;
import Puzzles.Puzzle;
import Puzzles.PuzzleSystem;
import Quests.Quest;
import Quests.QuestSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameGUI extends JFrame {
    private JLabel welcomeLabel;
    private Image backgroundImage;
    private Image libraryBackgroundImage;
    private Image forestBackgroundImage;
    private JTextField nameField;
    private JButton startGameButton;
    private JPanel playerPanel;
    private JPanel npcPanel;
    private JButton greetButton;
    private JButton fightButton;
    private JButton fleeButton;
    private JButton avoidSnakeButton;
    private JButton avoidPoisonButton;
    private JButton avoidTrapButton;
    private JButton solveButton;
    private JLabel playerLabel;
    private JLabel npcLabel;
    private JLabel monsterLabel;
    private JLabel snakeLabel;
    private JLabel trapLabel;
    private JLabel hpLabel;

    private Player player;
    private Monster monster;
    private QuestSystem questSystem;
    private PuzzleSystem puzzleSystem;

    public GameGUI() {
        setTitle("Adventure Game");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        questSystem = new QuestSystem();
        puzzleSystem = new PuzzleSystem();

        welcomeLabel = new JLabel("Welcome to the Adventure Game! Please enter the name of the player.");
        welcomeLabel.setForeground(Color.WHITE);
        hpLabel = new JLabel();
        hpLabel.setForeground(Color.WHITE);
        nameField = new JTextField(20);
        ImageIcon playerIcon = new ImageIcon("oop-project/gfx/player.png");
        ImageIcon npcIcon = new ImageIcon("oop-project/gfx/npc.png");
        ImageIcon monsterIcon = new ImageIcon("oop-project/gfx/monster.png");
        ImageIcon snakeIcon = new ImageIcon("oop-project/gfx/snake.png");
        ImageIcon trapIcon = new ImageIcon("oop-project/gfx/trap.png");
        libraryBackgroundImage = new ImageIcon("oop-project/gfx/library.png").getImage();
        forestBackgroundImage = new ImageIcon("oop-project/gfx/forest.png").getImage();
        Image playerImage = playerIcon.getImage().getScaledInstance(700, 700, Image.SCALE_SMOOTH);
        Image npcImage = npcIcon.getImage().getScaledInstance(700, 700, Image.SCALE_SMOOTH);
        Image monsterImage = monsterIcon.getImage().getScaledInstance(700, 700, Image.SCALE_SMOOTH);
        Image snakeImage = snakeIcon.getImage().getScaledInstance(700, 700, Image.SCALE_SMOOTH);
        Image trapImage = trapIcon.getImage().getScaledInstance(700, 700, Image.SCALE_SMOOTH);
        ImageIcon scaledPlayerIcon = new ImageIcon(playerImage);
        ImageIcon scaledNpcIcon = new ImageIcon(npcImage); 
        ImageIcon scaledMonsterIcon = new ImageIcon(monsterImage);  
        ImageIcon scaledSnakeIcon = new ImageIcon(snakeImage);      
        ImageIcon scaledTrapIcon = new ImageIcon(trapImage); 
        playerLabel = new JLabel(scaledPlayerIcon);
        npcLabel = new JLabel(scaledNpcIcon);
        monsterLabel = new JLabel(scaledMonsterIcon);
        snakeLabel = new JLabel(scaledSnakeIcon);
        trapLabel = new JLabel(scaledTrapIcon);
        startGameButton = new JButton("Start Game");
        startGameButton.setForeground(Color.WHITE);
        startGameButton.setBackground(Color.GREEN);
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        playerPanel = new JPanel();
        npcPanel = new JPanel();
        greetButton = new JButton("Greet NPC");
        greetButton.setVisible(false);
        greetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                greetNPC();
            }
        });
        fightButton = new JButton("Fight");
        fightButton.setVisible(false);
        fightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fightMonster();
            }
        });
        fleeButton = new JButton("Flee");
        fleeButton.setVisible(false);
        fleeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fleeFromMonster();
            }
        });
        avoidSnakeButton = new JButton("Avoid Snake");
        avoidSnakeButton.setVisible(false);
        avoidSnakeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                avoidSnake();
            }
        });
        avoidPoisonButton = new JButton("Stop Poison");
        avoidPoisonButton.setVisible(false);
        avoidPoisonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                avoidPoison();
            }
        });
        avoidTrapButton = new JButton("Avoid Trap");
        avoidTrapButton.setVisible(false);
        avoidTrapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                avoidTrap();
            }
        });
        solveButton = new JButton("Solve Puzzles");
        solveButton.setVisible(false);
        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                presentPuzzles();
            }
        });

        backgroundImage = libraryBackgroundImage;
        setContentPane(new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        });
        setLayout(new BorderLayout());
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menuPanel.setBackground(Color.BLACK);
        playerPanel.setOpaque(false);
        npcPanel.setOpaque(false);
        menuPanel.add(welcomeLabel);
        menuPanel.add(nameField);
        menuPanel.add(startGameButton);
        menuPanel.add(hpLabel);
        hpLabel.setVisible(false);
        add(menuPanel, BorderLayout.NORTH);
        add(playerPanel, BorderLayout.WEST);
        add(npcPanel, BorderLayout.EAST);
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actionPanel.setBackground(Color.BLACK);
        actionPanel.add(greetButton);
        actionPanel.add(fightButton);
        actionPanel.add(fleeButton);
        actionPanel.add(avoidSnakeButton);
        actionPanel.add(avoidPoisonButton);
        actionPanel.add(avoidTrapButton);
        actionPanel.add(solveButton);
        playerPanel.add(playerLabel);
        npcPanel.add(npcLabel);
        playerPanel.setVisible(false);
        npcPanel.setVisible(false);
        add(actionPanel, BorderLayout.SOUTH);
    }

    private void updateHPLabel() {
        if (player != null) {
            hpLabel.setText("HP: " + player.getHP());
        }
    }

    private void startGame() {
        String playerName = nameField.getText().trim();
        if (!playerName.isEmpty()) {
            player = new Player(playerName, Dice.roll(21), Dice.roll(21),
            Dice.roll(21), Dice.roll(21), Dice.roll(21),
            Dice.roll(21), Dice.roll(21), Dice.roll(21));
            welcomeLabel.setVisible(false);
            nameField.setVisible(false);
            startGameButton.setVisible(false);
            hpLabel.setVisible(true);
            updateHPLabel();
            greetButton.setVisible(true);
            questSystem.loadQuestsFromFile("oop-project/textfiles/quests.txt");
            puzzleSystem.loadPuzzlesFromFile("oop-project/textfiles/puzzles.txt");
            playerPanel.setVisible(true);
            npcPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a name.");
        }
    }

    private void greetNPC() {
        Quest quest = questSystem.getRandomQuest();
        if (quest != null) {
            JOptionPane.showMessageDialog(this, "Hello " + player.getName() + ", this is quest for you.");
            JOptionPane.showMessageDialog(this, "Quest: " + quest.getDescription());
            greetButton.setVisible(false);
            backgroundImage = forestBackgroundImage;
            repaint();
            npcPanel.remove(npcLabel);
            npcPanel.add(snakeLabel);
            JOptionPane.showMessageDialog(this, "You encounter a very poisonous snake!");
            avoidSnakeButton.setVisible(true);      
        } else {
            JOptionPane.showMessageDialog(this, "No quests available.");
        }
    }

    private void avoidSnake() {
        int wisdomCheck = Dice.roll(10) + player.getWisdom();
        int snakeDanger = Dice.roll(20);
        if (wisdomCheck >= snakeDanger) {
            npcPanel.remove(snakeLabel);
            avoidSnakeButton.setVisible(false);
            JOptionPane.showMessageDialog(this, "Your wisdom helps you avoid the snake.");
            npcPanel.add(trapLabel);
            avoidTrapButton.setVisible(true);
            JOptionPane.showMessageDialog(this, "You encounter a trap!");
        } else {
            npcPanel.remove(snakeLabel);
            avoidSnakeButton.setVisible(false);
            avoidPoisonButton.setVisible(true);
            JOptionPane.showMessageDialog(this, "Snake bites you");
        }
    }

    private void avoidPoison() {
        int constitutionCheck = Dice.roll(10) + player.getConstitution();
        int snakeDamage = Dice.roll(20);
        if(constitutionCheck >= snakeDamage){
            JOptionPane.showMessageDialog(this, "You successfully stop the poison.");
        }
        else{
            int hp = player.takeDamage(snakeDamage);
            JOptionPane.showMessageDialog(this, "You get bitten! Your HP: " + hp);
            updateHPLabel();
            if (player.getHP() <= 0) {
                JOptionPane.showMessageDialog(this, "You died from poison!");
                System.exit(0);
            }
        }
        npcPanel.add(trapLabel);
        avoidPoisonButton.setVisible(false);
        avoidTrapButton.setVisible(true);
        JOptionPane.showMessageDialog(this, "You encounter a trap!");
    }

    private void avoidTrap() {
        int intelligenceCheck = Dice.roll(10) + player.getIntelligence();
        int trapDamage = Dice.roll(20);
        if (intelligenceCheck >= trapDamage) {
            JOptionPane.showMessageDialog(this, "Your intelligence helps you avoid it.");
        } else {
            int hp = player.takeDamage(trapDamage);
            JOptionPane.showMessageDialog(this, "You get damage! Your HP: " + hp);
            updateHPLabel();
            if (player.getHP() <= 0) {
                JOptionPane.showMessageDialog(this, "You died in the trap!");
                System.exit(0);
            }
        }
        JOptionPane.showMessageDialog(this, "There is monster infront of you. You can try to attack or flee.");
        avoidTrapButton.setVisible(false);
        npcPanel.remove(trapLabel);
        npcPanel.add(monsterLabel);
        monster = new Monster("Worm", Dice.roll(21), Dice.roll(21));
        fightButton.setVisible(true);
        fleeButton.setVisible(true);
    }

    private void fightMonster() {
        if (player.attack(monster, 20)) {
            int damageDealtByPlayer = Dice.roll(10) + player.getStrength();
            int remainingMonsterHP = monster.takeDamage(damageDealtByPlayer);
            JOptionPane.showMessageDialog(this, "You hit the monster for " + damageDealtByPlayer + " damage. Monster's HP: " + remainingMonsterHP);
            if (monster.getHP() <= 0) {
                JOptionPane.showMessageDialog(this, "You defeated the monster!");
                npcPanel.remove(monsterLabel);
                fightButton.setVisible(false);
                fleeButton.setVisible(false);
                npcPanel.add(npcLabel);
                JOptionPane.showMessageDialog(this, "I am happy that you are back. I have puzzles for you to solve.");
                solveButton.setVisible(true);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "You failed to attack the monster!");
        }

        if(monster.getHP() > 0){
            if (monster.attack(player, 20)) {
                int damageDealtByMonster = Dice.roll(5) + monster.getAC();
                int remainingPlayerHP = player.takeDamage(damageDealtByMonster);
                JOptionPane.showMessageDialog(this, "The monster hits you for " + damageDealtByMonster + " damage. Your HP: " + remainingPlayerHP);
                updateHPLabel();
                if (player.getHP() <= 0) {
                    JOptionPane.showMessageDialog(this, "You were defeated by the monster!");
                    System.exit(0);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Monster failed to attack you!");
            }
        }
    }
    

    private void fleeFromMonster() {
        int diceRoll = Dice.roll(10);
        if (diceRoll + player.getDexterity() >= monster.getAC()) {
            JOptionPane.showMessageDialog(this, "You successfully fled from the monster!");
            npcPanel.remove(monsterLabel);
            fightButton.setVisible(false);
            fleeButton.setVisible(false);
            npcPanel.add(npcLabel);
            JOptionPane.showMessageDialog(this, "I am happy that you are back. I have puzzles for you to solve to be rewarded.");
            solveButton.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "You failed to flee!");    
            fleeButton.setVisible(false);     
        }
    }

    private void presentPuzzles() {
        ArrayList<Puzzle> puzzles = puzzleSystem.getPuzzles();
        if (puzzles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puzzles available.");
            endGame();
        } else {
            int solvedPuzzles = 0;
            for (Puzzle puzzle : puzzles) {
                int answerCheck = JOptionPane.showConfirmDialog(this, "Do you want to solve the puzzle?\n" + puzzle.getDescription(), "Puzzle", JOptionPane.YES_NO_OPTION);
                if (answerCheck == JOptionPane.YES_OPTION) {
                    String answer = JOptionPane.showInputDialog(this, "Enter your answer:");
                    if (answer != null && answer.equalsIgnoreCase(puzzle.getSolution())) {
                        JOptionPane.showMessageDialog(this, "Correct! You solved the puzzle.");
                        solvedPuzzles++;
                    } else {
                        JOptionPane.showMessageDialog(this, "Incorrect.");
                    }
                } else {
                    int charismaCheck = Dice.roll(10) + player.getCharisma();
                    if (charismaCheck >= 15) {
                        JOptionPane.showMessageDialog(this, "Your charisma persuades the NPC to skip this puzzle.");
                        solvedPuzzles++;
                    } else {
                        JOptionPane.showMessageDialog(this, "The NPC insists you solve the puzzle.");
                        String answer = JOptionPane.showInputDialog(this, "Enter your answer:");
                        if (answer != null && answer.equalsIgnoreCase(puzzle.getSolution())) {
                            JOptionPane.showMessageDialog(this, "Correct! You solved the puzzle.");
                            solvedPuzzles++;
                        } else {
                            JOptionPane.showMessageDialog(this, "Incorrect.");
                        }
                    }
                }
            }
            if (solvedPuzzles == puzzles.size()) {
                JOptionPane.showMessageDialog(this, "Congratulations! You solved all the puzzles. The quest is finished. You did not fail OOP!!!");
                endGame();
            } else {
                JOptionPane.showMessageDialog(this, "You have not solved all the puzzles. You failed the quest.");
                endGame();
            }
        }
    }

    private void endGame() {
        JOptionPane.showMessageDialog(this, "Thank you for playing the Adventure Game!");
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameGUI().setVisible(true);
            }
        });
    }
}
