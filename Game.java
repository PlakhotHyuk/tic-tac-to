import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Board board;
    private boolean vsComputer;

    public Game() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Оберіть режим гри:");
        System.out.println("1. Гравець vs Гравець");
        System.out.println("2. Гравець vs Комп'ютер");
        int mode = sc.nextInt();
        sc.nextLine(); // очистка буфера

        System.out.print("Введіть ім'я гравця 1 (X): ");
        player1 = new Player(sc.nextLine(), 'X');

        if (mode == 2) {
            player2 = new ComputerPlayer('O');
            vsComputer = true;
        } else {
            System.out.print("Введіть ім'я гравця 2 (O): ");
            player2 = new Player(sc.nextLine(), 'O');
            vsComputer = false;
        }

        board = new Board();
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        Player current = player1;

        while (true) {
            board.printBoard();
            int move;

            if (current instanceof ComputerPlayer) {
                System.out.println("Хід комп'ютера...");
                move = ((ComputerPlayer) current).chooseMove(board);
            } else {
                System.out.print(current.getName() + ", виберіть позицію (1-9): ");
                move = sc.nextInt();
            }

            if (!board.makeMove(move, current.getSymbol())) {
                System.out.println("Некоректний хід. Спробуйте ще раз.");
                continue;
            }

            if (board.checkWin(current.getSymbol())) {
                board.printBoard();
                System.out.println(current.getName() + " переміг!");
                break;
            }

            if (board.isFull()) {
                board.printBoard();
                System.out.println("Нічия!");
                break;
            }

            current = (current == player1) ? player2 : player1;
        }
    }
}
