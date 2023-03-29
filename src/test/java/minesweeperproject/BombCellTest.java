package minesweeperproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import minesweeperproject.game.celler.BombCell;

public class BombCellTest {

    private BombCell cell;
    private BombCell cell2;

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

    // burde ha test for flagged også
}
