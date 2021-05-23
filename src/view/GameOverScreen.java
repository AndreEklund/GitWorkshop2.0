package view;

import control.MainProgram;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameOverScreen extends Pane {

    private Image gameOver;

    public GameOverScreen(MainProgram mainProgram) {
        setOnMouseClicked(e -> mainProgram.changeToMenu());
        setupImages();
        gameOverAnimation();
    }
    public void setupImages() {
        gameOver = new Image("file:files/texts/Gameover.png", 600, 600, false, false);
    }
    public void gameOverAnimation() {
        ImageView introView = new ImageView(gameOver);
        introView.setStyle("fx-background-color: transparent;");
        FadeTransition ft = new FadeTransition(Duration.millis(4000), introView);
        getChildren().add(introView);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

    }
}
