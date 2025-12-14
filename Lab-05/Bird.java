import java.util.*;

public class Bird extends Creature {
    private static final Map<String, String> SPECIES_REGISTRY = new HashMap<>();

    public static void addSpecies(String name, String definition) {
        if (SPECIES_REGISTRY.containsKey(name)) {
            throw new IllegalArgumentException("Species '" + name + "' already exists.");
        }
        SPECIES_REGISTRY.put(name, definition);
    }

    public Bird(String name, String species) {
        super(name, species);
    }

    
    public void speak() {
        System.out.println(getName() + " (" + getSpecies() + ") chirps.");
    }

    
    public void takeTurn() {
        
    }
}
