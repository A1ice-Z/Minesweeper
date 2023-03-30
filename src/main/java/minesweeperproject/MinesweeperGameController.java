package minesweeperproject;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MinesweeperGameController {
    @FXML
    private Button backToMenuButton;

    @FXML
    private Button newGameButton;

    @FXML
    private GridPane easyGrid;

    @FXML
    private GridPane mediumGrid;

    @FXML
    private GridPane hardGrid;

    public GridPane returnHardGrid() {
        return this.hardGrid;
    }

    public GridPane returnEasyGrid() {
        return this.easyGrid;
    }

    public GridPane returnMediumGrid() {
        return this.mediumGrid;
    }

    public void makeGridButtons(GridPane grid) {
        int row = grid.getRowCount();
        int column = grid.getColumnCount();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Button button = new Button();
                grid.add(button, j, i);
            }
        }
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
}
