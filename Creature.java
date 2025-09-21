// Creature.java
class Creature {
    private String name;
    private int size;

    public Creature(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void eat(String food) {
        System.out.println(name + " eats " + food + ".");
    }

    public void talk(String words) {
        System.out.println(name + " says: \"" + words + "\"");
    }

    public void move(String direction) {
        System.out.println(name + " moves " + direction + ".");
    }

    public void describe() {
        System.out.println(name + " is a creature of size " + size + ".");
    }
}

public class CreatureDemo {
    public static void main(String[] args) {
        Creature dragon = new Creature("Draco", 50);
        dragon.describe();
        dragon.eat("sheep");
        dragon.talk("Hello, human!");
        dragon.move("north");
    }
}