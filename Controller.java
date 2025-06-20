import java.util.Scanner;
import java.util.Stack;

public class Controller {
    private Board mainBoard;
    private Generator generator;
    private Scanner sc;
    private Stack<Undo> undo;
    private final int HOLES = 45;

    public Controller() {
        this.mainBoard = new Board();
        this.generator = new Generator(mainBoard);
        this.sc = new Scanner(System.in);
        this.undo = new Stack<>();
    }

    public void start() {
        System.out.println("\n--- Welcome to Console Sudoku! ---");
        startNewGame();
        play();
    }

    public void startNewGame() {
        generator.generate(HOLES);
        undo.clear();
    }


    public void play() {
        while (true) {
            Display.printBoard(mainBoard);

            if (isBoardFull() && Validator.isSolved(mainBoard.getBoard())) {
                System.out.println("\nCongratulations! You have solved the puzzle!");
                break;
            }
            System.out.println("\nEnter your move: row column number (e.g., '1 2 5')");
            System.out.println("Type 'new' for a new game, Type 'undo' to revert last move or 'exit' to quit.");
            System.out.print("-> ");
            String input = sc.nextLine().trim().toLowerCase();

            switch (input) {
                case "exit":
                    System.out.println("Thanks for playing!");
                    return;
                case "new":
                    System.out.println("Starting a new game...");
                    startNewGame();
                    continue;
                case "undo":
                    undoLastMove();
                default:
                    if (!processInput(input)) {
                        System.err.println("\n--- Invalid input or move. Please try again. ---");
                    }
                    break;
            }
        }
    }

    public boolean processInput(String input) {
        String[] parts = input.split("\\s+");
        if (parts.length != 3) {
            return false;
        }
        try {
            int row = Integer.parseInt(parts[0]) - 1;
            int col = Integer.parseInt(parts[1]) - 1;
            int num = Integer.parseInt(parts[2]);

            if (row < 0 || row > 8 || col < 0 || col > 8 || num < 1 || num > 9) {
                System.err.println("Values out of range. Row, col, and number must be between 1 and 9.");
                return false;
            }

            if (mainBoard.getInitialCell(row, col)) {
                System.err.println("Cannot change the initial puzzle numbers.");
                return false;
            }

            if (Validator.isValidMove(mainBoard.getBoard(), row, col, num)) {
                int oldNum = mainBoard.getCell(row, col);
                    mainBoard.setCell(row, col, num);
                    undo.push(new Undo(row, col, num, oldNum));
                return true;
            } else {
                System.err.println("That move is not valid. A number cannot be repeated in the same row, column, or 3x3 box.");
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void undoLastMove() {
        if (!undo.isEmpty()) {
            Undo lastMove = undo.pop();
            mainBoard.setCell(lastMove.getRow(), lastMove.getCol(), lastMove.getOldNum());
            System.out.println("\n--- Undid the last move. ---");
        } else {
            System.out.println("\n--- No moves to undo. ---");
        }
    }

    public boolean isBoardFull() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (mainBoard.getCell(row, col) == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
