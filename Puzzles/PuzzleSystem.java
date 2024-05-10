package Puzzles;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PuzzleSystem {
    private ArrayList<Puzzle> puzzles;

    public PuzzleSystem() {
        puzzles = new ArrayList<>();
    }

    public void loadPuzzlesFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            String description = "";
            String solution = "";

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Puzzle")) {
                    if (!description.isEmpty() && !solution.isEmpty()) {
                        puzzles.add(new Puzzle(description, solution));
                        description = "";
                        solution = "";
                    }
                } else if (line.startsWith("Description:")) {
                    description = line.substring(13).trim();
                } else if (line.startsWith("Answer:")) {
                    solution = line.substring(8).trim();
                }
            }

            if (!description.isEmpty() && !solution.isEmpty()) {
                puzzles.add(new Puzzle(description, solution));
            }

            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading puzzles file: " + e.getMessage());
        }
    }

    public ArrayList<Puzzle> getPuzzles() {
        return puzzles;
    }

    public Puzzle getRandomPuzzle() {
        if (puzzles.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return puzzles.get(random.nextInt(puzzles.size()));
    }
}
