public class World implements TurnTaker {
    private final int rows;
    private final int cols;
    private final Tile[][] grid;

    public World(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Tile[rows][cols];
    }

    public void setTile(int r, int c, Tile t) { grid[r][c] = t; }
    public Tile getTile(int r, int c) { return grid[r][c]; }

    
    public void takeTurn() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Tile t = grid[r][c];
                if (t != null) t.takeTurn();
            }
        }
    }
}
