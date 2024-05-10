package Quests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QuestSystem {
    private ArrayList<Quest> quests;

    public QuestSystem() {
        quests = new ArrayList<>();
    }

    public void loadQuestsFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            String title = "";
            StringBuilder description = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Quest")) {
                    if (!title.isEmpty() && description.length() > 0) {
                        quests.add(new Quest(title, description.toString()));
                        title = "";
                        description.setLength(0);
                    }
                } else if (line.startsWith("Title:")) {
                    title = line.substring(7).trim();
                } else if (line.startsWith("Description:")) {
                    description.append(line.substring(12).trim()).append("\n");
                }
            }

            if (!title.isEmpty() && description.length() > 0) {
                quests.add(new Quest(title, description.toString()));
            }

            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading quests file: " + e.getMessage());
        }
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public Quest getRandomQuest() {
        if (quests.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return quests.get(random.nextInt(quests.size()));
    }
}
