package view.Menu;

import control.MainProgram;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.DigitalClock;

import java.io.FileNotFoundException;


public class RightPanel extends GridPane {

    /**
     * Author Filip Örnling
     */

    private ImageView currentLevelView;
    private Label level;
    private Image imageMenu;
    private ImageView menuView;
    private Label heartLabel;
    private Image levelNumber;
    private MainProgram mainProgram;
    private DigitalClock clock = new DigitalClock();
    private boolean running = false;
    private String gameMode;
    private int seconds;
    private Image heart;
    private ImageView currentHeartView;

    private Image pickaxe;
    private ImageView pickaxeView;
    private Label pickaxeLabel;

    private static Integer STARTTIME = 15;
    private Timeline timeline = new Timeline();
    private Timeline timeline2;
    private Label timerLabel = new Label();
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    private IntegerProperty stackedSeconds = new SimpleIntegerProperty();
    private Font font = Font.loadFont("file:files/fonts/PressStart2P.ttf", 50);




    public RightPanel(MainProgram mainProgram, String gameMode) throws FileNotFoundException {
        this.mainProgram = mainProgram;
        this.gameMode = gameMode;

        levelNumber = new Image("file:files/levelcounter/"+ gameMode +".png", 90, 30, false, false);
        currentLevelView = new ImageView(levelNumber);
        imageMenu = new Image("file:files/texts/Menu.png", 90, 30, false, false);
        menuView = new ImageView(imageMenu);

        heart = new Image("file:files/hearts/3heart.png", 90, 30, false, false);
        currentHeartView = new ImageView(heart);

        pickaxe = new Image("file:files/items/pickaxe.png", 30, 30, false, false);

        pickaxeView = new ImageView(pickaxe);
        pickaxeLabel = new Label();
        pickaxeLabel.setTranslateX(8);


        level = new Label();
        level.setTranslateX(8);
        level.setGraphic(currentLevelView);

        heartLabel = new Label();
        heartLabel.setTranslateX(8);
        heartLabel.setGraphic(currentHeartView);

        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setFont(font);
        timerLabel.setTranslateY(200);
        timerLabel.setTranslateX(4);

        add(timerLabel, 0, 3);

        add(level,0,1);

        add(heartLabel,0,2);

        add(pickaxeLabel, 0, 3);

        //add(clock,0,2);
        //runClock();

        menuView.setOnMouseClicked(e -> MainMenuClicked(e));
        add(menuView,0,0);
    }

    public void changeHeartCounter(String number){
        heart = new Image("file:files/hearts/" + number + "heart.png", 90, 30, false, false);
        currentHeartView = new ImageView(heart);
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
        currentLevelView = new ImageView(levelNumber);
        level.setGraphic(currentLevelView);
    }



    private void MainMenuClicked(MouseEvent e){
        System.out.println("Main Menu clicked");
        mainProgram.changeToMenu();

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