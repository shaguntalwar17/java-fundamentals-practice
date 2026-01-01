public class Board {
    private char[][] board;

    public Board() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public boolean placeMove(int row, int col, char symbol) {
        if (board[row][col] == '-') {
            board[row][col] = symbol;
            return true;
        }
        return false;
    }

    public void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkWin(char symbol) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) || // Row check
                (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) { // Column check
                return true;
            }
        }
        // Diagonal checks
        if ((board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
            (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
            return true;
        }
        return false;
    }
}

