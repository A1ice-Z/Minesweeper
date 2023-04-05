package minesweeperproject.game.celler;

public class NumberCell extends Cell {
    private int number;

    /**
     * Creates a numbercell --> vet ikke om dette er nok
     * 
     * @param row    The x cordinate for the cell
     * @param column The y cordinate for the cell
     */
    public NumberCell(int row, int column) {
        super(row, column);
    }

    /**
     * Gives the numbercell the number of bomb around it
     * 
     * @param number The amount of bombs there is around this cell
     */
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Integer display() {
        return number;
    }

    @Override
    public void open() {
        this.open = true;
    }
}
