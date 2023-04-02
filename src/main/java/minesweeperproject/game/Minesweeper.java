package minesweeperproject.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import minesweeperproject.game.celler.BombCell;
import minesweeperproject.game.celler.NumberCell;

public class Minesweeper {
    private GridImpl playingGrid;
    private int totalMines;
    private Collection<NumberCell> unopenedCells = new ArrayList<>();

    public Minesweeper(int rows, int columns, int totalMines) {
        this.totalMines = totalMines;
        playingGrid = new GridImpl(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                unopenedCells.add((NumberCell) playingGrid.getElement(i, j));
            }
        }
    }

    // kanskje bytte param til cell
    public void onFirstClick(int row, int column) {
        playingGrid.getElement(row, column).open();
        generateMines(totalMines, row, column);
        setNumbers();
        unopenedCells.remove(playingGrid.getElement(row, column));
    }

    public void generateMines(int totalMines, int firstCellRow, int firstCellColumn) {
        HashSet<String> setOfMines = new HashSet<>();
        while (setOfMines.size() != totalMines) {
            int row = new Random().nextInt(playingGrid.getRowCount());
            int column = new Random().nextInt(playingGrid.getColumnCount());
            String cellID = String.format("%d%d", row, column);
            if (!setOfMines.contains(cellID) && !cellID.equals(String.format("%d%d", firstCellRow, firstCellColumn))) {
                setOfMines.add(cellID);
                unopenedCells.remove(playingGrid.getElement(row, column));
                playingGrid.setElement(row, column, new BombCell(row, column));
            }
        }
    }

    public void setNumbers() {
        for (NumberCell numberCell : unopenedCells) {
            int sum = 0;
            int y = numberCell.getRow();
            int x = numberCell.getColumn();

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (y + j < playingGrid.getRowCount() && y + j >= 0 && x + i < playingGrid.getColumnCount()
                            && x + i >= 0)
                        if (playingGrid.getElement(y + j, x + i).display() == -1)
                            sum++;
                }
            }
            numberCell.setNumber(sum);
        }
    }

    public void onClick(int row, int column) {

        if (playingGrid.getElement(row, column).isFlagged() || playingGrid.getElement(row, column).isOpen())
            return;
        // if venstreclick
        if (playingGrid.getElement(row, column).display() == -1) {
            // bombe
        } else {
            playingGrid.getElement(row, column).open();
            unopenedCells.remove(playingGrid.getElement(row, column));
        }
        // primary= venstre click and secondary = høyre click button? sjekke for det
    }

    public GridImpl getPlayingGrid() {
        return playingGrid;
    }

    public void statusGame() {

    }

    public static void main(String[] args) {
        var test = new Minesweeper(3, 3, 5);
        test.onFirstClick(0, 0);
        System.out.println(test);
    }
}
