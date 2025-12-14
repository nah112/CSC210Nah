import java.util.*;

public class Plant extends Creature {
    
    private static final Map<String, String> SPECIES_REGISTRY = new HashMap<>();

    public static void addSpecies(String name, String definition) {
        if (SPECIES_REGISTRY.containsKey(name)) {
            throw new IllegalArgumentException("Species '" + name + "' already exists.");
        }
        SPECIES_REGISTRY.put(name, definition);
    }

    public static boolean speciesExists(String name) {
        return SPECIES_REGISTRY.containsKey(name);
    }

    public static String speciesDefinition(String name) {
        return SPECIES_REGISTRY.get(name);
    }

    private boolean perennial;         
    private boolean evergreen;         
    private boolean hasFlowers;        

    private StemType stemType;         
    private final EnumSet<GrowthHabit> habits = EnumSet.noneOf(GrowthHabit.class);

    
    private final List<ReproductionStrategy> reproductionStrategies = new ArrayList<>();

    
    public Plant(String name, String species) {
        super(name, species);
        
        this.stemType = StemType.GREEN_SOFT;
    }

    public Plant(String name, String species,
                 boolean perennial, boolean evergreen, boolean hasFlowers,
                 StemType stemType, Collection<GrowthHabit> habits,
                 Collection<ReproductionStrategy> strategies) {
        super(name, species);
        this.perennial = perennial;
        this.evergreen = evergreen;
        this.hasFlowers = hasFlowers;
        this.stemType = stemType != null ? stemType : StemType.GREEN_SOFT;
        if (habits != null) this.habits.addAll(habits);
        if (strategies != null) this.reproductionStrategies.addAll(strategies);
    }

    public void reproduce(Tile tile) {
        for (ReproductionStrategy s : reproductionStrategies) {
            s.reproduce(this, tile);
        }
    }

    public void reproduce(Tile tile, int times) {  
        for (int i = 0; i < times; i++) {
            reproduce(tile);
        }
    }

   
    public void speak() { 
        System.out.println(getName() + " (" + getSpecies() + ") rustles softly.");
    }

    
    public void takeTurn() {
        
    }

    
    public boolean isPerennial() { return perennial; }
    public boolean isEvergreen() { return evergreen; }
    public boolean hasFlowers() { return hasFlowers; }
    public StemType getStemType() { return stemType; }
    public EnumSet<GrowthHabit> getHabits() { return habits.clone(); }
    public List<ReproductionStrategy> getReproductionStrategies() { return List.copyOf(reproductionStrategies); }
}
