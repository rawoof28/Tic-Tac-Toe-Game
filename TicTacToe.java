import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };
    static Random random = new Random();

    static char currentPlayer = 'X';
    static int moves = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Game Starts: ");
        do{
            System.out.println("Choose mode:");
            System.out.println("1. One Player");
            System.out.println("2. Two Player");
            System.out.println("3. Exit");
            int choice = sc.nextInt();

            if (choice == 1) {
                playOnePlayer(sc);
            } else if(choice == 2) {
                playTwoPlayer(sc);
            }
            else
            {
                System.out.println("Game End .");
                break;
            }
            System.out.println("\n_________________________________\n");
        }while(true);

        sc.close();
    }

    static void playTwoPlayer(Scanner sc) {
        resetBoard();
        currentPlayer = 'X';
        moves = 0;

        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter row and column (1-3 1-3):");
            int row = sc.nextInt() - 1;
            int col = sc.nextInt() - 1;

            if (!isValidMove(row, col)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            board[row][col] = currentPlayer;
            moves++;

            if (checkWinner(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (moves == 9) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    static void playOnePlayer(Scanner sc) {
        resetBoard();
        currentPlayer = 'X';
        moves = 0;

        while (true) {
            printBoard();

            if (currentPlayer == 'X') {
                System.out.println("Your turn (X). Enter row and column (1-3 1-3):");
                int row = sc.nextInt() - 1;
                int col = sc.nextInt() - 1;

                if (!isValidMove(row, col)) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }

                board[row][col] = 'X';
                moves++;

                if (checkWinner('X')) {
                    printBoard();
                    System.out.println("You win!");
                    break;
                }
            } else {
                computerMove();
                if (checkWinner('O')) {
                    printBoard();
                    System.out.println("Computer wins!");
                    break;
                }
            }

            if (moves == 9) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    static void computerMove() {
        System.out.println("Computer's turn (O):");
        while (true) {
            int row = random.nextInt(3);
            int col = random.nextInt(3);

            if (isValidMove(row, col)) {
                board[row][col] = 'O';
                moves++;
                System.out.println("Computer chose: " + (row + 1) + " " + (col + 1));
                break;
            }
        }
    }

    static void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void printBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("---+---+---");
        }
        System.out.println();
    }

    static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    static boolean checkWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;

        return false;
    }
}