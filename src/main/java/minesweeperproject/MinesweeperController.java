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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import minesweeperproject.game.Minesweeper;

public class MinesweeperController {
    @FXML
    private Button easyButton, mediumButton, hardButton, customButton;

    @FXML
    private Button backToMenuButton, newGameButton;

    @FXML
    private GridPane easyGrid = new GridPane();

    @FXML
    private GridPane mediumGrid = new GridPane();

    @FXML
    private GridPane hardGrid = new GridPane();

    @FXML
    private GridPane customGrid = new GridPane();

    private Minesweeper game;

    public void initialize() {
        makeGridButtons(easyGrid);
        makeGridButtons(mediumGrid);
        makeGridButtons(hardGrid);
        makeCustomGridButtons(customGrid, 20);
    }

    @FXML
    public void makeGridButtons(GridPane grid) {
        int row = grid.getRowCount();
        int column = grid.getColumnCount();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Button button = new Button();
                button.setMinSize(22, 1);
                button.setStyle("-fx-border-color: BLACK");
                grid.add(button, j, i);

                button.setOnAction(b -> {
                    button.setDisable(true);
                });
            }
        }
    }

    @FXML
    public void makeCustomGridButtons(GridPane grid, int sides) {
        for (int a = 0; a < sides; a++) {
            for (int b = 0; b < sides; b++) {
                Button button = new Button();
                button.setPrefSize(100, 30);
                button.setStyle("-fx-border-color: BLACK");
                grid.add(button, b, a);

                button.setOnAction(g -> {
                    button.setDisable(true);
                });
            }
        }
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

    @FXML
    public void customClick() throws IOException {
        System.out.println("hei");
        Stage primaryStage = (Stage) customButton.getScene().getWindow();

        primaryStage.setTitle("TeststingFile");

        FXMLLoader modeLoader = new FXMLLoader(getClass().getResource("TeststingFile.fxml"));
        Parent modePane = modeLoader.load();
        Scene modeScene = new Scene(modePane);

        primaryStage.setScene(modeScene);
        primaryStage.show();

    }

    @FXML
    public void backToMenuClicked() throws IOException {
        Stage primaryStage = (Stage) backToMenuButton.getScene().getWindow();

        primaryStage.setTitle("Minesweeper");

        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("Minesweeper.fxml"));
        Parent menuPane = menuLoader.load();
        Scene menuScene = new Scene(menuPane);

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    @FXML
    public void newGameClicked() {

    }

    @FXML
    public void onMouseClick(MouseEvent event) {
        System.out.println(event.getButton());
        // finner ut om det er høyre eller venstre click
        if (event.getButton() == MouseButton.SECONDARY) {

        } else if (event.getButton() == MouseButton.PRIMARY) {

        }
    }

    public void updateGame() {

    }

}
