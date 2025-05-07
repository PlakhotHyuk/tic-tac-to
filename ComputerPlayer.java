public class ComputerPlayer extends Player {
    public ComputerPlayer(char symbol) {
        super("Комп'ютер", symbol);
    }

    public int chooseMove(Board board) {
        char[] cells = board.getCells();
        char mySymbol = getSymbol();
        char opponentSymbol = (mySymbol == 'X') ? 'O' : 'X';

        // 1. Спроба виграти
        for (int i = 1; i <= 9; i++) {
            if (board.isCellFree(i)) {
                cells[i - 1] = mySymbol;
                if (board.checkWin(mySymbol)) {
                    cells[i - 1] = (char) ('0' + i);
                    return i;
                }
                cells[i - 1] = (char) ('0' + i);
            }
        }

        // 2. Блокування суперника
        for (int i = 1; i <= 9; i++) {
            if (board.isCellFree(i)) {
                cells[i - 1] = opponentSymbol;
                if (board.checkWin(opponentSymbol)) {
                    cells[i - 1] = (char) ('0' + i);
                    return i;
                }
                cells[i - 1] = (char) ('0' + i);
            }
        }

        // 3. Центр
        if (board.isCellFree(5)) return 5;

        // 4. Кути
        int[] corners = {1, 3, 7, 9};
        for (int c : corners) {
            if (board.isCellFree(c)) return c;
        }

        // 5. Інші
        for (int i = 1; i <= 9; i++) {
            if (board.isCellFree(i)) return i;
        }

        return -1; // не повинно статись
    }
}
