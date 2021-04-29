package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


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



}