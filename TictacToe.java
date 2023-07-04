import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        // Create the 3x3 game board
        char[][] game_board = new char[3][3];

        // Keep track of players
        char c = '-';
        boolean p1Turn = true;

        // Initalize game board with '-'
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game_board[i][j] = '-';
            }
        }

        while (true) {
            displayBoard(game_board);
            c = checkTurn(c, p1Turn);
            move(c, game_board);

            char temp = winner(game_board);
            if (temp == 'x') {
                displayBoard(game_board);
                System.out.println("Player1 (x) has won!");
            } else if (temp == 'o') {
                displayBoard(game_board);
                System.out.println("Player2 (o) has won!");
            } else {
                // a tie
                if (tie(game_board)) {
                    System.out.println("It's a tie!");
                    return;
                } else {
                    // Switch active players
                    p1Turn = !p1Turn;
                }
            }
        }
    }

    private static boolean tie(char[][] game_board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game_board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private static char winner(char[][] game_board) {

        // Check all rows
        for (int i = 0; i < 3; i++) {
            if (game_board[i][0] == game_board[i][1] && game_board[i][1] == game_board[i][2]
                    && game_board[i][0] != ' ') {
                return game_board[i][0];
            }
        }
        // Check all columns
        for (int j = 0; j < 3; j++) {
            if (game_board[0][j] == game_board[1][j] && game_board[1][j] == game_board[2][j]
                    && game_board[0][j] != ' ') {
                return game_board[0][j];
            }
        }
        // Check first diagonal
        if (game_board[0][0] == game_board[1][1] && game_board[1][1] == game_board[2][2] && game_board[0][0] != ' ') {
            return game_board[0][0];
        }
        // Check second diagonal
        if (game_board[2][0] == game_board[1][1] && game_board[1][1] == game_board[0][2] && game_board[2][0] != ' ') {
            return game_board[2][0];
        }
        // no winner yet
        return ' ';
    }

    private static void move(char c, char[][] game_board) {

        // indexes
        int row = 0;
        int col = 0;

        while (true) {

            try (Scanner input = new Scanner(System.in)) {
                System.out.print("Enter a row number (0, 1, 2): ");
                row = input.nextInt();
                System.out.print("Enter a column number (0, 1, 2): ");
                col = input.nextInt();
            }
            // check if the row and col are valid
            if (row < 0 || col < 0 || row > 2 || col > 2) {
                System.out.println("This position if not on the game board. Please try again.");
            }
            // check if the postion is valid
            else if (game_board[row][col] != '-') {
                System.out.println("Someone has already made a move at that postion. Please try again.");

            } else {
                game_board[row][col] = c;
            }
        }
    }

    private static char checkTurn(char c, boolean p1Turn) {
        if (p1Turn) {
            System.out.println("Player1's turn (x):");
            c = 'x';
        } else {
            System.out.println("Player2's turn (o):");
            c = 'o';
        }
        return c;
    }

    private static void displayBoard(char[][] game_board) {
        System.out.println("\nTic Tac Toe Board");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(game_board[i][j] + " ");
            }
            System.out.println();
        }
    }
}