import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class EcosystemGUI extends JFrame {
    private World world;
    private JButton[][] grid;
    private JTextArea sidebar;
    private JTextArea logArea;
    private javax.swing.Timer timer;

    public EcosystemGUI() {
        world = World.loadFromFile("ecosystem.txt");

        setTitle("Ecosystem GUI");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(world.getRows(), world.getCols()));
        grid = new JButton[world.getRows()][world.getCols()];

        for (int r = 0; r < world.getRows(); r++) {
            for (int c = 0; c < world.getCols(); c++) {
                JButton b = new JButton();
                int rr = r, cc = c;
                b.addActionListener(e -> showCell(rr, cc));
                grid[r][c] = b;
                gridPanel.add(b);
            }
        }

        sidebar = new JTextArea();
        sidebar.setEditable(false);

        logArea = new JTextArea();
        logArea.setEditable(false);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JScrollPane(sidebar), BorderLayout.CENTER);
        rightPanel.add(new JScrollPane(logArea), BorderLayout.SOUTH);

        JPanel controlPanel = new JPanel();
        JButton stepBtn = new JButton("Step");
        JButton playBtn = new JButton("Play");
        JButton resetBtn = new JButton("Reset");
        JButton loadBtn = new JButton("Load");
        JButton saveBtn = new JButton("Save");

        stepBtn.addActionListener(e -> step());
        playBtn.addActionListener(e -> togglePlay());
        resetBtn.addActionListener(e -> reset());
        loadBtn.addActionListener(e -> load());
        saveBtn.addActionListener(e -> save());

        controlPanel.add(stepBtn);
        controlPanel.add(playBtn);
        controlPanel.add(resetBtn);
        controlPanel.add(loadBtn);
        controlPanel.add(saveBtn);

        add(controlPanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        timer = new javax.swing.Timer(500, e -> step());

        refresh();
        setVisible(true);
    }

    private void step() {
        world.takeTurn();
        logArea.append("Turn executed\n");
        refresh();
    }

    private void togglePlay() {
        if (timer.isRunning()) timer.stop();
        else timer.start();
    }

    private void reset() {
        world = World.loadFromFile("ecosystem.txt");
        logArea.append("World reset\n");
        refresh();
    }

    private void load() {
        world = World.loadFromFile("ecosystem.txt");
        logArea.append("World loaded\n");
        refresh();
    }

    private void save() {
        world.saveToFile("ecosystem_saved.txt");
        logArea.append("World saved\n");
    }

    private void showCell(int r, int c) {
        sidebar.setText(world.getCell(r, c).toString());
    }

    private void refresh() {
        for (int r = 0; r < world.getRows(); r++)
            for (int c = 0; c < world.getCols(); c++)
                grid[r][c].setText(world.getCell(r, c).display());
    }

    public static void main(String[] args) {
        new EcosystemGUI();
    }
}

class World {
    private Cell[][] cells;

    public World(int r, int c) {
        cells = new Cell[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                cells[i][j] = new Cell();
    }

    public static World loadFromFile(String file) {
        return new World(10, 10);
    }

    public void saveToFile(String file) {}

    public void takeTurn() {
        for (Cell[] row : cells)
            for (Cell cell : row)
                cell.update();
    }

    public int getRows() { return cells.length; }
    public int getCols() { return cells[0].length; }
    public Cell getCell(int r, int c) { return cells[r][c]; }
}

class Cell {
    private int energy = 0;

    public void update() { energy++; }

    public String display() { return String.valueOf(energy); }

    public String toString() { return "Energy: " + energy; }
}
