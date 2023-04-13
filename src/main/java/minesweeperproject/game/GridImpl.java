package minesweeperproject.game;

import java.util.ArrayList;

import minesweeperproject.game.celler.Cell;
import minesweeperproject.game.celler.NumberCell;

/**
 * Class that makes the grid for each Minesweeper game
 */

public class GridImpl implements IGrid {
    private ArrayList<ArrayList<Cell>> grid = new ArrayList<>();

    /**
     * Creates a new Grid for a game
     *
     * @param rows    The amount of rows the Grid is gonna have
     * @param columms Thes columns of rows the Grid is gonna have
     * @throws IllegalArgumentException If the row or column is invalid
     */

    public GridImpl(int rows, int columns) {
        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("Your row or column can't be a negative number");
        }
        for (int i = 0; i < rows; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < columns; j++) {
                grid.get(i).add(new NumberCell(i, j));
            }
        }
    }

    @Override
    public int getRowCount() {
        return grid.size();
    }

    @Override
    public int getColumnCount() {
        return grid.get(0).size();
    }

    @Override
    public Cell getElement(int row, int column) {

        return grid.get(row).get(column);
    }

    @Override
    public void setElement(int row, int column, Cell element) {
        grid.get(row).set(column, element);
    }

}
