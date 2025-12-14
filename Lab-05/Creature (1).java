public abstract class Creature implements TurnTaker {
    protected String name;
    protected String species;

    protected Creature(String name, String species) {
        this.name = name;
        this.species = species;
    }

    public String getName() { return name; }
    public String getSpecies() { return species; }

    
    public void speak() {
        System.out.println(name + " (" + species + ") makes a generic sound.");
    }

    
    public abstract void takeTurn();
}
