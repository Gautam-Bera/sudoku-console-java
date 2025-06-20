public class Undo {
    private final int row;
    private final int col;
    private final int num;
    private final int oldNum;

    public Undo(int row, int col, int num, int oldNum) {
        this.row = row;
        this.col = col;
        this.num = num;
        this.oldNum = oldNum;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getNum() {
        return num;
    }

    public int getOldNum() {
        return oldNum;
    }
}
