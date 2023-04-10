package minesweeperproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.print.Collation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import minesweeperproject.game.GridImpl;
import minesweeperproject.game.Minesweeper;
import minesweeperproject.game.MinesweeperTimer;

public class MinesweeperController {
    @FXML
    private Button easyButton, mediumButton, hardButton;

    @FXML
    private Button backToMenuButton, newGameButton;

    @FXML
    private GridPane easyGrid = new GridPane();

    @FXML
    private GridPane mediumGrid = new GridPane();

    @FXML
    private GridPane hardGrid = new GridPane();

    @FXML
    private HBox navBarBox = new HBox();

    @FXML
    private HBox timerBox = new HBox();

    @FXML
    private VBox winningScoreBox = new VBox();

    @FXML
    private Button lagreSpillerButton;

    @FXML
    private TextField spillerNavn = new TextField();

    @FXML
    private Pane spillerTid = new Pane();

    @FXML
    private Pane spillerNavnTekst = new Pane();

    private MinesweeperTimer timer = new MinesweeperTimer();
    private int recordTime;

    private static Minesweeper game;
    private List<StackPane> bombs = new ArrayList<StackPane>();
    private List<StackPane> falseFlags = new ArrayList<StackPane>();
    private int clickCount;

    public void initialize() {
        makeGridButtons(easyGrid, 9, 9);
        makeGridButtons(mediumGrid, 16, 16);
        makeGridButtons(hardGrid, 16, 30);

        timer.setAlignment(Pos.CENTER_RIGHT);
        timerBox.getChildren().add(timer);
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
    public void newGameClicked() throws IOException {
        makeNewGame(easyGrid);
    }

    public void makeNewGame(GridPane grid) {
        bombs = new ArrayList<StackPane>();
        falseFlags = new ArrayList<StackPane>();
        clickCount = 0;
        System.out.println(easyGrid.getChildren());
        easyGrid.getChildren().clear();
        System.out.println(easyGrid.getChildren());
        makeGridButtons(easyGrid, 9, 9);
        System.out.println(easyGrid.getChildren());
        setGame("Easy");
    }

    public void onMouseClick(MouseEvent event, GridPane grid, Node node) {
        if (game.isGameLost())
            return;
        Integer rowIndex = GridPane.getRowIndex(node);
        Integer colIndex = GridPane.getColumnIndex(node);
        GridImpl gameGrid = game.getPlayingGrid();
        StackPane stackPane = (StackPane) node;
        System.out.println(event.getButton());
        // finner ut om det er høyre eller venstre click
        if (event.getButton() == MouseButton.SECONDARY) {
            if (clickCount == 0 || gameGrid.getElement(rowIndex, colIndex).isOpen()) {
                return;
            }
            if (!gameGrid.getElement(rowIndex, colIndex).isFlagged()) {
                Image flagImage = new Image(getClass().getResource("flag.png").toExternalForm(), 30, 30, false, true);
                ImageView imageView = new ImageView(flagImage);
                imageView.setOnMouseClicked(click -> {
                    onMouseClick(click, grid, stackPane);
                });
                stackPane.getChildren().add(imageView);
                gameGrid.getElement(rowIndex, colIndex).setFlagged(true);
                if (!bombs.contains(stackPane))
                    falseFlags.add(stackPane);

            } else {
                if (falseFlags.contains(stackPane))
                    falseFlags.remove(stackPane);
                stackPane.getChildren().remove(2);
                gameGrid.getElement(rowIndex, colIndex).setFlagged(false);
            }
        } else if (event.getButton() == MouseButton.PRIMARY) {
            if (clickCount == 0) {
                game.onFirstClick(rowIndex, colIndex);
                clickCount++;
                makeGame(grid);
                timer.start();
            } else {
                game.onClick(rowIndex, colIndex);
            }
            if (game.isGameLost()) {
                gameOver(rowIndex, colIndex, grid);
            }
            updateGame(grid);
            checkWin(grid);
        }
    }

    public void gameOver(int row, int column, GridPane gridPane) {
        timer.reset();
        timer.stop();
        gridPane.getChildren().get(row * gridPane.getColumnCount() + column).setStyle("-fx-background-color: RED");
        ;
        for (StackPane bomb : bombs) {
            if (bomb.getChildren().size() != 3)
                bomb.getChildren().remove(1);
        }
        for (StackPane falseFlag : falseFlags) {
            falseFlag.getChildren().remove(0, 3);
            Image bombImage = new Image(getClass().getResource("bomb.png").toExternalForm(), 30, 30, false,
                    true);
            ImageView imageView = new ImageView(bombImage);
            Image xImage = new Image(getClass().getResource("X.png").toExternalForm(), 30, 30, false,
                    true);
            ImageView imageView2 = new ImageView(xImage);
            falseFlag.getChildren().add(imageView);
            falseFlag.getChildren().add(imageView2);
        }
    }

    public void checkWin(GridPane gridPane) {
        if (game.getUnopenedCells().isEmpty()) {
            timer.stop();
            recordTime = timer.getTime();
            int rowIndex = 0;
            int columnIndex = 0;
            for (StackPane bomb : bombs) {
                if (bomb.getChildren().size() != 3) {
                    Image flagImage = new Image(getClass().getResource("flag.png").toExternalForm(), 30, 30, false,
                            true);
                    ImageView imageView = new ImageView(flagImage);
                    bomb.getChildren().add(imageView);
                }
                rowIndex = (columnIndex + 1) == gridPane.getColumnCount() ? ++rowIndex : rowIndex;
                columnIndex = (columnIndex + 1) == gridPane.getColumnCount() ? 0 : ++columnIndex;
            }

            setRecordTime(recordTime);
        }
    }

    private void setRecordTime(int recordTime) {
        easyGrid.setVisible(false);
        navBarBox.setVisible(false);
        timerBox.setVisible(false);
        winningScoreBox.setVisible(true);
        winningScoreBox.setDisable(false);
        spillerNavnTekst.getChildren().add(new Label("Skriv inn brukernavnet ditt under: "));
        spillerTid.getChildren().add(new Label("Tid: " + recordTime + " sekunder"));
    }

    public void makeGame(GridPane gridPane) {
        int rowIndex = 0;
        int columnIndex = 0;
        GridImpl grid = game.getPlayingGrid();
        for (Node node : gridPane.getChildren()) {
            StackPane stackPane = (StackPane) node;
            Text text = new Text((grid.getElement(rowIndex, columnIndex).display().toString()));
            text.getStyleClass().add("n" + (grid.getElement(rowIndex, columnIndex).display().toString()));
            if (grid.getElement(rowIndex, columnIndex).display() == -1) {
                bombs.add(stackPane);
                Image bombImage = new Image(getClass().getResource("bomb.png").toExternalForm(), 30, 30, false,
                        true);
                ImageView imageView = new ImageView(bombImage);
                stackPane.getChildren().add(0, imageView);
            } else
                stackPane.getChildren().add(0, text);

            rowIndex = (columnIndex + 1) == grid.getColumnCount() ? ++rowIndex : rowIndex;
            columnIndex = (columnIndex + 1) == grid.getColumnCount() ? 0 : ++columnIndex;
        }
    }

    // mulig å slå denne sammen med makegame kanskje
    public void updateGame(GridPane gridPane) {
        int rowIndex = 0;
        int columnIndex = 0;
        GridImpl currentGrid = game.getPlayingGrid();
        for (Node node : gridPane.getChildren()) {
            StackPane stackPane = (StackPane) node;
            if (currentGrid.getElement(rowIndex, columnIndex).isOpen() && stackPane.getChildren().size() == 2) {
                stackPane.getChildren().remove(1);
            }
            rowIndex = (columnIndex + 1) == currentGrid.getColumnCount() ? ++rowIndex : rowIndex;
            columnIndex = (columnIndex + 1) == currentGrid.getColumnCount() ? 0 : ++columnIndex;
        }
    }
}
