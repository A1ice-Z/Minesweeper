package minesweeperproject.game.celler;

/**
 * Abstract class that contains the general data a cell needs
 */

public abstract class Cell {
    private boolean open;
    private boolean flagged;
    private int row;
    private int column;

    /**
     * Creates a new cell on the specified coordinates (row, column)
     * 
     * @param row    The y coordinate for the cell
     * @param column The x coordinate for the cell
     * @throws IllegalArgumentException if the cells coordinates are negative
     */
    public Cell(int row, int column) {
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException("Kordinatene til cellen kan ikke være negativ");
        }
        this.row = row;
        this.column = column;
    }

    /**
     * 
     * @return If the cell is open
     */
    public boolean isOpen() {
        return this.open;
    }

    /**
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
     * 
     * @return The cells row
     */
    public int getRow() {
        return row;
    }

    /**
     * 
     * @return The cells column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the value of the cell which is to be displayed. Differs between bombs and
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
}
