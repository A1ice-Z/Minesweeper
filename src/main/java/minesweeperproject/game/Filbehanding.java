package minesweeperproject.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import minesweeperproject.util.FileHelper;

public class Filbehanding {
    private static ArrayList<String> tempScores;

    /**
     * Read lines from file, and changes the format to a ranking format
     *
     * @param path The path to the file it is supposed to read from
     */
    public static void fileReader(String path) throws IOException {
        List<String> scores = FileHelper.readLines(path, false);
        tempScores = new ArrayList<>();

        tempScores.add("Ledertavle: ");
        for (int i = 0; i < scores.size(); i++) {
            String[] bruker = scores.get(i).split(",");
            if (bruker.length == 2) {
                tempScores.add((i + 1) + ". " + bruker[0] + " " + bruker[1]);
            }
        }
    }

    /**
     * returns the content of the file that was read
     *
     * @return The content of the file that was read
     */
    public static ArrayList getTempScores() {
        return tempScores;
    }

    /**
     * Writes spesific information to file. It overwrites the information that was
     * already there
     *
     * @param path   The path to the file it is supposed to write to
     * @param scores The information that is supposed to be written to the file
     */
    public static void fileWriter(String path, List<String> scores) throws IOException {
        FileHelper.writeLines(path, scores);
    }
}
