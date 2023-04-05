package minesweeperproject.game.celler;

/**
 * Abstract class that contains the generall data a cell needs
 */

public abstract class Cell {
    protected boolean open;
    protected boolean flagged;
    private int row;
    private int column;

    /**
     * Creates a new cell places on the cordinates (row, column)---> usikker på om
     * det er dette eller (column, row)
     * 
     * @param row    The x cordinate for the cell
     * @param column The y cordinate for the cell
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns if the cell is open
     * 
     * @return If the cell is open
     */
    public boolean isOpen() {
        return this.open;
    }

    /**
     * Returns if the cell is flagged
     * 
     * @return If the cell is flagged
     */
    public boolean isFlagged() {
        return flagged;
    }

    /**
     * Returns the cells row
     * 
     * @return The cells row
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the cells column
     * 
     * @return The cells column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the value of the cell --> usikker på om dette er riktig def
     * 
     * @return Integer
     */
    public abstract Integer display();

    /**
     * Opens up the cell that is clicked
     */
    public abstract void open();

    public static void main(String[] args) {
        System.out.println();
    }
}
