public class Tank {
    private Fish[] fish;

    public Tank(Fish[] f) {
        fish = f;
    }

    public void show() {
        for (Fish f : fish) {
            f.swim();
        }
    }
}
