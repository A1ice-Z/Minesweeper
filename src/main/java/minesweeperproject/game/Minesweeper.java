package minesweeperproject.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import minesweeperproject.game.celler.BombCell;
import minesweeperproject.game.celler.Cell;
import minesweeperproject.game.celler.NumberCell;

/**
 * Minesweeper game logic
 */

public class Minesweeper {
    private GridImpl playingGrid;
    private int totalMines;
    private Collection<NumberCell> unopenedCells = new ArrayList<>();
    private boolean gameLost = false;

    /**
     * Creates a new Minesweeper game
     * 
     * @param rows       The amount of rows this games grid has
     * @param columns    The amount of columns this games grid has
     * @param totalMines The amount of bombs there exists in the grid
     * @throws IllegalArgumentException If rows, columns or toltalmines is equal or
     *                                  lower than 0. Or if there are mines equal to
     *                                  or more than the amount of cells in the grid
     */
    public Minesweeper(int rows, int columns, int totalMines) {
        if (totalMines <= 0 || rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException(
                    "Man kan ikke sette antall rows, columns eller toltalMines lik eller mindre enn 0");
        }
        if (totalMines >= rows * columns) {
            throw new IllegalArgumentException("For mange miner");
        }
        this.totalMines = totalMines;
        playingGrid = new GridImpl(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                unopenedCells.add((NumberCell) playingGrid.getElement(i, j));
            }
        }
    }

    /**
     * On the players first click the board will be generated
     * 
     * @param row    The row of the cell the player clicked
     * @param column The column of the cell the player clicked
     */
    public void onFirstClick(int row, int column) {
        validateCell(row, column);
        generateMines(row, column);
        setNumbers();
        unopenedCells.remove(playingGrid.getElement(row, column));
        onClick(row, column);
    }

    /**
     * Distributes the amount of bombs a game has randomly on the games grid
     * 
     * @param firstCellRow    The y cordinates to the first cell that is clicked on
     *                        when the game starts
     * @param firstCellColumn The x cordinates to the first cell that is clicked on
     *                        when the game starts
     */
    public void generateMines(int firstCellRow, int firstCellColumn) {
        validateCell(firstCellRow, firstCellColumn);
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

    /**
     * Iterates through all the cells in the grid that is not a bombcell, and
     * generates the cells numbers based on the amount of bombs there is around the
     * cell
     */
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

    /**
     * If the cell is openable, checks is it is a bomb or numbercell. If its a bomb
     * its game over, if its a numbercell that cell gets opened. If the numbercell
     * is 0, the 8 cells around are also opened.
     * 
     * @param row    The y cordinate for the cell
     * @param column The x cordinate for the cell
     */
    public void onClick(int row, int column) {
        validateCell(row, column);

        Cell clickedCell = playingGrid.getElement(row, column);

        if (clickedCell.isFlagged() || clickedCell.isOpen())
            return;
        if (clickedCell.display() == -1) {
            gameLost = true;
        } else {
            clickedCell.open();
            unopenedCells.remove(clickedCell);
            if (clickedCell.display() == 0) {
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (row + j < playingGrid.getRowCount() && row + j >= 0
                                && column + i < playingGrid.getColumnCount()
                                && column + i >= 0)
                            onClick(row + j, column + i);
                    }
                }
            }
        }
    }

    /**
     * 
     * @return The current Minesweeper games amount of unopened cells
     */
    public Collection<NumberCell> getUnopenedCells() {
        return unopenedCells;
    }

    /**
     * 
     * @return The current Minesweeper games grid
     */
    public GridImpl getPlayingGrid() {
        return playingGrid;
    }

    /**
     * 
     * @return The current Minesweeper games status
     */
    public boolean isGameLost() {
        return gameLost;
    }

    /**
     * 
     * @param row    the y coordinate of the cell
     * @param column the x coordinate of the cell
     * 
     * @throws IllegalArgumentException if the cell is invalid
     */
    public void validateCell(int row, int column) {
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException("Kordinatene til cellen kan ikke være mindre enn 0");
        }
        if (row >= playingGrid.getRowCount() || column >= playingGrid.getColumnCount()) {
            throw new IllegalArgumentException("Koordinatene er utenfor grid");
        }
    }

    public static void main(String[] args) {
        var test = new Minesweeper(3, 3, 5);
        test.onFirstClick(0, 0);
        System.out.println(test);
    }
}
