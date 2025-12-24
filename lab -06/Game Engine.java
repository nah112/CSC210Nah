import java.util.Scanner;

public class GameEngine {
    private Inventory inv = new Inventory();

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Lemonade Stand!");

        while (true) {
            System.out.println("Lemons: " + inv.getLemons());
            System.out.println("1) Make lemonade (uses 2 lemons)");
            System.out.println("2) Add lemons");
            System.out.println("3) Quit");
            int c = sc.nextInt();

            if (c == 1) {
                if (inv.use(2)) 
                    System.out.println("You made lemonade!");
                else
                    System.out.println("Not enough lemons!");
            } 
            else if (c == 2) {
                System.out.print("How many lemons to add? ");
                inv.add(sc.nextInt());
            }
            else break;
        }
    }
}
