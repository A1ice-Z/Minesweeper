package minesweeperproject.game;

import java.util.ArrayList;

public class MinesweeperLeaderBoard {
    ArrayList<Integer> minesweeperLeaderBoard;
    private final int maxSize = 16;
    private int index;

    public MinesweeperLeaderBoard() {
        this.minesweeperLeaderBoard = new ArrayList<>(maxSize);
    }

    public ArrayList<Integer> getMinesweeperLeaderBoard() {
        return minesweeperLeaderBoard;
    }

    public int getElement(int position) {
        if (position >= this.minesweeperLeaderBoard.size()) {
            throw new IllegalArgumentException("Dette er ikke en gyldig index");
        }

        return this.minesweeperLeaderBoard.get(position);
    }

    public void addResult(int result) {
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

    public int getIndex() {
        return index;
    }
}
