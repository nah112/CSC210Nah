public class Main {
    public static void main(String[] args) {
        Fish[] fish = {
            new Fish("Nemo"),
            new Fish("Dory")
        };

        Tank tank = new Tank(fish);
        Aquarium a = new Aquarium(tank);
        a.run();
    }
}
