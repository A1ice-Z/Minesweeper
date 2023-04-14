package minesweeperproject.game.celler;

/**
 * Abstract class that contains the generall data a cell needs
 */

public abstract class Cell {
    private boolean open;
    private boolean flagged;
    private int row;
    private int column;

    /**
     * Creates a new cell places on the cordinates (row, column)
     * 
     * @param row    The y cordinate for the cell
     * @param column The x cordinate for the cell
     * @throws IllegalArgumentException if the cells cordinates are negativ
     */
    public Cell(int row, int column) {
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException("Kordinatene til cellen kan ikke være negativ");
        }
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

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
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
     * Returns the value of the cell which is to be displayed. Differs for bombs and
     * cells
     * 
     * @return Integer
     */
    public abstract Integer display();

    /**
     * Opens up the cell that is clicked
     */
    public void open() {
        open = true;
    };

    public static void main(String[] args) {
        System.out.println();
    }
}
