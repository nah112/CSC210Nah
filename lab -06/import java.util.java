import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board b = new Board();
        char player = 'X';

        while (true) {
            System.out.println(b);
            System.out.println("Player " + player + " move (0-8): ");
            int m = sc.nextInt();

            if (!b.move(m, player)) {
                System.out.println("Invalid move!");
                continue;
            }

            if (b.win(player)) {
                System.out.println(b);
                System.out.println("Player " + player + " wins!");
                break;
            }

            if (b.full()) {
                System.out.println(b);
                System.out.println("Draw!");
                break;
            }

            player = (player == 'X') ? 'O' : 'X';
        }
    }
}
