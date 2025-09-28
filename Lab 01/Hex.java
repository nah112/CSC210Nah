// Hex.java
public class Hex {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Hex <hexadecimal_number>");
            System.exit(1);
        }

        String hex = args[0].toLowerCase();
        long decimal = 0;
        int base = 16;

        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            int value;

            if (c >= '0' && c <= '9') {
                value = c - '0';
            } else if (c >= 'a' && c <= 'f') {
                value = 10 + (c - 'a');
            } else {
                System.err.println("Error: Not a valid hexadecimal number.");
                System.exit(1);
                return; // just to satisfy compiler
            }

            decimal = decimal * base + value;
        }

        System.out.println("Decimal value: " + decimal);
    }
}