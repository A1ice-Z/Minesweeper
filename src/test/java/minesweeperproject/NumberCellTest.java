package minesweeperproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import minesweeperproject.game.celler.NumberCell;

public class NumberCellTest {

    private NumberCell cell;
    private NumberCell cell2;

    @Test
    @DisplayName("Tester konstruktøren sin IllegalArgumentException")
    public void testInvalidConstructur() {
        assertThrows(IllegalArgumentException.class, () -> {
            new NumberCell(-1, 0);
        }, "Antall rows, columns eller toltalMines lik eller mindre enn 0");
        assertThrows(IllegalArgumentException.class, () -> {
            new NumberCell(1, -2);
        }, "Antall rows, columns eller toltalMines lik eller mindre enn 0");
    }

    @Nested
    public class testNumberCellMethods {
        @BeforeEach
        public void setUp() {
            cell = new NumberCell(1, 1);
            cell.setNumber(3);
            cell2 = new NumberCell(2, 3);
            cell2.setNumber(5);
        }

        @Test
        @DisplayName("Sjekker om den display returnerer riktig verdi")
        public void testBombCellDisplay() {
            assertEquals(3, cell.display());
            assertEquals(5, cell2.display());
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

}
