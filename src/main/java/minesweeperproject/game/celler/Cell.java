package minesweeperproject.game.celler;

public abstract class Cell {
    protected boolean open;
    protected boolean flagged;
    private int row;
    private int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isOpen() {
        return this.open;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public abstract Integer display();

    public abstract void open();

    public static void main(String[] args) {
        System.out.println();
    }
}
