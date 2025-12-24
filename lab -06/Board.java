public class Board {
    private char[] cells = new char[9];

    public Board() {
        for (int i = 0; i < 9; i++) cells[i] = ' ';
    }

    public boolean move(int pos, char p) {
        if (pos < 0 || pos > 8 || cells[pos] != ' ') return false;
        cells[pos] = p;
        return true;
    }

    public boolean win(char p) {
        int[][] w = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };
        for (int[] a : w)
            if (cells[a[0]] == p && cells[a[1]] == p && cells[a[2]] == p)
                return true;

        return false;
    }

    public boolean full() {
        for (char c : cells) if (c == ' ') return false;
        return true;
    }

    public String toString() {
        return String.format(
            "%c|%c|%c\n-+-+-\n%c|%c|%c\n-+-+-\n%c|%c|%c",
            cells[0],cells[1],cells[2],
            cells[3],cells[4],cells[5],
            cells[6],cells[7],cells[8]
        );
    }
}
