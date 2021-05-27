package view.Campaign;

import control.MainProgram;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import view.AudioPlayer;
import view.Menu.RightPanel;

import java.io.FileNotFoundException;

public class World5Template extends World1Template {

    private Image ghost;

    private int squareSize;
    private PathTransition animation;
    private PathTransition animation2;
    private PathTransition animation3;
    private PathTransition animation4;
    private PathTransition animation5;
    private int currentLevel;


    public World5Template(int[][] level, int currentLevel, int heartCrystals, MainProgram mainProgram, RightPanel rightPanel, int worldImage, AudioPlayer audioPlayer) throws FileNotFoundException {
        super(level, currentLevel, heartCrystals, mainProgram, rightPanel, worldImage, audioPlayer);
        squareSize = 600/(level.length+2);
        this.currentLevel = currentLevel;
        rightPanel.changeHeartCounter(String.valueOf(heartCrystals));
        initialize();
    }
    public void initialize() {

        ghost = new Image("file:files/ghost.png", squareSize, squareSize, false, false);

        if (currentLevel==1){
            ImageView ghost3V = new ImageView();
            ImageView ghost1V = new ImageView();
            ghost3V.setImage(ghost);
            ghost1V.setImage(ghost);
            add(ghost3V,10,10);
            add(ghost1V,20,5);

            Rectangle rectangle = new Rectangle(300,150);
            rectangle.setY(65);
            rectangle.setX(-150);


            Rectangle rectangle2 = new Rectangle(97,280);
            rectangle2.setY(65);
            rectangle2.setX(-150);



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

        else if (currentLevel == 2){
            ImageView ghost1V = new ImageView();
            ImageView ghost2V = new ImageView();

            ghost1V.setImage(ghost);
            ghost2V.setImage(ghost);

            add(ghost1V,6,3);
            add(ghost2V,20,5);

            Rectangle rectangle = new Rectangle(270,100);
            rectangle.setY(50);
            rectangle.setX(-150);


            Rectangle rectangle1 = new Rectangle(100,100);
            rectangle1.setY(80);
            rectangle1.setX(-150);

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
        else if (currentLevel == 3 ){
            ImageView ghost1V = new ImageView();
            ImageView ghost2V = new ImageView();

            ghost1V.setImage(ghost);
            ghost2V.setImage(ghost);

            add(ghost1V,13,3);
            add(ghost2V,12,10);

            Rectangle rectangle = new Rectangle(132,100);
            rectangle.setY(50);
            rectangle.setX(-150);


            Rectangle rectangle1 = new Rectangle(100,100);
            rectangle1.setY(80);
            rectangle1.setX(-150);

            animation = new PathTransition();
            animation.setNode(ghost1V);
            animation.setDuration(Duration.seconds(1.5));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(rectangle);
            animation.play();

            animation2 = new PathTransition();
            animation2.setNode(ghost2V);
            animation2.setDuration(Duration.seconds(1.5));
            animation2.setCycleCount(Animation.INDEFINITE);
            animation2.setPath(rectangle1);
            animation2.play();


            ghost1V.setOnMouseEntered(e -> enteredGhost(e));
            ghost2V.setOnMouseEntered(e -> enteredGhost(e));


        }
        else if(currentLevel==4) {
            ImageView ghost1V = new ImageView();
            ImageView ghost2V = new ImageView();
            ImageView ghost3V = new ImageView();

            ghost3V.setImage(ghost);
            ghost2V.setImage(ghost);
            ghost1V.setImage(ghost);

            add(ghost1V, 12, 8);
            add(ghost2V, 14, 0);
            //add(ghost3V, 8, 12);


            Rectangle rectangle = new Rectangle(132, 200);
            rectangle.setY(80);
            rectangle.setX(-150);

            Rectangle rectangle1 = new Rectangle(0, 135);
            rectangle1.setY(80);
            rectangle1.setX(-150);

            Rectangle rectangle2 = new Rectangle(400, 0);
            rectangle2.setY(20);
            rectangle2.setX(-150);

            animation = new PathTransition();
            animation.setNode(ghost1V);
            animation.setDuration(Duration.seconds(2));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(rectangle);
            animation.play();

            animation2 = new PathTransition();
            animation2.setNode(ghost2V);
            animation2.setDuration(Duration.seconds(3));
            animation2.setAutoReverse(true);
            animation2.setCycleCount(Animation.INDEFINITE);
            animation2.setPath(rectangle1);
            animation2.play();

            animation3 = new PathTransition();
            animation3.setNode(ghost3V);
            animation3.setDuration(Duration.seconds(2.5));
            animation3.setCycleCount(Animation.INDEFINITE);
            animation3.setPath(rectangle2);
            animation3.play();
        }

        else if (currentLevel==6){
            ImageView ghost1V = new ImageView();
            ImageView ghost2V = new ImageView();
            ImageView ghost3V = new ImageView();
            ImageView ghost4V = new ImageView();

            ghost4V.setImage(ghost);
            ghost3V.setImage(ghost);
            ghost2V.setImage(ghost);
            ghost1V.setImage(ghost);

            add(ghost4V, 8, 10);
            add(ghost3V, 8, 6);

            add(ghost1V, 7, 6);
            add(ghost2V, 8, 2);



            Rectangle rectangle = new Rectangle(83, 125);
            rectangle.setY(65);
            rectangle.setX(-150);

            Rectangle rectangle1 = new Rectangle(0, 40);
            rectangle1.setY(65);
            rectangle1.setX(-150);

            Rectangle rectangle2 = new Rectangle(170, 87);
            rectangle2.setY(20);
            rectangle2.setX(20);

            Rectangle rectangle3 = new Rectangle(170, 87);
            rectangle3.setY(20);
            rectangle3.setX(20);


            animation4 = new PathTransition();
            animation4.setNode(ghost4V);
            animation4.setDuration(Duration.seconds(3));
            animation4.setAutoReverse(true);
            animation4.setCycleCount(Animation.INDEFINITE);
            animation4.setPath(rectangle3);
            animation4.play();

            animation3 = new PathTransition();
            animation3.setNode(ghost3V);
            animation3.setDuration(Duration.seconds(2.5));
            animation3.setAutoReverse(true);
            animation3.setCycleCount(Animation.INDEFINITE);
            animation3.setPath(rectangle2);
            animation3.play();

            animation2 = new PathTransition();
            animation2.setNode(ghost2V);
            animation2.setDuration(Duration.seconds(1.5));
            animation2.setAutoReverse(true);
            animation2.setCycleCount(Animation.INDEFINITE);
            animation2.setPath(rectangle1);
            animation2.play();

            animation = new PathTransition();
            animation.setNode(ghost1V);
            animation.setDuration(Duration.seconds(2));
            animation.setAutoReverse(false);
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(rectangle);
            animation.play();


        }



    }
}
