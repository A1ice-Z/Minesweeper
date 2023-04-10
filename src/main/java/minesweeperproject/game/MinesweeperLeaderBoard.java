package minesweeperproject.game;

import java.util.ArrayList;

public class MinesweeperLeaderBoard {
    ArrayList<Integer> minesweeperLeaderBoard;
    private final int maxSize = 20;

    public MinesweeperLeaderBoard() {
        this.minesweeperLeaderBoard = new ArrayList<>(maxSize);

    }

    public ArrayList<Integer> getHighscoreList() {
        return minesweeperLeaderBoard;
    }

    public int getElement(int position) {
        if (position >= this.minesweeperLeaderBoard.size()) {
            throw new IllegalArgumentException("Dette er ikke en gyldig index");
        }

        return this.minesweeperLeaderBoard.get(position);
    }

    public void addResult(int result) {
        int index = 0;
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

}