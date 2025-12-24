public class Aquarium {
    private Tank tank;

    public Aquarium(Tank t) {
        tank = t;
    }

    public void run() {
        tank.show();
    }
}
