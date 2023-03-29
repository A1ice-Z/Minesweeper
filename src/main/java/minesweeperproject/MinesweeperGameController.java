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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MinesweeperGameController {
    @FXML
    private Button backToMenuButton;

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
}
