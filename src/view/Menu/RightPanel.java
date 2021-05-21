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
import model.MapCreator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;


public class RightPanel extends GridPane {

    /**
     * Author Filip Örnling
     */

    private ImageView currentLevelView;
    private Label level;
    private Image imageMenu;
    private Image levelNumber;
    private MainProgram mainProgram;
    private DigitalClock clock = new DigitalClock();
    private boolean running = false;
    private String gameMode;
    private int seconds;

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

        level = new Label();
        level.setTranslateX(8);
        level.setGraphic(currentLevelView);

        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setFont(font);
        timerLabel.setTranslateY(200);
        timerLabel.setTranslateX(4);

        add(timerLabel, 0, 3);

        BackgroundImage menuBGImage = new BackgroundImage(imageMenu,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(imageMenu.getWidth(), imageMenu.getHeight(),false,false,false,false));

        add(level,0,1);

        //add(clock,0,2);
        //runClock();


        Button btnMenu = new Button();
        ImageView imageViewMenu = new ImageView(imageMenu);
        btnMenu.setGraphic(imageViewMenu);

        Background menuBackground = new Background(menuBGImage);
        
        btnMenu.setBackground(menuBackground);
        btnMenu.setOnMouseClicked(e -> Mainmenyclicked(e));
        add(btnMenu,0,0);
    }



    public void changeLevelCounter(String number){
        levelNumber = new Image("file:files/levelcounter/" + number + ".png", 90, 30, false, false);
        currentLevelView = new ImageView(levelNumber);
        level.setGraphic(currentLevelView);
    }



    private void Mainmenyclicked(MouseEvent e){
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