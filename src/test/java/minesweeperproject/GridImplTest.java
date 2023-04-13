package minesweeperproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javafx.scene.control.Cell;
import minesweeperproject.game.GridImpl;
import minesweeperproject.game.celler.NumberCell;

public class GridImplTest {
    private GridImpl testGrid;

    @Test
    @DisplayName("Tester kontruktøren sin throw IllegalArgument")
    public void testInvalidConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GridImpl(0, -1);
        }, "Antall rader og kolonner kan ikke være mindre enn 0");
        assertThrows(IllegalArgumentException.class, () -> {
            new GridImpl(-1, 0);
        }, "Antall rader og kolonner kan ikke være mindre enn 0");
    }

    @Test
    @DisplayName("Tester konstruktøren")
    public void testValisCinstructur() {
        testGrid = new GridImpl(5, 7);
        assertEquals(5, testGrid.getRowCount(), 0.01);
        assertEquals(7, testGrid.getColumnCount(), 0.01);
    }

    @Nested
    public class testSetAndGet {
        @BeforeEach
        public void setUp() {
            testGrid = new GridImpl(2, 2);
        }

        @Test
        @DisplayName("Tester for om element blir satt på riktig posisjon, om om riktig element blir hentet ut")
        public void testGetAndSetElement() {
            NumberCell testCell1 = new NumberCell(0, 0);
            NumberCell testCell2 = new NumberCell(1, 1);
            testGrid.setElement(0, 0, testCell1);
            testGrid.setElement(1, 1, testCell2);

            assertEquals(testCell1, testGrid.getElement(0, 0));
            assertEquals(testCell2, testGrid.getElement(1, 1));
        }

        @Test
        @DisplayName("Tester for om den kaster en exception når man prøver å hente en verdi fra en ugyldig posisjon")
        public void testInvalidGetElement() {
            assertThrows(IndexOutOfBoundsException.class, () -> {
                testGrid.getElement(2, 1);
            }, "Dette er en ugyldig posisjon");
            assertThrows(IndexOutOfBoundsException.class, () -> {
                testGrid.getElement(1, 2);
            }, "Dette er en ugyldig posisjon");
        }

        @Test
        @DisplayName("Tester for om den kaster en exception når man prøver å sette en verdi på en ugyldig posisjon")
        public void testInvalidSetElement() {
            assertThrows(IndexOutOfBoundsException.class, () -> {
                testGrid.setElement(2, 1, new NumberCell(2, 1));
                ;
            }, "Dette er en ugyldig posisjon");
            assertThrows(IndexOutOfBoundsException.class, () -> {
                testGrid.setElement(1, 2, new NumberCell(1, 2));
                ;
            }, "Dette er en ugyldig posisjon");
        }
    }
}
