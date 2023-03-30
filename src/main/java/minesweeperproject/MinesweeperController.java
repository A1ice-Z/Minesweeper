package minesweeperproject;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import minesweeperproject.game.Minesweeper;

public class MinesweeperController {
    @FXML
    private Button easyButton;

    @FXML
    private Button mediumButton;

    @FXML
    private Button hardButton;

    @FXML
    private Button customButton;

    private Minesweeper game;

    public void initialize() {


        makeGridButtons(this.returnEasyGrid());
    }

    private void changeMode(String mode) throws IOException {
        Set<String> modes = new HashSet<>(Arrays.asList("Easy", "Medium", "Hard"));
        if (modes.contains(mode)) {
            Stage primaryStage = (Stage) easyButton.getScene().getWindow();

            primaryStage.setTitle("Minesweeper" + mode);

            FXMLLoader modeLoader = new FXMLLoader(getClass().getResource("Minesweeper" + mode + ".fxml"));
            Parent modePane = modeLoader.load();
            Scene modeScene = new Scene(modePane);

            primaryStage.setScene(modeScene);
            primaryStage.show();
        } else {
            throw new IllegalArgumentException("mode does not exsist");
        }
    }

    @FXML
    public void easyClicked() throws IOException {
        changeMode("Easy");

    }

    @FXML
    public void mediumClicked() throws IOException {
        changeMode("Medium");
    }

    @FXML
    public void hardClicked() throws IOException {
        changeMode("Hard");
    }

    public void updateGame() {

    }

}
