package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import view.MapCreator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class OptionButtonPane extends GridPane {

    private Image image;
    private String imageUrl = "C:\\Users\\Filip\\Desktop\\GitWorkshop2.0\\files\\Startgame.png";

    private MapCreator mapCreator;
    public OptionButtonPane(MapCreator mapCreator) throws FileNotFoundException {
        this.mapCreator=mapCreator;
        FileInputStream inputStream = new FileInputStream(imageUrl);
        image = new Image(inputStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(100);

        Button startGame = new Button();
        startGame.setGraphic(imageView);
        add(startGame,0,0);
        startGame.setOnMouseClicked(e ->startGameClicked(e));
        Button mute = new Button("Mute Music");
        mute.setOnMouseClicked(e -> muteClicked(e));
        add(mute,0,1);
    }

    private void startGameClicked(MouseEvent e) {
        System.out.println("Startgame Clicked");
        mapCreator.start();
    }


    private void muteClicked(MouseEvent e){
        System.out.println("Mute button Clicked!");
    }



}