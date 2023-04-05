package minesweeperproject.game;

import minesweeperproject.game.celler.Cell;

/**
 * A interface for the Grid used in each game
 */

public interface IGrid {

    /**
     * Returns the number of rows in this Grid
     * 
     * @return The number of rows in this Grid
     */
    public int getRowCount();

    /**
     * Returns the number of columns in this Grid
     * 
     * @return The number of columns in this Grid
     */
    public int getColumnCount();

    /**
     * Returns the String at the given row and column
     * 
     * @return The String at the given row and column
     * 
     * @throws IllegalArgumentException if the row or column is out of range
     */
    public Cell getElement(int row, int column);

    /**
     * Sets the String at the given row and column
     * 
     * @throws IllegalArgumentException if the row or column is out of range
     */
    public void setElement(int row, int column, Cell element);
}