public class Board {
    private char[] cells = new char[9];

    public Board() {
        for (int i = 0; i < 9; i++) {
            cells[i] = (char) ('1' + i); // Заповнюємо цифрами 1–9
        }
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            System.out.print(" " + cells[i] + " ");
            if (i % 3 != 2) System.out.print("|");
            else if (i != 8) System.out.println("\n---+---+---");
        }
        System.out.println();
    }

    public boolean makeMove(int position, char symbol) {
        if (position < 1 || position > 9 || cells[position - 1] == 'X' || cells[position - 1] == 'O') {
            return false;
        }
        cells[position - 1] = symbol;
        return true;
    }

    public boolean checkWin(char symbol) {
        int[][] wins = {
            {0,1,2},{3,4,5},{6,7,8}, // Горизонталі
            {0,3,6},{1,4,7},{2,5,8}, // Вертикалі
            {0,4,8},{2,4,6}          // Діагоналі
        };
        for (int[] line : wins) {
            if (cells[line[0]] == symbol && cells[line[1]] == symbol && cells[line[2]] == symbol)
                return true;
        }
        return false;
    }

    public boolean isFull() {
        for (char c : cells) {
            if (c != 'X' && c != 'O') return false;
        }
        return true;
    }
}
