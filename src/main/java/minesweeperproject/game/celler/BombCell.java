package minesweeperproject.game.celler;

public class BombCell extends Cell {

    public BombCell(int row, int column) {
        super(row, column);
    }

    @Override
    public int display() {
        return -1;
    }

}
