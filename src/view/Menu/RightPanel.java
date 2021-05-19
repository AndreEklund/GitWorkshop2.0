package view.Menu;

import control.MainProgram;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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



    public RightPanel(MainProgram mainProgram, String gameMode) throws FileNotFoundException {
        this.mainProgram = mainProgram;
        this.gameMode = gameMode;

        levelNumber = new Image("file:files/levelcounter/"+ gameMode +".png", 90, 30, false, false);
        currentLevelView = new ImageView(levelNumber);
        imageMenu = new Image("file:files/texts/Menu.png", 90, 30, false, false);

        level = new Label();
        level.setTranslateX(8);
        level.setGraphic(currentLevelView);

        BackgroundImage menuBGImage = new BackgroundImage(imageMenu,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(imageMenu.getWidth(), imageMenu.getHeight(),false,false,false,false));

        add(level,0,1);

        //add(clock,0,2);
        runClock();


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