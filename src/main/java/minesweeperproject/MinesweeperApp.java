package minesweeperproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MinesweeperApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(
                new Scene(
                        FXMLLoader.load(MinesweeperApp.class.getResource("Minesweeper.fxml"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        MinesweeperApp.launch(args);
    }

}
