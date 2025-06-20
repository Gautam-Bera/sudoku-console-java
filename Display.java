public class Display {

    public static void printBoard(Board board) {
        System.out.println("\n    1 2 3   4 5 6   7 8 9");
        System.out.println("  +-------+-------+-------+");
        for (int row = 0; row < 9; row++) {
            System.out.print((row + 1) + " | ");
            for (int col = 0; col < 9; col++) {
                int cellValue = board.getCell(row, col);

                if (cellValue == 0) {
                    System.out.print("· ");
                } else {
                    System.out.print(cellValue + " ");
                }
                if ((col + 1) % 3 == 0) System.out.print("| ");
            }
            System.out.println();
            if ((row + 1) % 3 == 0) System.out.println("  +-------+-------+-------+");
        }
    }
}
