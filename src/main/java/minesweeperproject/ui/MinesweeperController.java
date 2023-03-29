package minesweeperproject.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import minesweeperproject.game.Minesweeper;
import minesweeperproject.game.celler.Cell;

public class MinesweeperController {
    @FXML
    private Button easyButton, mediumButton, hardButton, customButton;

    @FXML
    private Button exitButton, removeButton;

    private Minesweeper game;

    public void initialize() {

    }

    public void updateGame() {

    }

    public void makeGameBox(int row, int column, Cell cell) {

    }
}
