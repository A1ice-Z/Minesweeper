package minesweeperproject;

import java.util.ArrayList;

public class GridImpl implements IGrid {
    private ArrayList<ArrayList<Cell>> grid = new ArrayList<>();

    public GridImpl(int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < columns; j++) {
                grid.get(i).add(new NumberCell());
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
