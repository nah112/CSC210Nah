public class Inventory {
    private int lemons = 10;

    public int getLemons() {
        return lemons;
    }

    public void add(int x) {
        lemons += x;
    }

    public boolean use(int x) {
        if (lemons >= x) {
            lemons -= x;
            return true;
        }
        return false;
    }
}
