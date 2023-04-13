package minesweeperproject.game.celler;

public class NumberCell extends Cell {
    private int number;

    /**
     * Creates a numbercell
     * 
     * @param row    The x cordinate for the cell
     * @param column The y cordinate for the cell
     */
    public NumberCell(int row, int column) {
        super(row, column);
    }

    /**
     * Gives the cell a number value
     * 
     * @param number The amount of bombs there is around this cell
     */
    public void setNumber(int number) {
        if (number > 8 || number < 0) {
            throw new IllegalArgumentException("A cell can't have this amount of bombs around it");
        }
        this.number = number;
    }

    @Override
    public Integer display() {
        return number;
    }
}
