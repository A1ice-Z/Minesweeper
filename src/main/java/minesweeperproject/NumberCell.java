package minesweeperproject;

public class NumberCell extends Cell {
    private int number;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "1";
    }
}
