package minesweeperproject.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import minesweeperproject.util.FileHelper;

public class Filbehanding {
    private static ArrayList<String> tempScores;

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

    public static ArrayList getTempScores() {
        return tempScores;
    }

    public static void fileWriter(String path, List<String> scores) throws IOException {
        FileHelper.writeLines(path, scores);
    }
}
