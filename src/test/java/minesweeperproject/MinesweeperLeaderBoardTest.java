package minesweeperproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import minesweeperproject.game.MinesweeperLeaderBoard;

public class MinesweeperLeaderBoardTest {
    private MinesweeperLeaderBoard minesweeperLeaderBoard;

    @BeforeEach
    public void setUp() {
        minesweeperLeaderBoard = new MinesweeperLeaderBoard();
    }

    @Test
    @DisplayName("Tester om henting av board fungerer")
    public void testGetMinesweeperLeaderBoard() {
        ArrayList<Integer> tempResults = minesweeperLeaderBoard.getMinesweeperLeaderBoard();
        assertNotNull(tempResults);
        assertTrue(tempResults.isEmpty());
    }

    @Test
    @DisplayName("Tester om resultat blir lagt til på riktig måte")
    public void testAddResultWithMaxLeaderBoard() {
        for (int i = 0; i <= 16; i++) {
            minesweeperLeaderBoard.addResult(i * 10);
        }
        minesweeperLeaderBoard.addResult(15);

        ArrayList<Integer> tempResults = minesweeperLeaderBoard.getMinesweeperLeaderBoard();

        assertNotNull(minesweeperLeaderBoard);
        assertEquals(16, tempResults.size(), 0.01);
        assertEquals(0, tempResults.get(0), 0.01);
        assertEquals(10, tempResults.get(1), 0.01);
        assertEquals(15, tempResults.get(2), 0.01);
        assertEquals(20, tempResults.get(3), 0.01);
    }

    @Nested
    public class testSetAndGetMethods {
        @BeforeEach
        public void setAndGetMethodsSetUp() {
            minesweeperLeaderBoard.addResult(10);
            minesweeperLeaderBoard.addResult(40);
            minesweeperLeaderBoard.addResult(30);
            minesweeperLeaderBoard.addResult(100);
        }

        @Test
        @DisplayName("Tester om resultat blir lagt til på riktig måte")
        public void testAddResult() {
            ArrayList<Integer> tempResults = minesweeperLeaderBoard.getMinesweeperLeaderBoard();

            assertNotNull(tempResults);
            assertEquals(4, tempResults.size(), 0.01);
            assertEquals(10, tempResults.get(0), 0.01);
            assertEquals(30, tempResults.get(1), 0.01);
            assertEquals(40, tempResults.get(2), 0.01);
            assertEquals(100, tempResults.get(3), 0.01);
        }

        @Test
        @DisplayName("Tester om indexen blir tent ut riktig")
        public void testGetIndex() {
            minesweeperLeaderBoard.addResult(1);
            assertEquals(0, minesweeperLeaderBoard.getIndex(), 0.01);

            minesweeperLeaderBoard.addResult(130);
            assertEquals(5, minesweeperLeaderBoard.getIndex(), 0.01);

            minesweeperLeaderBoard.addResult(60);
            assertEquals(4, minesweeperLeaderBoard.getIndex(), 0.01);
        }

        @Test
        @DisplayName("Tester om henting av element fra board henter ut riktig element")
        public void testGetElementValidPosition() {
            assertEquals(10, minesweeperLeaderBoard.getElement(0), 0.01);
            assertEquals(30, minesweeperLeaderBoard.getElement(1), 0.01);
            assertEquals(40, minesweeperLeaderBoard.getElement(2), 0.01);
            assertEquals(100, minesweeperLeaderBoard.getElement(3), 0.01);
        }

        @Test
        @DisplayName("Tester om henting av element fra board kaster en illegalArgument exception for ugyldig posisjon")
        public void testGetElementInvalidPosition() {
            assertThrows(IllegalArgumentException.class, () -> {
                minesweeperLeaderBoard.getElement(5);
            }, "Man kan ikke hente ut et element fra en posisjon som ikke eksisterer");
        }
    }
}
