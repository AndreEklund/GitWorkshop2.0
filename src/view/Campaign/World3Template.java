package view.Campaign;

import control.MainProgram;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
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
    private int currentLevel;

    public World3Template(int[][] level, int currentLevel, int heartCrystals, MainProgram mainProgram, RightPanel rightPanel, int worldImage, AudioPlayer audioPlayer) throws FileNotFoundException {
        super(level, currentLevel, heartCrystals, mainProgram, rightPanel, worldImage, audioPlayer);
        squareSize = 600/(level.length+2);
        this.currentLevel = currentLevel;
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
            ghost3V.setImage(ghost);

            add(ghost3V,5,5);

            Rectangle rectangle = new Rectangle(145,400);
            rectangle.setY(50);
            rectangle.setX(-150);
            rectangle.setArcWidth(50);
            rectangle.setArcHeight(50);

            animation = new PathTransition();
            animation.setNode(ghost3V);
            animation.setDuration(Duration.seconds(4));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(rectangle);
            animation.play();

        }

        else if (currentLevel ==2){
            ImageView ghost1V = new ImageView();
            ImageView ghost2V = new ImageView();

            ghost1V.setImage(ghost);
            ghost2V.setImage(ghost);

            add(ghost1V,10,0);
            add(ghost2V,9,0);

            Rectangle rectangle = new Rectangle(145,400);
            rectangle.setY(50);
            rectangle.setX(-150);
            rectangle.setArcWidth(50);
            rectangle.setArcHeight(50);

            Rectangle rectangle1 = new Rectangle(400,145);
            rectangle1.setY(200);
            rectangle1.setX(-250);
            rectangle1.setArcWidth(50);
            rectangle1.setArcHeight(50);

            animation = new PathTransition();
            animation.setNode(ghost1V);
            animation.setDuration(Duration.seconds(4));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(rectangle);
            animation.play();

            animation2 = new PathTransition();
            animation2.setNode(ghost2V);
            animation2.setDuration(Duration.seconds(5));
            animation2.setCycleCount(Animation.INDEFINITE);
            animation2.setPath(rectangle1);
            animation2.play();
        }
        else if (currentLevel ==3){
          ImageView ghost4V = new ImageView();
          ImageView ghost2V = new ImageView();

          ghost2V.setImage(ghost);
          ghost4V.setImage(ghost);

          add(ghost2V,5,5);
          add(ghost4V,4,3);
            Rectangle rectangle = new Rectangle(100,350);
            rectangle.setY(50);
            rectangle.setX(-150);
            rectangle.setArcWidth(50);
            rectangle.setArcHeight(50);

            Rectangle rectangle1 = new Rectangle(100,200);
            rectangle1.setY(200);
            rectangle1.setX(-250);
            rectangle1.setArcWidth(50);
            rectangle1.setArcHeight(50);

            animation = new PathTransition();
            animation.setNode(ghost4V);
            animation.setDuration(Duration.seconds(4));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(rectangle);
            animation.play();

            animation2 = new PathTransition();
            animation2.setNode(ghost2V);
            animation2.setDuration(Duration.seconds(5));
            animation2.setCycleCount(Animation.INDEFINITE);
            animation2.setPath(rectangle1);
            animation2.play();

        }
        else if(currentLevel==4){
           ImageView ghost5V = new ImageView();
           ImageView ghost6V = new ImageView();
           ImageView ghost7V = new ImageView();

           ghost5V.setImage(ghost);
           ghost6V.setImage(ghost);
           ghost7V.setImage(ghost);

           add(ghost5V,4,5);
           add(ghost6V,5,3);
           add(ghost7V,4,3);
            Rectangle rectangle = new Rectangle(100,350);
            rectangle.setY(50);
            rectangle.setX(-150);
            rectangle.setArcWidth(50);
            rectangle.setArcHeight(50);

            Rectangle rectangle1 = new Rectangle(100,200);
            rectangle1.setY(200);
            rectangle1.setX(-250);
            rectangle1.setArcWidth(50);
            rectangle1.setArcHeight(50);

            Rectangle rectangle3 = new Rectangle(120,275);
            rectangle1.setY(250);
            rectangle1.setX(-300);
            rectangle1.setArcWidth(80);
            rectangle1.setArcHeight(50);

            animation = new PathTransition();
            animation.setNode(ghost5V);
            animation.setDuration(Duration.seconds(4));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(rectangle);
            animation.play();

            animation2 = new PathTransition();
            animation2.setNode(ghost6V);
            animation2.setDuration(Duration.seconds(5));
            animation2.setCycleCount(Animation.INDEFINITE);
            animation2.setPath(rectangle1);
            animation2.play();

            animation3 = new PathTransition();
            animation3.setNode(ghost7V);
            animation3.setDuration(Duration.seconds(5));
            animation3.setCycleCount(Animation.INDEFINITE);
            animation3.setPath(rectangle3);
            animation3.play();


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
