package minesweeperproject.game.celler;

public class BombCell extends Cell {

    /**
     * Creates a bombcell --> vet ikke om dette er nok
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

    @Override
    public void open() {
        System.out.println("game over");
        this.open = true;
    }

}
