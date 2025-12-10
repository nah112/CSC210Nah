import java.util.Scanner;

public class GoodCasino {
    public static double play(Customer customer, SlotMachine machine, double bet) {
        double actualSpent = customer.spend(bet);
        double winnings = machine.pullLever(actualSpent);
        customer.receive(winnings);
        return winnings;
    }

    public static void main(String[] args) {
        Customer customer = new Customer("customer.txt");
        SlotMachine machine = new SlotMachine("slot-machine.txt");
        Scanner sc = new Scanner(System.in);

        System.out.println( "Welcome to GoodCasino");
        while (true) {
            System.out.println("\nYour wallet: $" + customer.checkWallet());
            System.out.println("Machine pot: $" + machine.getMoneyPot());
            System.out.print("Enter bet (or 'quit' to exit): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                break;
            }

            try {
                double bet = Double.parseDouble(input);
                if (bet <= 0) {
                    System.out.println("Bet must be positive.");
                    continue;
                }

                double winnings = play(customer, machine, bet);
                System.out.println("Spin: " + machine);
                System.out.println("You won: $" + winnings);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

            if (customer.checkWallet() <= 0 || machine.getMoneyPot() <= 0) {
                System.out.println("Game over! Someone ran out of money.");
                break;
            }
        }

        customer.save("customer.txt");
        machine.save("slot-machine.txt");
        sc.close();
        System.out.println("Thanks for playing! State saved.");
    }
}
