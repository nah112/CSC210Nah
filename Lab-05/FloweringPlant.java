import java.util.List;

public class FloweringPlant extends Plant {
    private boolean currentlyFlowering;

    public FloweringPlant(String name, String species) {
        super(name, species);
        this.currentlyFlowering = true;
    }

    public FloweringPlant(String name, String species, boolean flowering) {
        super(name, species);
        this.currentlyFlowering = flowering;
    }

    
    public void takeTurn() {
        
        currentlyFlowering = !currentlyFlowering;
    }
}
