package minesweeperproject;

public class NumberCell extends Cell {
    public NumberCell(int row, int column) {
        super(row, column);
    }

    private int number;

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int display() {
        return number;
    }
}
