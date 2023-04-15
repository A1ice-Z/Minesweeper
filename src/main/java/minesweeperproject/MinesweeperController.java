package minesweeperproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.print.Collation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
import minesweeperproject.game.MinesweeperLeaderBoard;
import minesweeperproject.game.MinesweeperTimer;
import minesweeperproject.util.FileHelper;
import minesweeperproject.game.Filbehanding;

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
    private Text minesLeftText;

    @FXML
    private HBox navBarBox = new HBox();

    @FXML
    private HBox minesLeftBox = new HBox();

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

    private Image flagImage = new Image(getClass().getResource("flag.png").toExternalForm(), 30, 30, false, true);
    private Image bombImage = new Image(getClass().getResource("bomb.png").toExternalForm(), 30, 30, false,
            true);
    private Image xImage = new Image(getClass().getResource("X.png").toExternalForm(), 30, 30, false,
            true);
    private static final String EASY_LEADERBOARD = "./src/main/resources/minesweeperproject/EasyLeaderboard.txt";
    private static final String MEDIUM_LEADERBOARD = "./src/main/resources/minesweeperproject/MediumLeaderboard.txt";
    private static final String HARD_LEADERBOARD = "./src/main/resources/minesweeperproject/HardLeaderboard.txt";
    private MinesweeperTimer timer = new MinesweeperTimer();
    private int recordTime;
    private MinesweeperLeaderBoard leaderBoard = new MinesweeperLeaderBoard();

    private static Minesweeper game;
    private List<StackPane> mines = new ArrayList<StackPane>();
    private List<StackPane> falseFlags = new ArrayList<StackPane>();
    private int clickCount;
    private int minesLeft = 0;

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
            game = new Minesweeper(9, 9, 10);
        } else if (mode.equals("Medium")) {
            game = new Minesweeper(16, 16, 40);
        } else {
            game = new Minesweeper(16, 30, 99);
        }
    }

    private void changeMode(String mode) throws IOException {
        Set<String> modes = new HashSet<>(Arrays.asList("Easy", "Medium", "Hard"));
        if (modes.contains(mode)) {
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
        setGame(mode);
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
    public void newEasyGameClicked() throws IOException {
        makeNewGame(easyGrid);
    }

    @FXML
    public void newMediumGameClicked() throws IOException {
        makeNewGame(mediumGrid);
    }

    @FXML
    public void newHardGameClicked() throws IOException {
        makeNewGame(hardGrid);
    }

    public void makeNewGame(GridPane grid) {
        mines = new ArrayList<StackPane>();
        falseFlags = new ArrayList<StackPane>();
        clickCount = 0;
        int gameID = grid.getColumnCount();
        grid.getChildren().clear();
        if (gameID == 9) {
            makeGridButtons(grid, 9, 9);
            setGame("Easy");
            minesLeft = 10;
        } else if (gameID == 16) {
            makeGridButtons(grid, 16, 16);
            setGame("Medium");
            minesLeft = 40;
        } else if (gameID == 30) {
            makeGridButtons(grid, 16, 30);
            setGame("Hard");
            minesLeft = 99;
        }
        updateMinesLeft(false);
        timer.reset();
        timer.stop();

    }

    public void onMouseClick(MouseEvent event, GridPane grid, Node node) {
        if (game.isGameLost())
            return;
        Integer rowIndex = GridPane.getRowIndex(node);
        Integer colIndex = GridPane.getColumnIndex(node);
        GridImpl gameGrid = game.getPlayingGrid();
        StackPane stackPane = (StackPane) node;
        if (event.getButton() == MouseButton.SECONDARY) {
            if (clickCount == 0 || gameGrid.getElement(rowIndex, colIndex).isOpen()) {
                return;
            }
            if (!gameGrid.getElement(rowIndex, colIndex).isFlagged()) {
                ImageView imageView = new ImageView(flagImage);
                imageView.setOnMouseClicked(click -> {
                    onMouseClick(click, grid, stackPane);
                });
                stackPane.getChildren().add(imageView);
                gameGrid.getElement(rowIndex, colIndex).setFlagged(true);
                if (!mines.contains(stackPane))
                    falseFlags.add(stackPane);
                updateMinesLeft(false);
            } else {
                if (falseFlags.contains(stackPane))
                    falseFlags.remove(stackPane);
                stackPane.getChildren().remove(2);
                gameGrid.getElement(rowIndex, colIndex).setFlagged(false);
                updateMinesLeft(true);
            }
        } else if (event.getButton() == MouseButton.PRIMARY) {
            if (clickCount == 0) {
                game.onFirstClick(rowIndex, colIndex);
                clickCount++;
                makeGame(grid);
                timer.start();
                minesLeft = mines.size();
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
        for (StackPane mine : mines) {
            if (mine.getChildren().size() != 3)
                mine.getChildren().remove(1);
        }
        for (StackPane falseFlag : falseFlags) {
            falseFlag.getChildren().remove(0, 3);
            ImageView imageView = new ImageView(bombImage);
            ImageView imageView2 = new ImageView(xImage);
            falseFlag.getChildren().add(imageView);
            falseFlag.getChildren().add(imageView2);
        }
    }

    public void checkWin(GridPane gridPane) {
        if (game.getUnopenedCells().isEmpty()) {
            timer.stop();
            recordTime = timer.getTime();
            if (mines.size() == 10) {
                setRecordTime(recordTime, EASY_LEADERBOARD);
            } else if (mines.size() == 40) {
                setRecordTime(recordTime, MEDIUM_LEADERBOARD);
            } else {
                setRecordTime(recordTime, HARD_LEADERBOARD);
            }
        }
    }

    private void setRecordTime(int recordTime, String path) {
        easyGrid.setVisible(false);
        minesLeftBox.setVisible(false);
        mediumGrid.setVisible(false);
        hardGrid.setVisible(false);
        navBarBox.setVisible(false);
        timerBox.setVisible(false);
        winningScoreBox.setVisible(true);
        winningScoreBox.setDisable(false);

        spillerNavnTekst.getChildren().add(new Label("Skriv inn brukernavnet ditt under: "));
        spillerTid.getChildren().add(new Label("Tid: " + recordTime + " sekunder"));

        try {
            leaderBoardSetUp(path);
        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(AlertType.WARNING, "Kunne ikke lese spillstatus");
            alert.show();
        }

    }

    @FXML
    public void easyModeAddLeaderBoardScore() throws IOException {
        addLeaderBoardScore(EASY_LEADERBOARD);
    }

    @FXML
    public void mediumModeAddLeaderBoardScore() throws IOException {
        addLeaderBoardScore(MEDIUM_LEADERBOARD);
    }

    @FXML
    public void hardModeAddLeaderBoardScore() throws IOException {
        addLeaderBoardScore(HARD_LEADERBOARD);
    }

    private void addLeaderBoardScore(String path) throws IOException {
        String brukerNavn = new String();
        brukerNavn = spillerNavn.getText();
        List<String> scores = FileHelper.readLines(path, false);
        List<Integer> timeScores = new ArrayList<>();
        for (int j = 0; j < scores.size(); j++) {
            String[] bruker = scores.get(j).split(",");
            timeScores.add(Integer.valueOf(bruker[1]));
        }

        for (int i = 0; i < timeScores.size(); i++) {
            leaderBoard.addResult(timeScores.get(i));
        }
        leaderBoard.addResult(recordTime);

        if (leaderBoard.getIndex() == -1) {
            Filbehanding.fileWriter(path, scores);
        } else {
            scores.add(leaderBoard.getIndex(), brukerNavn + "," + recordTime);
            if (scores.size() > 16) {
                scores.remove(16);
            }

            Filbehanding.fileWriter(path, scores);
        }

        backToMenuClicked();
    }

    public void leaderBoardSetUp(String path) throws IOException {
        ListView leaderBoardBox = new ListView<>();
        ArrayList<String> scores = new ArrayList<>();
        Filbehanding.fileReader(path);
        scores = Filbehanding.getTempScores();
        leaderBoardBox.getItems().setAll(scores);
        winningScoreBox.getChildren().add(leaderBoardBox);
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
                mines.add(stackPane);
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

    public void updateMinesLeft(boolean increase) {
        if (clickCount == 0) {

        } else if (increase)
            minesLeft++;
        else
            minesLeft--;
        minesLeftText.setText(String.format("%d", minesLeft));
    }
}
