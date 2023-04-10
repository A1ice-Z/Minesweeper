package minesweeperproject.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class MinesweeperTimer extends HBox {
    private int time = 0;
    private Label minesweeperTimeLabel = new Label(String.valueOf(time));

    private Timeline minesweeperTimeline;

    public MinesweeperTimer() {
        minesweeperTimeLabel.setFont(new Font("Arial", 15));
        minesweeperTimeLabel.setAlignment(Pos.CENTER);
        minesweeperTimeLabel.setMinWidth(25);
        this.getChildren().add(minesweeperTimeLabel);
        minesweeperTimeline = new Timeline(new KeyFrame(Duration.seconds(1), ae -> {
            time++;
            minesweeperTimeLabel.setText(String.valueOf(time));
        }));
        minesweeperTimeline.setCycleCount(Animation.INDEFINITE);
    }

    public void start() {
        minesweeperTimeLabel.setText(String.valueOf(time));
        minesweeperTimeline.play();
    }

    public void reset() {
        time = 0;
        minesweeperTimeLabel.setText(String.valueOf(time));
    }

    public void stop() {
        minesweeperTimeline.stop();
    }

    public int getTime() {
        return time;
    }
}
