import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    
    private static String readFile(String path) throws IOException {
        return Files.readString(Path.of(path));
    }

    
    private static World buildWorldFromJson(String jsonText) {
        
        World w = new World(2, 2);

        Tile a = new Tile(60, 20, 70);
        Tile b = new Tile(30, 25, 40);
        Tile c = new Tile(80, 15, 90);
        Tile d = new Tile(50, 30, 50);

        
        Plant.addSpecies("AppleTree", "perennial, woody, flowering, seeds");
        Plant.addSpecies("MossCommon", "annual, mat, spores");
        Bird.addSpecies("Sparrow", "small insectivore");
        Mammal.addSpecies("Deer", "herbivore");
        Fish.addSpecies("Trout", "freshwater");

        
        a.addCreature(new FloweringPlant("TreeA", "AppleTree"));
        a.addCreature(new Mammal("Deer1", "Deer"));

        b.addCreature(new Moss("Moss1", "MossCommon"));

        c.addCreature(new Bird("Bird1", "Sparrow"));

        d.addCreature(new Fish("Fish1", "Trout"));

        w.setTile(0, 0, a);
        w.setTile(0, 1, b);
        w.setTile(1, 0, c);
        w.setTile(1, 1, d);

        return w;
    }

    public static void main(String[] args) {
        
        String jsonText = "{}";
        if (args.length > 0) {
            try {
                jsonText = readFile(args[0]);
            } catch (IOException e) {
                System.err.println("Could not read JSON file: " + e.getMessage());
            }
        }

        World world = buildWorldFromJson(jsonText);

        
        for (int i = 0; i < 100; i++) {
            world.takeTurn();
        }

        System.out.println("Simulation finished: 100 turns.");
    }
}
