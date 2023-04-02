package minesweeperproject;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.Collation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import minesweeperproject.game.GridImpl;
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

    private static Minesweeper game;
    private int clickCount;

    public void initialize() {
        makeGridButtons(easyGrid, 9, 9);
        makeGridButtons(mediumGrid, 16, 16);
        makeGridButtons(hardGrid, 16, 30);
        makeCustomGridButtons(customGrid, 20);
    }

    @FXML
    public void makeGridButtons(GridPane grid, int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                StackPane stackPane = new StackPane();
                stackPane.getStylesheets().add(getClass().getResource("numberStyle.css").toExternalForm());
                stackPane.setMinSize(30, 30);
                grid.add(stackPane, j, i);
                Pane unopenedPane = new Pane();
                unopenedPane.setMinSize(30, 30);
                unopenedPane.setStyle("-fx-border-color: BLACK; -fx-background-color: #BDBDBD");
                stackPane.getChildren().add(unopenedPane);
                unopenedPane.setOnMouseClicked(event -> {
                    onMouseClick(event, grid, stackPane);
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

    public void setGame(String mode) {
        if (mode.equals("Easy")) {
            System.out.println(game);
            game = new Minesweeper(9, 9, 10);
            System.out.println(game);
        } else if (mode.equals("Medium"))
            game = new Minesweeper(16, 16, 40);
        else
            game = new Minesweeper(16, 30, 99);
    }

    private void changeMode(String mode) throws IOException {
        Set<String> modes = new HashSet<>(Arrays.asList("Easy", "Medium", "Hard"));
        if (modes.contains(mode)) {
            setGame(mode);
            clickCount = 0;
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

    public void onMouseClick(MouseEvent event, GridPane grid, Node node) {
        Integer rowIndex = GridPane.getRowIndex(node);
        Integer colIndex = GridPane.getColumnIndex(node);
        System.out.println(event.getButton());
        // finner ut om det er høyre eller venstre click
        if (event.getButton() == MouseButton.SECONDARY) {
            if (clickCount == 0) {
                return;
            }
        } else if (event.getButton() == MouseButton.PRIMARY) {
            if (clickCount == 0) {
                System.out.println(game);
                game.onFirstClick(rowIndex, colIndex);
                clickCount++;
                makeGame(grid);
            }

        }
    }

    public void makeGame(GridPane gridPane) {
        int rowIndex = 0;
        int columnIndex = 0;
        GridImpl grid = game.getPlayingGrid();
        for (Node node : gridPane.getChildren()) {
            StackPane stackPane = (StackPane) node;
            Text text = new Text((grid.getElement(rowIndex, columnIndex).display().toString()));
            text.getStyleClass().add("n" + (grid.getElement(rowIndex, columnIndex).display().toString()));
            rowIndex = (columnIndex + 1) == grid.getColumnCount() ? ++rowIndex : rowIndex;
            columnIndex = (columnIndex + 1) == grid.getColumnCount() ? 0 : ++columnIndex;
            stackPane.getChildren().add(text);
        }
    }

}
