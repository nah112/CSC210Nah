import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CreaturesFromFileGUI extends JFrame {
    private final ProcessCreatureFile processor = new ProcessCreatureFile();
    private File currentFile = null;

    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> creatureList = new JList<>(listModel);

    private final JTextField nameField = new JTextField();
    private final JTextField sizeField = new JTextField();
    private final JTextArea infoArea = new JTextArea();

    private final JButton loadBtn = new JButton("Load...");
    private final JButton saveFileBtn = new JButton("Save File");
    private final JButton saveChangesBtn = new JButton("Save Changes");
    private final JButton addBtn = new JButton("Add");
    private final JButton removeBtn = new JButton("Remove");

    public CreaturesFromFileGUI() {
        super("Program 4: Creatures From File");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left: list
        creatureList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroll = new JScrollPane(creatureList);
        listScroll.setPreferredSize(new Dimension(180, 0));
        add(listScroll, BorderLayout.WEST);

        // Center: form
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6, 6, 6, 6);
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;
        int row = 0;

        gc.gridx = 0; gc.gridy = row; form.add(new JLabel("Name:"), gc);
        gc.gridx = 1; gc.gridy = row++; form.add(nameField, gc);

        gc.gridx = 0; gc.gridy = row; form.add(new JLabel("Size:"), gc);
        gc.gridx = 1; gc.gridy = row++; form.add(sizeField, gc);

        JPanel editActions = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        editActions.add(saveChangesBtn);
        editActions.add(addBtn);
        editActions.add(removeBtn);
        gc.gridx = 0; gc.gridy = row; gc.gridwidth = 2; form.add(editActions, gc);

        add(form, BorderLayout.CENTER);

        // Right: preview
        infoArea.setEditable(false);
        infoArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane infoScroll = new JScrollPane(infoArea);
        infoScroll.setPreferredSize(new Dimension(300, 0));
        add(infoScroll, BorderLayout.EAST);

        // Top: file actions
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        topBar.add(loadBtn);
        topBar.add(saveFileBtn);
        add(topBar, BorderLayout.NORTH);

        // Listeners
        creatureList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) populateFormFromSelection();
        });

        loadBtn.addActionListener(e -> loadFromChooser());
        saveFileBtn.addActionListener(e -> saveFile());
        saveChangesBtn.addActionListener(e -> saveChangesToSelected());
        addBtn.addActionListener(e -> addCreatureFromForm());
        removeBtn.addActionListener(e -> removeSelectedCreature());

        setSize(880, 400);
        setLocationRelativeTo(null);
    }

        private void refreshList() {
        listModel.clear();
        for (Creature c : processor.getCreatures()) listModel.addElement(c.getName());
        if (!listModel.isEmpty()) creatureList.setSelectedIndex(0);
        populateFormFromSelection();
    }

    private void populateFormFromSelection() {
        int i = creatureList.getSelectedIndex();
        if (i < 0 || i >= processor.getCreatures().size()) {
            nameField.setText("");
            sizeField.setText("");
            infoArea.setText("");
            return;
        }
        Creature c = processor.getCreatures().get(i);
        nameField.setText(c.getName());
        sizeField.setText(String.valueOf(c.getSize()));
        infoArea.setText(c.toString());
    }

    private Creature buildCreatureFromForm(boolean showErrors) {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            if (showErrors) warn("Name cannot be empty.");
            return null;
        }
        double size;
        try { size = Double.parseDouble(sizeField.getText().trim()); }
        catch (NumberFormatException nfe) {
            if (showErrors) warn("Size must be a number.");
            return null;
        }
        return new Creature(name, size);
    }

    private void loadFromChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Load Creatures File");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            try {
                processor.loadFromFile(f);
                refreshList();
                info("Loaded " + processor.getCreatures().size() + " creature(s) from:\n" + f.getName());
            } catch (IOException ex) {
                error("Failed to load file:\n" + ex.getMessage());
            }
        }
    }

    private void saveFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Creatures File");
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            try {
                processor.saveToFile(f);
                info("Saved to:\n" + f.getName());
            } catch (IOException ex) {
                error("Failed to save file:\n" + ex.getMessage());
            }
        }
    }

    private void saveChangesToSelected() {
        int i = creatureList.getSelectedIndex();
        if (i < 0) { warn("Select a creature first."); return; }
        Creature updated = buildCreatureFromForm(true);
        if (updated == null) return;
        try {
            processor.updateCreature(i, updated);
            refreshList();
            creatureList.setSelectedIndex(i);
            infoArea.setText(updated.toString());
        } catch (Exception ex) {
            error("Failed to update creature:\n" + ex.getMessage());
        }
    }

    private void addCreatureFromForm() {
        Creature c = buildCreatureFromForm(true);
        if (c == null) return;
        try {
            processor.addCreature(c);
            refreshList();
            int last = listModel.size() - 1;
            if (last >= 0) creatureList.setSelectedIndex(last);
        } catch (Exception ex) {
            error("Failed to add creature:\n" + ex.getMessage());
        }
    }

    private void removeSelectedCreature() {
        int i = creatureList.getSelectedIndex();
        if (i < 0) { warn("Select a creature to remove."); return; }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Remove \"" + listModel.getElementAt(i) + "\"?",
                "Confirm Remove", JOptionPane.OK_CANCEL_OPTION);
        if (confirm != JOptionPane.OK_OPTION) return;
        try {
            processor.removeCreature(i);
            refreshList();
        } catch (Exception ex) {
            error("Failed to remove creature:\n" + ex.getMessage());
        }
    }

    
    private void warn(String m) { JOptionPane.showMessageDialog(this, m, "Warning", JOptionPane.WARNING_MESSAGE); }
    private void info(String m) { JOptionPane.showMessageDialog(this, m, "Info", JOptionPane.INFORMATION_MESSAGE); }
    private void error(String m) { JOptionPane.showMessageDialog(this, m, "Error", JOptionPane.ERROR_MESSAGE); }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            setNimbus();
            new CreaturesFromFileGUI().setVisible(true);
        });
    }

    private static void setNimbus() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}
    }
}
