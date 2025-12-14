public class Creature {
    private String name;
    private double size;

    public Creature(String name, double size) {
        this.name = name;
        this.size = size;
    }

    public String getName() { return name; }
    public double getSize() { return size; }

 
    public void setName(String name) { this.name = name; }
    public void setSize(double size) { this.size = size; }

    public void eat() {
        System.out.println(name + " eats.");
    }

    public String toString() {
        return "Creature Name: " + name + "\nSize: " + size;
    }
}
