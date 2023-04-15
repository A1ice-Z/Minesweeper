package minesweeperproject.game.celler;

public class BombCell extends Cell {

    /**
     * Creates a bombcell
     * 
     * @param row    The x cordinate for the cell
     * @param column The y cordinate for the cell
     */
    public BombCell(int row, int column) {
        super(row, column);
    }

    @Override
    public Integer display() {
        return -1;
    }
}
