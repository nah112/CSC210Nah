import java.io.*;
import java.util.*;

public class ProcessCreatureFile {
    public static void main(String[] args) {
        String filename = "creature-data.csv";
        List<Creature> creatures = new ArrayList<>();

        // READ
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Creature c = Creature.fromCSV(line);
                if (c != null) creatures.add(c);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // MODIFY (example actions)
        if (!creatures.isEmpty()) creatures.get(0).setColor("gold");
        creatures.add(new Creature("dragon", 500, "green"));
        if (creatures.size() > 1) creatures.remove(1);

        // WRITE
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Creature c : creatures) pw.println(c.toCSV());
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }

        System.out.println("Updated creatures written to file.");
    }
}
