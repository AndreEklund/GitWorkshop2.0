package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.DigitalClock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;


public class OptionButtonPane extends GridPane {

    /**
     * Author Filip Örnling
     */

    private Image imageStart;
    private Image imageMeny;
    private String imageUrl = "files/Stage1.png";
    private String imageUrlMeny = "files/Menybutton.png";
    private Main main;
    private MapCreator mapCreator;
    private DigitalClock clock = new DigitalClock();
    private boolean running = false;
    private javafx.scene.control.Label label = new Label("Digital Clock Test...");

    public OptionButtonPane(MapCreator mapCreator,Main main) throws FileNotFoundException {
        this.main=main;
        this.mapCreator=mapCreator;

        File file = new File(imageUrl);
        imageStart = new Image(file.toURI().toString());


        ImageView imageView = new ImageView(imageStart);
        imageView.setFitHeight(50);
        imageView.setFitWidth(100);

        FileInputStream inputStream = new FileInputStream(imageUrlMeny);
        imageMeny = new Image(inputStream);

        ImageView image = new ImageView(imageStart);

        BackgroundImage bimage2 = new BackgroundImage(imageMeny,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(imageMeny.getWidth(),imageMeny.getHeight(),true,true,true,false));

        add(image,0,1);


        Button startGame = new Button();
        startGame.setGraphic(imageView);

        //Background background = new Background(bimage);
       // startGame.setBackground(background);
        add(label,0,2);
        add(clock,0,3);
        runClock();
        add(startGame,0,0);
        //startGame.setOnMouseClicked(e ->startGameClicked(e));

        Button mute = new Button();
        ImageView imageView1 = new ImageView(imageMeny);
        mute.setGraphic(imageView1);

        Background background1 = new Background(bimage2);
        
        mute.setBackground(background1);
        mute.setOnMouseClicked(e -> Mainmenyclicked(e));
        add(mute,0,0);
    }



    private void Mainmenyclicked(MouseEvent e){
        System.out.println("Main Menu clicked");
        main.setStartScreen();

    }

    private void runClock() {
        running = true;
        new Thread() {
            public void run() {
                long last = System.nanoTime();
                double delta = 0;
                double ns = 1000000000.0 / 1;
                int count = 0;

                while (running) {
                    long now = System.nanoTime();
                    delta += (now - last) / ns;
                    last = now;

                    while (delta >= 1) {
                        count = (count + 1) % 60;
                        System.out.println("pulse...." + count);
                        DecimalFormat df = new DecimalFormat("00");
                        clock.refreshDigits(df.format(count));
                        delta--;
                    }
                }
            }
        }.start();
    }



}