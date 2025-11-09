import java.io.*;
import java.util.*;

public class CreatureRegistry {
    private List<Creature> creatures = new ArrayList<>();
    private String filename;

    public CreatureRegistry(String filename) throws IOException {
        this.filename = filename;
        loadFromFile();
    }

    private void loadFromFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Creature c = Creature.fromCSV(line);
                if (c != null) creatures.add(c);
            }
        }
    }

    public int getCount() { return creatures.size(); }

    public Creature getCreatureCopy(int index) {
        Creature c = creatures.get(index);
        return new Creature(c.getName(), c.getWeight(), c.getColor());
    }

    public void addCreature(Creature c) { creatures.add(c); }

    public void updateCreature(int index, Creature newC) {
        creatures.set(index, newC);
    }

    public void deleteCreature(int index) {
        creatures.remove(index);
    }

    public void saveToFile() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Creature c : creatures) pw.println(c.toCSV());
        }
    }

    // Test main method
    public static void main(String[] args) {
        try {
            CreatureRegistry reg = new CreatureRegistry("creature-data.csv");
            System.out.println("Loaded " + reg.getCount() + " creatures.");

            reg.addCreature(new Creature("phoenix", 12, "orange"));
            reg.saveToFile();

            System.out.println("Added phoenix and saved file.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
