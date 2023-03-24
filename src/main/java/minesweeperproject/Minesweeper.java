package minesweeperproject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class Minesweeper {
    private GridImpl grid;
    private int totalMines;
    private Collection<Cell> Cells = new ArrayList<>();

    public Minesweeper(int rows, int columns, int totalMines) {
        this.totalMines = totalMines;
        grid = new GridImpl(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cells.add(grid.getElement(i, j));
            }
        }
    }

    public void onFirstClick(int row, int column) {
        Cells.remove(grid.getElement(row, column));
        generateMines(totalMines);
        setNumbers();
    }

    public void generateMines(int totalMines) {
        HashSet<String> setOfMines = new HashSet<>();
        while (setOfMines.size() != totalMines) {
            int row = new Random().nextInt(grid.getRowCount());
            int column = new Random().nextInt(grid.getColumnCount());
            String cellID = String.format("%d%d", row, column);
            if (!setOfMines.contains(cellID)) {
                setOfMines.add(cellID);
                Cells.remove(grid.getElement(row, column));
                grid.setElement(row, column, new BombCell(row, column));
            }
        }
    }

    public void setNumbers() {
        for (Cell numberCell : Cells) {
            int sum = 0;
            int y = numberCell.getRow();
            int x = numberCell.getColumn();
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    try {
                        if (grid.getElement(y + j, x + i).display() == -1)
                            sum++;
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                }
            }
            numberCell.setNumber(sum);
        }
    }

    public static void main(String[] args) {
        var test = new Minesweeper(3, 3, 5);
        test.onFirstClick(0, 0);
        System.out.println(test);
    }
}
