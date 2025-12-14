import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class HelloGUI extends JFrame {
    private JLabel label;
    private JButton greetButton;
    private String[] greetings = {
        "Hello there!",
        "Good day!",
        "Howdy!",
        "Hi, friend!",
        "Greetings!"
    };

    public HelloGUI() {
        super("Hello GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        label = new JLabel("", SwingConstants.CENTER);
        greetButton = new JButton("Greet");

        greetButton.addActionListener(e -> {
            Random r = new Random();
            label.setText(greetings[r.nextInt(greetings.length)]);
        });

        add(label, BorderLayout.CENTER);
        add(greetButton, BorderLayout.SOUTH);

        setSize(300,150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HelloGUI());
    }
}
