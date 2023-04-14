package minesweeperproject.game;

import java.util.ArrayList;

/**
 * Class that makes the minesweeper leaderborad for each game
 */

public class MinesweeperLeaderBoard {
    ArrayList<Integer> minesweeperLeaderBoard;
    private final int maxSize = 16;
    private int index;

    /**
     * Creates a new minesweeper leaderboard
     */
    public MinesweeperLeaderBoard() {
        this.minesweeperLeaderBoard = new ArrayList<>(maxSize);
    }

    /**
     * Returns the minesweeper leaderboard
     * 
     * @return The minesweeper leaderboard
     */
    public ArrayList<Integer> getMinesweeperLeaderBoard() {
        return minesweeperLeaderBoard;
    }

    /**
     * Returns the element from a spesific position
     * 
     * @param position The cordinates to the element that they want to get
     * @return The element from a spesific position
     * @throws IllegalArgumentException If the posision is out of bounds
     */
    public int getElement(int position) {
        if (position >= this.minesweeperLeaderBoard.size()) {
            throw new IllegalArgumentException("Dette er ikke en gyldig index");
        }

        return this.minesweeperLeaderBoard.get(position);
    }

    /**
     * Checks if the result is god enouh to be on the leaderboard, and if it is, it
     * is then added to its suitable position
     * 
     * @param result The result that is being added to the leaderboard
     * @throws IllegalArgumentException If the result is negative
     */
    public void addResult(int result) {
        if (result < 0) {
            throw new IllegalArgumentException("Resultatet kan ikke være negativt");
        }
        index = -1;
        int tempSize = minesweeperLeaderBoard.size();

        if (minesweeperLeaderBoard.size() == maxSize) {
            for (int i = 0; i < minesweeperLeaderBoard.size(); i++) {
                if (minesweeperLeaderBoard.get(i) > result) {
                    index = i;

                    minesweeperLeaderBoard.remove(maxSize - 1);
                    minesweeperLeaderBoard.add(index, result);
                    break;
                }
            }
        } else {
            for (int i = 0; i < minesweeperLeaderBoard.size(); i++) {
                if (minesweeperLeaderBoard.get(i) > result) {
                    index = i;
                    minesweeperLeaderBoard.add(index, result);
                    break;
                }
            }
        }
        if (minesweeperLeaderBoard.size() == tempSize && tempSize != maxSize) {
            minesweeperLeaderBoard.add(result);
            index = minesweeperLeaderBoard.size() - 1;
        }
    }

    /**
     * Returns the index to the newest added element
     * 
     * @return The index to the newest added element
     */
    public int getIndex() {
        return index;
    }
}
