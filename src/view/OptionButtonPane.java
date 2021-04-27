package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class OptionButtonPane extends GridPane {

    /**
     * Author Filip Örnling
     */

    private Image image;
    private String imageUrl = "files/Startgame.png";
    private Main main;
    private MapCreator mapCreator;
    public OptionButtonPane(MapCreator mapCreator,Main main) throws FileNotFoundException {
        this.main=main;
        this.mapCreator=mapCreator;
        FileInputStream inputStream = new FileInputStream(imageUrl);
        image = new Image(inputStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(100);

        BackgroundImage bimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(image.getWidth(),image.getHeight(),true,true,true,false));

        Button startGame = new Button();
        startGame.setGraphic(imageView);

        Background background = new Background(bimage);
        startGame.setBackground(background);

        add(startGame,0,0);
        startGame.setOnMouseClicked(e ->startGameClicked(e));
        Button mute = new Button("Main Menu");
        mute.setOnMouseClicked(e -> Mainmenyclicked(e));
        add(mute,0,1);
    }

    private void startGameClicked(MouseEvent e) {
        System.out.println("Startgame Clicked");
        mapCreator.start();
    }

    private void Mainmenyclicked(MouseEvent e){
        System.out.println("Main Menu clicked");
        main.setStartScreen();

    }



}