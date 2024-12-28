import java.util.Scanner;

public class TicTacToe {
    static final int EMPTY = 0;
    static final int X = 1;
    static final int O = 2;
    static final int SIZE = 3;
    static int[] board = new int[SIZE * SIZE];
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Current board:");
            printBoard();
            if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }
            playerMove(scanner);
            if (checkWin(X)) {
                printBoard();
                System.out.println("Player X wins!");
                break;
            }
            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }
            computerMove();
            if (checkWin(O)) {
                printBoard();
                System.out.println("Player O wins!");
                break;
            }
        }
    }

    static void playerMove(Scanner scanner) {
        System.out.print("Player X, enter your move (1-9): ");
        int move = scanner.nextInt() - 1;
        if (board[move] == EMPTY) {
            board[move] = X;
        } else {
            System.out.println("Invalid move. Try again.");
            playerMove(scanner);
        }
    }

    static void computerMove() {
        int bestMove = findBestMove();
        board[bestMove] = O;
    }

    static int findBestMove() {
        int bestValue = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < board.length; i++) {
            if (board[i] == EMPTY) {
                board[i] = O;
                int moveValue = minimax(false);
                board[i] = EMPTY;
                if (moveValue > bestValue) {
                    bestValue = moveValue;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    static int minimax(boolean isMax) {
        if (checkWin(O)) return 10;
        if (checkWin(X)) return -10;
        if (isBoardFull()) return 0;

        if (isMax) {
            int bestValue = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == EMPTY) {
                    board[i] = O;
                    bestValue = Math.max(bestValue, minimax(false));
                    board[i] = EMPTY;
                }
            }
            return bestValue;
        } else {
            int bestValue = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == EMPTY) {
                    board[i] = X;
                    bestValue = Math.min(bestValue, minimax(true));
                    board[i] = EMPTY;
                }
            }
            return bestValue;
        }
    }

    static boolean checkWin(int player) {
        for (int i = 0; i < SIZE; i++) {
            if ((board[i * SIZE] == player && board[i * SIZE + 1] == player && board[i * SIZE + 2] == player) || 
                (board[i] == player && board[i + SIZE] == player && board[i + SIZE * 2] == player)) {
                return true;
            }
        }
        return (board[0] == player && board[4] == player && board[8] == player) ||
               (board[2] == player && board[4] == player && board[6] == player);
    }

    static boolean isBoardFull() {
        for (int cell : board) {
            if (cell == EMPTY) return false;
        }
        return true;
    }

    static void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                char symbol = board[i * SIZE + j] == X ? 'X' : board[i * SIZE + j] == O ? 'O' : ' ';
                System.out.print("| " + symbol + " ");
            }
            System.out.println("|");
        }
    }
}
