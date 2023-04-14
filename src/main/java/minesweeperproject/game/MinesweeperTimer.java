package minesweeperproject.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Class that makes the timer for each minesweeper game
 */

public class MinesweeperTimer extends HBox {
    private int time = 0;
    private Label minesweeperTimeLabel = new Label(String.valueOf(time));

    private Timeline minesweeperTimeline;

    /**
     * Sets what the design and position of the timer text in the
     * minesweeperTimeLabel is supposed to look like, and also ads it to an HBox.
     * 
     * Makes the minesweeperTimeline and spesefies how fast the timer is supposed to
     * count, and how long the timer can last
     */
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

    /**
     * Starts the timer, and shows the time as a String format text in the
     * minesweeperTimeLabel
     */
    public void start() {
        minesweeperTimeLabel.setText(String.valueOf(time));
        minesweeperTimeline.play();
    }

    /**
     * Resets the timer, and shows the time as a String format text in the
     * minesweeperTimeLabel
     */
    public void reset() {
        time = 0;
        minesweeperTimeLabel.setText(String.valueOf(time));
    }

    /**
     * Stops the timer
     */
    public void stop() {
        minesweeperTimeline.stop();
    }

    /**
     * Returns the minesweeper timeline
     * 
     * @return The minesweeper timeline
     */
    public int getTime() {
        return time;
    }

    /**
     * Returns the minesweeper timeline
     * 
     * @return The minesweeper timeline
     */
    public Timeline getMinesweeperTimeline() {
        return minesweeperTimeline;
    }
}
