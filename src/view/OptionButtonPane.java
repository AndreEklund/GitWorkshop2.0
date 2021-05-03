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

    private Image level1Image;
    private Image level2Image;
    private Image level3Image;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;

    private Label level;



    private Image imageMeny;
    private String lvl1 = "files/level1_1.png";
    private String lvl2 = "files/level2ny.png";
    private String lvl3 = "files/level3ny.png";
    private String imageUrlMeny = "files/Menybutton.png";
    private Main main;
    private MapCreator mapCreator;
    private DigitalClock clock = new DigitalClock();
    private boolean running = false;



    public OptionButtonPane(MapCreator mapCreator,Main main) throws FileNotFoundException {
        this.main=main;
        this.mapCreator=mapCreator;

        File file1 = new File(lvl1);
        File file2 = new File(lvl2);
        File file3 = new File(lvl3);

        level1Image = new Image(file1.toURI().toString(),150,50,false,false);
        level2Image = new Image(file2.toURI().toString(),150,50,false,false);
        level3Image = new Image(file3.toURI().toString(),150,50,false,false);

         imageView1 = new ImageView(level1Image);
         imageView2 = new ImageView(level2Image);
         imageView3 = new ImageView(level3Image);




        FileInputStream inputStream = new FileInputStream(imageUrlMeny);
        imageMeny = new Image(inputStream);

        level = new Label();

        level.setGraphic(imageView1);


        BackgroundImage bimage2 = new BackgroundImage(imageMeny,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(imageMeny.getWidth(),imageMeny.getHeight(),true,true,true,false));

        add(level,0,1);

        add(clock,0,2);
        runClock();


        Button Meny = new Button();
        ImageView imageViewMeny = new ImageView(imageMeny);
        Meny.setGraphic(imageViewMeny);

        Background background1 = new Background(bimage2);
        
        Meny.setBackground(background1);
        Meny.setOnMouseClicked(e -> Mainmenyclicked(e));
        add(Meny,0,0);
    }

    public void changeLvl2(){
        getChildren().remove(imageView1);
        add(imageView2,0,1);
    }

    public void changeLvl3(){
        imageView2.setVisible(false);
        imageView3.setVisible(true);
    }



    private void Mainmenyclicked(MouseEvent e){
        System.out.println("Main Menu clicked");
        main.changeToMenu();

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
                        DecimalFormat df = new DecimalFormat("00");
                        clock.refreshDigits(df.format(count));
                        delta--;
                    }
                }
            }
        }.start();
    }



}