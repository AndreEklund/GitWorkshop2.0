package view;

import control.MainProgram;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class WorldIntroAnimation extends Pane {

    private String currentWorld;
    private Image worldIntro;

    public WorldIntroAnimation(){}
    public WorldIntroAnimation(String world) {
        this.currentWorld = world;
        setupImages();
        introAnimation();
    }
    public void setupImages() {
        worldIntro = new Image("file:files/worlds/World" + currentWorld + ".png", 600, 600, false, false);
    }
    public void introAnimation() {
        ImageView introView = new ImageView(worldIntro);
        introView.setStyle("fx-background-color: transparent;");
        FadeTransition ft = new FadeTransition(Duration.millis(4000), introView);
        getChildren().add(introView);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();

    }
}
