import java.util.*;

public class Generator {
    private Board board;
    private Random random = new Random();

    public Generator(Board board) {
        this.board = board;
    }

    public void generate(int holesToMake) {
        board.reset();

        filledBoard(0, 0);

        pokeHoles(holesToMake);
    }

    public boolean filledBoard(int row, int col) {

        if (row == 9) {
            row = 0;
            if (++col == 9) {
                return true;
            }
        }

        if (board.getCell(row, col) != 0) {
            return filledBoard(row + 1, col);
        }

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        for (int num : numbers) {
            if (Validator.isValidMove(board.getBoard(), row, col, num)) {
                board.setCell(row, col, num);
                if (filledBoard(row + 1, col)) return true;
                board.setCell(row, col, 0);
            }

        }
        return false;
    }

    public void pokeHoles(int holesToMake) {
        int holes = 0;
        List<Integer> cellIndices = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            cellIndices.add(i);
        }
        Collections.shuffle(cellIndices);

        for (int index : cellIndices) {
            if (holes >= holesToMake) {
                break;
            }
            int row = index / 9;
            int col = index % 9;
            if (board.getCell(row, col) != 0) {
                board.setCell(row, col, 0);
                holes++;
            }
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.getCell(row, col) != 0) {
                    board.setInitialCell(row, col);
                }
            }
        }
    }
}
