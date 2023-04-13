package minesweeperproject;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import minesweeperproject.game.Minesweeper;
import minesweeperproject.game.celler.NumberCell;

public class MinesweeperTest {
    private Minesweeper game;

    @Test
    @DisplayName("Tester kontruktøren sin throw new illegalArgument")
    public void testMinesweeperConstructorIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Minesweeper(-1, 2, 2);
        }, "Antall rows, columns eller toltalMines lik eller mindre enn 0");
        assertThrows(IllegalArgumentException.class, () -> {
            new Minesweeper(1, -2, 2);
        }, "Antall rows, columns eller toltalMines lik eller mindre enn 0");
        assertThrows(IllegalArgumentException.class, () -> {
            new Minesweeper(1, 2, -2);
        }, "Antall rows, columns eller toltalMines lik eller mindre enn 0");
        assertThrows(IllegalArgumentException.class, () -> {
            new Minesweeper(0, 0, 0);
        }, "Antall rows, columns eller toltalMines lik eller mindre enn 0");
    }

    @Nested
    @DisplayName("Tester om funksjonen funegrer på riktig måte")
    private class minesweeperGameLogicTest {
        @BeforeEach
        public void setUp() {
            new Minesweeper(5, 5, 5);
        }

        @Test
        @DisplayName("Tester kontrolleren")
        public void testMinesweeperConstructur() {
            assertEquals(20, game.getUnopenedCells().size(), 0.01);
            assertEquals(25, ((List) game.getPlayingGrid()).size(), 0.01);
            assertFalse(game.isGameLost());
        }

        @Test
        @DisplayName("Tester om spiller gjør slik at man ikke kan trykke på en bombe på det første klikket")
        public void testOnFirstClick() {
            game.onFirstClick(0, 0);
            assertTrue(game.getPlayingGrid().getElement(0, 0).isOpen());
            assertFalse(game.getPlayingGrid().getElement(0, 0).display() == -1);
        }

        @Nested
        public class voidClassesTest {
            @BeforeEach
            public void generateMinesSetUp() {
                game.generateMines(5, 0, 0);
                game.setNumbers();
            }

            @Test
            @DisplayName("Tester om det blir generert riktig antall bomber")
            public void testGeneratingMines() {
                int testingBombCount = 0;

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (game.getPlayingGrid().getElement(i, j).display() == -1) {
                            testingBombCount++;
                        }
                    }
                }

                assertEquals(5, testingBombCount, 0.01);
            }

            @Test
            @DisplayName("Tester om numerene som satt på number cellene er riktige")
            public void testSetNumbers() {
                int[][] expectedCellNumbers = {
                        { -1, -1, 2, 1, 0 },
                        { 2, 3, -1, 1, 0 },
                        { 0, 1, 2, 2, 1 },
                        { 1, 1, 1, -1, 1 },
                        { -1, 1, 1, 1, 1 }
                };
                // det er slik at at testen fungerer til tross for at bombene ikke nødvendigvis
                // er plassert på samme sted hver gang, er at det forventede rutenettet
                // (expectedNumbers) er basert på et rutenett der bombene er plassert på
                // bestemte steder (slik det er definert i testen).
                // Så selv om bombene plasseres
                // tilfeldig hver gang, så vil det genererte rutenettet ha de samme tallverdiene
                // som forventet rutenett når det gjelder antall bomber rundt hver celle, så
                // lenge bombene er plassert i samme mønster som i testen.
                // MEN HVORFOR ER DET SLIK!!!! --> Spør Arash

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (game.getPlayingGrid().getElement(i, j) instanceof NumberCell) {
                            assertEquals(expectedCellNumbers[i][j], game.getPlayingGrid().getElement(i, j).display());
                        }
                    }
                }
            }

            @Test
            @DisplayName("Tester om klikk funksjonen fungerer")
            public void testOnClick() {
                int rowCount = 0;
                int column = 0;

                while (game.getPlayingGrid().getElement(rowCount, column).display() == -1) {
                    rowCount++;
                }

                assertTrue(game.getPlayingGrid().getElement(rowCount, column).isOpen());
                assertFalse(game.getUnopenedCells().contains(game.getPlayingGrid().getElement(rowCount, column)));
            }
        }
    }

}
