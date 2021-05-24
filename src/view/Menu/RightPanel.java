package view.Menu;

import control.MainProgram;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.DigitalClock;
import view.AudioPlayer;

import java.io.FileNotFoundException;


public class RightPanel extends GridPane {

    /**
     * Author Filip Örnling
     */

    private MainProgram mainProgram;
    private DigitalClock clock = new DigitalClock();
    private boolean running = false;
    private String gameMode;
    private int seconds;

    private Image imageMenu;
    private ImageView menuView;

    private Image levelNumber;
    private ImageView currentLevelView;
    private Label levelLabel;

    private Image heart;
    private ImageView currentHeartView;
    private Label heartLabel;

    private Image pickaxe;
    private ImageView pickaxeView;
    private Label pickaxeLabel;

    private Image soundImage;
    private ImageView soundView;
    private Label soundLabel;
    private boolean soundOn;

    private Image musicImage;
    private ImageView musicView;
    private Label musicLabel;
    private boolean musicOn;

    private static Integer STARTTIME = 15;
    private Timeline timeline = new Timeline();
    private Timeline timeline2;
    private Label timerLabel = new Label();
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    private IntegerProperty stackedSeconds = new SimpleIntegerProperty();
    private Font font = Font.loadFont("file:files/fonts/PressStart2P.ttf", 50);

    private AudioPlayer audioPlayer;


    public RightPanel(MainProgram mainProgram, String gameMode, AudioPlayer audioPlayer) throws FileNotFoundException {
        this.mainProgram = mainProgram;
        this.gameMode = gameMode;
        this.audioPlayer = audioPlayer;

        soundOn = true;
        musicOn = true;

        imageMenu = new Image("file:files/texts/Menu.png", 90, 30, false, false);
        menuView = new ImageView(imageMenu);

        pickaxe = new Image("file:files/items/pickaxe.png", 30, 30, false, false);
        pickaxeView = new ImageView(pickaxe);
        pickaxeLabel = new Label();
        pickaxeLabel.setTranslateX(8);

        levelNumber = new Image("file:files/levelcounter/"+ gameMode +".png", 90, 30, false, false);
        currentLevelView = new ImageView(levelNumber);
        levelLabel = new Label();
        levelLabel.setTranslateX(8);
        levelLabel.setGraphic(currentLevelView);

        soundImage = new Image("file:files/soundbuttons/soundon.png", 30,30,false,false);
        soundView = new ImageView(soundImage);
        soundLabel = new Label();
        soundLabel.setTranslateX(38);
        soundLabel.setTranslateY(420);
        soundLabel.setGraphic(soundView);

        musicImage = new Image("file:files/soundbuttons/musicon.png", 30,30,false,false);
        musicView = new ImageView(musicImage);
        musicLabel = new Label();
        musicLabel.setTranslateX(68);
        musicLabel.setTranslateY(420);
        musicLabel.setGraphic(musicView);

        //Hearts only in Campaign
        if(gameMode!="Random"){
            heart = new Image("file:files/hearts/3heart.png", 90, 30, false, false);
            currentHeartView = new ImageView(heart);
            heartLabel = new Label();
            heartLabel.setTranslateX(8);
            heartLabel.setGraphic(currentHeartView);
            add(heartLabel,0,2);
        }

        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setFont(font);
        timerLabel.setTranslateY(200);
        timerLabel.setTranslateX(4);

        add(timerLabel, 0, 3);

        add(levelLabel,0,1);

        add(pickaxeLabel, 0, 3);

        soundLabel.setOnMouseClicked(e -> soundLabelClicked());
        musicLabel.setOnMouseClicked(e -> musicLabelClicked());

        add(soundLabel,0,4);
        add(musicLabel,0,4);


        //add(clock,0,2);
        //runClock();

        menuView.setOnMouseClicked(e -> MainMenuClicked(e));
        add(menuView,0,0);
    }

    public void soundLabelClicked(){
        if(soundOn){
            soundImage = new Image("file:files/soundbuttons/soundoff.png", 30,30,false,false);
            audioPlayer.muteSound(true);
            soundOn = false;
        } else{
            soundImage = new Image("file:files/soundbuttons/soundon.png", 30,30,false,false);
            audioPlayer.muteSound(false);
            soundOn = true;
        }
        soundView.setImage(soundImage);
        soundLabel.setGraphic(soundView);
    }

    public void musicLabelClicked(){
        if(musicOn){
            musicImage = new Image("file:files/soundbuttons/musicoff.png",30,30,false,false);
            audioPlayer.muteMusic(true);
            musicOn = false;
        } else{
            musicImage = new Image("file:files/soundbuttons/musicon.png",30,30,false,false);
            audioPlayer.muteMusic(false);
            musicOn = true;
        }
        musicView.setImage(musicImage);
        musicLabel.setGraphic(musicView);
    }

    public void changeHeartCounter(String number){
        heart = new Image("file:files/hearts/" + number + "heart.png", 90, 30, false, false);
        currentHeartView.setImage(heart);
        heartLabel.setGraphic(currentHeartView);

    }

    public void addPickaxe(){
        pickaxeLabel.setGraphic(pickaxeView);
    }
    public void removePickaxe(){
        pickaxeLabel.setGraphic(null);
    }



    public void changeLevelCounter(String number){
        levelNumber = new Image("file:files/levelcounter/" + number + ".png", 90, 30, false, false);
        currentLevelView.setImage(levelNumber);
        levelLabel.setGraphic(currentLevelView);
    }



    private void MainMenuClicked(MouseEvent e){
        System.out.println("Main Menu clicked");
        mainProgram.changeToMenu();
        audioPlayer.playButtonSound();

    }

    public void runClock() {
        System.out.println("runClock");
        timeSeconds.set(STARTTIME);

        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME+1),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
    }

    public void pauseClock(){
        seconds = timeSeconds.getValue().intValue();
        System.out.println(seconds);
        timeline.stop();

        timeSeconds.set(STARTTIME + seconds);
        timeline = null;
      //  timeline2 = null;
        /*timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME+1),
                        new KeyValue(timeSeconds, 0)));*/
    }

    public void resumeClock(){
        timeSeconds.set(STARTTIME + seconds);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME + seconds),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
    }

    public void setSTARTTIME(Integer STARTTIME) {
        RightPanel.STARTTIME = STARTTIME;
    }
}