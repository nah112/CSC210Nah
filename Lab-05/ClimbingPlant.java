import java.util.EnumSet;
import java.util.List;

public class ClimbingPlant extends Plant {
    public ClimbingPlant(String name, String species) {
        super(name, species);
        
        getHabits().add(GrowthHabit.CLIMBER);
        
    }

    
    public void takeTurn() {
        
    }
}
