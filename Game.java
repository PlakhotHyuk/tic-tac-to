import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Board board;

    public Game() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть ім'я гравця 1 (X): ");
        player1 = new Player(sc.nextLine(), 'X');
        System.out.print("Введіть ім'я гравця 2 (O): ");
        player2 = new Player(sc.nextLine(), 'O');
        board = new Board();
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        Player current = player1;

        while (true) {
            board.printBoard();
            System.out.print(current.getName() + ", виберіть позицію (1-9): ");
            int move = sc.nextInt();

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
