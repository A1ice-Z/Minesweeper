package minesweeperproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import minesweeperproject.game.celler.BombCell;

public class BombCellTest {

    private BombCell cell;
    private BombCell cell2;

    @Test
    @DisplayName("Tester konstruktøren sin IllegalArgumentException")
    public void testInvalidConstructur() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BombCell(-1, 0);
        }, "Antall rows, columns eller toltalMines lik eller mindre enn 0");
        assertThrows(IllegalArgumentException.class, () -> {
            new BombCell(1, -2);
        }, "Antall rows, columns eller toltalMines lik eller mindre enn 0");
    }

    @Nested
    public class testNumberCellMethods {

        @BeforeEach
        public void setUp() {
            cell = new BombCell(1, 1);
            cell2 = new BombCell(2, 3);
        }

        @Test
        @DisplayName("Sjekker om den display returnerer riktig verdi")
        public void testBombCellDisplay() {
            assertEquals(-1, cell.display());
            assertEquals(-1, cell2.display());
        }

        @Test
        @DisplayName("Sjekker om cellen returnerer riktig kollonne og rad")
        public void testBombCellRowAndColum() {
            assertEquals(1, cell.getColumn());
            assertEquals(1, cell.getRow());

            assertEquals(3, cell2.getColumn());
            assertEquals(2, cell2.getRow());
        }
    }

    // burde ha test for flagged også
}
