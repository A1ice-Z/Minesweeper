package minesweeperproject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import minesweeperproject.game.Filbehanding;
import minesweeperproject.util.FileHelper;

public class FilbehandingTest {
    private String tempPath = "TestFil.txt";
    List<String> tempFilInnhold = Arrays.asList("john,1", "markus,20", "lola", "25");

    @BeforeEach
    public void setUp() throws IOException {
        java.nio.file.Files.write(java.nio.file.Paths.get(tempPath), tempFilInnhold);
        // denne funksjonen skriv innholdet tempFilInnhold til en ny midtlertidig fil
        Filbehanding.fileReader(tempPath);
    }

    @Test
    @DisplayName("Tester om lesing fra fil fungerer på riktig måte")
    public void testFileReader() throws IOException {
        ArrayList<String> tempExpectedScores = new ArrayList<>();
        tempExpectedScores.add("Ledertavle: ");
        tempExpectedScores.add("1. john 1");
        tempExpectedScores.add("2. markus 20");
        ArrayList<String> tempScores = Filbehanding.getTempScores();

        Assertions.assertEquals(tempExpectedScores, tempScores);

        java.nio.file.Files.delete(java.nio.file.Paths.get(tempPath));
        // sletter den midtertidige filen som ble laget tidligere i testen
    }

    @Test
    @DisplayName("Tester om skriving til fil fungerer på riktig måte")
    public void testFileWriter() throws IOException {
        Filbehanding.fileWriter(tempPath, tempFilInnhold);

        List<String> lines = FileHelper.readLines(tempPath, false);
        Assertions.assertEquals(tempFilInnhold, lines);

        java.nio.file.Files.delete(java.nio.file.Paths.get(tempPath));
        // sletter den midtertidige filen som ble laget tidligere i testen
    }
}
