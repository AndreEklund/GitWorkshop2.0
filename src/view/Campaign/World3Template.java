package view.Campaign;

import control.MainProgram;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import view.AudioPlayer;
import view.Menu.RightPanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class World3Template extends World1Template {


    /**
     * Author André Eklund
     */

    private Image ghost;

    private int squareSize;
    private ImageView imageView = new ImageView();
    private PathTransition animation;
    private PathTransition animation2;
    private PathTransition animation3;
    private PathTransition animation4;
    private PathTransition animation5;
    private PathTransition animation6;
    private Thread platfrom;
    private int currentLevel;



    public World3Template(int[][] level, int currentLevel, int heartCrystals, MainProgram mainProgram, RightPanel rightPanel, int worldImage, AudioPlayer audioPlayer) throws FileNotFoundException {
        super(level, currentLevel, heartCrystals, mainProgram, rightPanel, worldImage, audioPlayer);
        squareSize = 600/(level.length+2);
        this.currentLevel = currentLevel;
        rightPanel.changeHeartCounter(String.valueOf(heartCrystals));
        setupGhost();

    }

    //Konstruktorn ska kunna ta emot int-arrayer och representera dem i GUIt
    public void setupGhost() throws FileNotFoundException {
        ghost = new Image("file:files/ghost.png", squareSize, squareSize, false, false);

        imageView.setImage(ghost);

        imageView.setX(1);
        imageView.setY(1);
        imageView.setFitHeight(squareSize);
        imageView.setFitWidth(squareSize);

        imageView.setOnMouseEntered(e -> enteredWall(e));



        initialize();
    }
    public void initialize() {
        if (currentLevel==1){
            ImageView ghost3V = new ImageView();
            ImageView ghost1V = new ImageView();
            ghost3V.setImage(ghost);
            ghost1V.setImage(ghost);
            add(ghost3V,5,5);
            add(ghost1V,14,5);

            Rectangle rectangle = new Rectangle(125,251);
            rectangle.setY(65);
            rectangle.setX(-147);


            Rectangle rectangle2 = new Rectangle(84,123);
            rectangle2.setY(65);
            rectangle2.setX(-147);



            animation = new PathTransition();
            animation.setNode(ghost3V);
            animation.setDuration(Duration.seconds(4));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(rectangle);
            animation.play();

            animation2 = new PathTransition();
            animation2.setNode(ghost1V);
            animation2.setDuration(Duration.seconds(4));
            animation2.setCycleCount(Animation.INDEFINITE);
            animation2.setPath(rectangle2);
            animation2.play();


            ghost1V.setOnMouseEntered(e -> enteredGhost(e));

            ghost3V.setOnMouseEntered(e -> enteredGhost(e));


        }

        else if (currentLevel ==2){
            ImageView ghost1V = new ImageView();
            ImageView ghost2V = new ImageView();

            ghost1V.setImage(ghost);
            ghost2V.setImage(ghost);

            //add(ghost1V,5,8);
            add(ghost2V,9,3);

            Rectangle rectangle = new Rectangle(125,120);
            rectangle.setY(65);
            rectangle.setX(-147);


            Rectangle rectangle1 = new Rectangle(210,125);
            rectangle1.setY(190);
            rectangle1.setX(-231);

            animation = new PathTransition();
            animation.setNode(ghost1V);
            animation.setDuration(Duration.seconds(2));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(rectangle);
            animation.play();

            animation2 = new PathTransition();
            animation2.setNode(ghost2V);
            animation2.setDuration(Duration.seconds(2));
            animation2.setCycleCount(Animation.INDEFINITE);
            animation2.setPath(rectangle1);
            animation2.play();


            ghost1V.setOnMouseEntered(e -> enteredGhost(e));
            ghost2V.setOnMouseEntered(e -> enteredGhost(e));

        }
        else if (currentLevel ==3){
          ImageView ghost4V = new ImageView();
          ImageView ghost2V = new ImageView();
          ImageView ghost1V = new ImageView();
          ImageView ghost3V = new ImageView();
          ImageView ghost5V = new ImageView();


          ghost1V.setImage(ghost);
          ghost2V.setImage(ghost);
          ghost3V.setImage(ghost);
          ghost4V.setImage(ghost);
          ghost5V.setImage(ghost);


          add(ghost2V,15,4);
          add(ghost4V,10,4);

          add(ghost1V,10,1);
          add(ghost3V,15,1);
          add(ghost5V,12,1);

            Rectangle rectangle = new Rectangle(125,83);
            rectangle.setY(190);
            rectangle.setX(-230);


            Rectangle rectangle1 = new Rectangle(125,83);
            rectangle1.setY(190);
            rectangle1.setX(-230);

            Rectangle rectangle2 = new Rectangle(0,83);
            rectangle2.setY(105);
            rectangle2.setX(-232);


            Rectangle rectangle3 = new Rectangle(0,83);
            rectangle3.setY(105);
            rectangle3.setX(-232);

            Rectangle rectangle4 = new Rectangle(0,83);
            rectangle4.setY(105);
            rectangle4.setX(-232);

            //Övre spöken

            animation3 = new PathTransition();
            animation3.setNode(ghost1V);
            animation3.setDuration(Duration.seconds(2));
            animation3.setCycleCount(Animation.INDEFINITE);
            animation3.setPath(rectangle2);
            animation3.setAutoReverse(true);
            animation3.play();

            animation4 = new PathTransition();
            animation4.setNode(ghost3V);
            animation4.setDuration(Duration.seconds(2));
            animation4.setCycleCount(Animation.INDEFINITE);
            animation4.setPath(rectangle3);
            animation4.setAutoReverse(true);
            animation4.play();


            animation5 = new PathTransition();
            animation5.setNode(ghost5V);
            animation5.setDuration(Duration.seconds(2));
            animation5.setCycleCount(Animation.INDEFINITE);
            animation5.setPath(rectangle4);
            animation5.setAutoReverse(true);
            animation5.play();

            //Undre spöken

            animation = new PathTransition();
            animation.setNode(ghost4V);
            animation.setDuration(Duration.seconds(2));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(rectangle);
            animation.setAutoReverse(true);
            animation.play();

            animation2 = new PathTransition();
            animation2.setNode(ghost2V);
            animation2.setDuration(Duration.seconds(3));
            animation2.setCycleCount(Animation.INDEFINITE);
            animation2.setPath(rectangle1);
            animation2.setAutoReverse(true);
            animation2.play();


            ghost1V.setOnMouseEntered(e -> enteredGhost(e));
            ghost2V.setOnMouseEntered(e -> enteredGhost(e));
            ghost3V.setOnMouseEntered(e -> enteredGhost(e));
            ghost4V.setOnMouseEntered(e -> enteredGhost(e));
            ghost5V.setOnMouseEntered(e -> enteredGhost(e));


        }
        else if(currentLevel==4){
            ImageView ghost1V = new ImageView();


        }
/*
        ghost3V.setImage(ghost);
        ghost4V.setImage(ghost);
        ghost5V.setImage(ghost);
        ghost6V.setImage(ghost);


        add(ghost3V,8,0);
        add(ghost4V, 1, 0);
        add(ghost5V, 2, 0);
        add(ghost6V, 3, 0);

*/
    }


}//Class
