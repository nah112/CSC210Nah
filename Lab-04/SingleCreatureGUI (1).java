import javax.swing.*;
import java.awt.*;

public class SingleCreatureGUI extends JFrame {
    private Creature creature;
    private JTextField nameField, sizeField;
    private JTextArea infoArea;

    public SingleCreatureGUI() {
        super("Single Creature Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        creature = new Creature("Dragon", 10.5);

        JPanel leftPanel = new JPanel(new GridLayout(3,2));
        leftPanel.add(new JLabel("Name:"));
        nameField = new JTextField(creature.getName());
        leftPanel.add(nameField);

        leftPanel.add(new JLabel("Size:"));
        sizeField = new JTextField(String.valueOf(creature.getSize()));
        leftPanel.add(sizeField);

        JButton saveBtn = new JButton("Save");
        leftPanel.add(saveBtn);

        JButton eatBtn = new JButton("Eat");
        leftPanel.add(eatBtn);

        infoArea = new JTextArea(creature.toString());
        infoArea.setEditable(false);
        add(new JScrollPane(infoArea), BorderLayout.EAST);
        add(leftPanel, BorderLayout.CENTER);

        saveBtn.addActionListener(e -> {
            creature.setName(nameField.getText());
            creature.setSize(Double.parseDouble(sizeField.getText()));
            infoArea.setText(creature.toString());
        });

        eatBtn.addActionListener(e -> creature.eat());

        setSize(400,200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SingleCreatureGUI::new);
    }
}
