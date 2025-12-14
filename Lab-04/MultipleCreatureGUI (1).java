import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MultipleCreatureGUI extends JFrame {
    private ArrayList<Creature> creatures;
    private JList<String> creatureList;
    private JTextField nameField, sizeField;
    private JTextArea infoArea;

    public MultipleCreatureGUI() {
        super("Multiple Creatures");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        creatures = new ArrayList<>();
        creatures.add(new Creature("Dragon", 10));
        creatures.add(new Creature("Unicorn", 7));
        creatures.add(new Creature("Goblin", 3));

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Creature c : creatures) model.addElement(c.getName());
        creatureList = new JList<>(model);
        add(new JScrollPane(creatureList), BorderLayout.WEST);

        JPanel middlePanel = new JPanel(new GridLayout(3,2));
        middlePanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        middlePanel.add(nameField);
        middlePanel.add(new JLabel("Size:"));
        sizeField = new JTextField();
        middlePanel.add(sizeField);

        JButton saveBtn = new JButton("Save");
        middlePanel.add(saveBtn);
        JButton eatBtn = new JButton("Eat");
        middlePanel.add(eatBtn);

        infoArea = new JTextArea();
        add(new JScrollPane(infoArea), BorderLayout.EAST);
        add(middlePanel, BorderLayout.CENTER);

        creatureList.addListSelectionListener(e -> {
            int i = creatureList.getSelectedIndex();
            if (i >= 0) {
                Creature c = creatures.get(i);
                nameField.setText(c.getName());
                sizeField.setText(String.valueOf(c.getSize()));
                infoArea.setText(c.toString());
            }
        });

        saveBtn.addActionListener(e -> {
            int i = creatureList.getSelectedIndex();
            if (i >= 0) {
                Creature c = creatures.get(i);
                c.setName(nameField.getText());
                c.setSize(Double.parseDouble(sizeField.getText()));
                infoArea.setText(c.toString());
                model.set(i, c.getName());
            }
        });

        eatBtn.addActionListener(e -> {
            int i = creatureList.getSelectedIndex();
            if (i >= 0) creatures.get(i).eat();
        });

        setSize(600,300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MultipleCreatureGUI::new);
    }
}
