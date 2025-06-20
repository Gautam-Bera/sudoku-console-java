public class Validator {

    public static boolean isValidMove(int[][] board, int row, int col, int num) {
        return !inRow(board, row, num) &&
                !inCol(board, col, num) &&
                !inBox(board, row - row % 3, col - col % 3, num);
    }

    public static boolean inRow(int[][] board, int row, int num) {
        for (int col = 0; col < 9; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean inCol(int[][] board, int col, int num) {
        for (int row = 0; row < 9; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean inBox(int[][] board, int startRow, int startCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row + startRow][col + startCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isSolved(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
                int num = board[row][col];
                board[row][col] = 0;
                if (!isValidMove(board, row, col, num)) {
                    board[row][col] = num;
                    return false;
                }
                board[row][col] = num;
            }
        }
        return true;
    }
}
