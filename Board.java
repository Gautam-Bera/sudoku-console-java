public class Board {

    private int[][] board;
    private boolean[][] initialBoard;

    public Board() {
        board = new int[9][9];
        initialBoard = new boolean[9][9];
    }

    public int getCell(int row, int col) {
        return board[row][col];
    }

    public void setCell(int row, int col, int value) {
        board[row][col] = value;
    }

    public boolean getInitialCell(int row, int col) {
        return initialBoard[row][col];
    }

    public void setInitialCell(int row, int col) {
        initialBoard[row][col] = true;
    }

    public int[][] getBoard() {
        return board;
    }

    public void reset() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                board[row][col] = 0;
                initialBoard[row][col] = false;
            }
        }
    }
}
