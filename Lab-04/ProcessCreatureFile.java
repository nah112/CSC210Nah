import java.io.*;
import java.util.*;

public class ProcessCreatureFile {
    private final List<Creature> creatures = new ArrayList<>();

        public ProcessCreatureFile() {}

    public List<Creature> getCreatures() { return creatures; }

    public void loadFromFile(File file) throws IOException {
        creatures.clear();
        if (file == null || !file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                if (first && line.toLowerCase().startsWith("name,")) {
                    first = false;
                    continue;
                }
                first = false;
                String[] parts = line.split(",");
                if (parts.length < 2) continue;
                String name = parts[0].trim();
                double size;
                try {
                    size = Double.parseDouble(parts[1].trim());
                } catch (NumberFormatException nfe) {
                    continue;
                }
                creatures.add(new Creature(name, size));
            }
        }
    }

    public void saveToFile(File file) throws IOException {
        if (file == null) throw new IOException("No file chosen.");
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println("name,size");
            for (Creature c : creatures) {
                pw.println(c.getName() + "," + c.getSize());
            }
        }
    }

    public void addCreature(Creature c) {
        if (c != null) creatures.add(new Creature(c.getName(), c.getSize()));
    }

    public void updateCreature(int index, Creature updated) {
    if (index < 0 || index >= creatures.size() || updated == null) {
        throw new IndexOutOfBoundsException("Invalid index: " + index);
    }
        creatures.set(index, new Creature(updated.getName(), updated.getSize()));
}
    public Creature removeCreature(int index) {
        if (index < 0 || index >= creatures.size()) return null;
        return creatures.remove(index);
    }
}
