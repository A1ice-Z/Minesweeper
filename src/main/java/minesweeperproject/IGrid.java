package minesweeperproject;

public interface IGrid {

    // Returns the number of rows in this Grid
    public int getRowCount();

    // Returns the number of columns in this Grid
    public int getColumnCount();

    // Returns the String at the given row and column. Throws an
    // IllegalArgumentException if the row or column is out of range
    public Cell getElement(int row, int column);

    // Sets the String at the given row and column. Throws an
    // IllegalArgumentException if the row or column is out of range
    public void setElement(int row, int column, Cell element);
}