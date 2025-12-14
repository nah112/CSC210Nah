import java.util.ArrayList;
import java.util.List;

public class Tile implements TurnTaker {
    private final List<Creature> creatures = new ArrayList<>();
    private int water;        
    private int temperature;  
    private int nutrients;    

    public Tile(int water, int temperature, int nutrients) {
        this.water = water;
        this.temperature = temperature;
        this.nutrients = nutrients;
    }

    public void addCreature(Creature c) { creatures.add(c); }
    public List<Creature> getCreatures() { return creatures; }

    public int getWater() { return water; }
    public int getTemperature() { return temperature; }
    public int getNutrients() { return nutrients; }

   
    public void takeTurn() {
        
        for (Creature c : creatures) {
            c.takeTurn();
            
        }
        
    }
}
