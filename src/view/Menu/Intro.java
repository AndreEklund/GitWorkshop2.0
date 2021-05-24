package view.Menu;

import control.MainProgram;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import view.AudioPlayer;

/**
 * Author: Viktor Näslund
 */

public class Intro extends Pane {

    private MainProgram mainProgram;
    private Image spaceImage;
    private Image mazeGen;
    private Image mazeGenGlow;
    private Image pressMouse;
    private AudioPlayer audioPlayer;

    public Intro(MainProgram mainProgram, AudioPlayer audioPlayer){
        this.mainProgram = mainProgram;
        this.audioPlayer = audioPlayer;
        setBackground();
        addListener();
        setupImages();
        introAnimation();

    }


    public void setBackground(){
        BackgroundImage backgroundImage= new BackgroundImage(new Image("file:files/intropics/1.png",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));
    }

    public void setupImages(){
        spaceImage = new Image("file:files/intropics/2.png", 800,600,false,false);
        mazeGen = new Image("file:files/intropics/3.png", 800,600,false,false);
        mazeGenGlow = new Image("file:files/intropics/4.png", 800,600,false,false);
        pressMouse = new Image("file:files/intropics/5.png", 800,600,false,false);
    }


    public void addListener(){
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                audioPlayer.stopMusic();
                audioPlayer.playButtonSound();
                mainProgram.changeToMenu();
            }
        });
    }


    public void introAnimation() {
        ImageView introView = new ImageView(spaceImage);
        introView.setStyle("fx-background-color: transparent;");
        FadeTransition ft = new FadeTransition(Duration.millis(3000), introView);

        getChildren().add(introView);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();


        ImageView mazeGenView = new ImageView(mazeGen);
        mazeGenView.setStyle("fx-background-color: transparent;");
        mazeGenView.toFront();
        FadeTransition ft2 = new FadeTransition(Duration.millis(2000), mazeGenView);
        mazeGenView.setOpacity(0);
        getChildren().add(mazeGenView);
        ft2.setDelay(Duration.millis(2000));

        ft2.setFromValue(0);
        ft2.setToValue(1);
        ft2.play();

        ImageView mazeGenGlowView = new ImageView(mazeGenGlow);
        mazeGenGlowView.setStyle("fx-background-color: transparent;");
        mazeGenGlowView.toFront();
        FadeTransition ft3 = new FadeTransition(Duration.millis(1500), mazeGenGlowView);
        mazeGenGlowView.setOpacity(0);
        getChildren().add(mazeGenGlowView);
        ft3.setDelay(Duration.millis(3500));

        ft3.setFromValue(0);
        ft3.setToValue(1);
        ft3.play();

        ImageView pressMouseView = new ImageView(pressMouse);
        pressMouseView.setStyle("fx-background-color: transparent;");
        pressMouseView.toFront();
        FadeTransition ft4 = new FadeTransition(Duration.millis(1500), pressMouseView);
        ft4.setCycleCount(Transition.INDEFINITE);
        pressMouseView.setOpacity(0);
        getChildren().add(pressMouseView);
        ft4.setDelay(Duration.millis(5500));

        ft4.setFromValue(0);
        ft4.setToValue(1);
        ft4.play();


    }

}
