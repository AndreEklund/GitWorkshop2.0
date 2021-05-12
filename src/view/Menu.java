package view;

import control.MainProgram;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * Author: Viktor Näslund
 */

public class Menu extends VBox {
    private MainProgram mainProgram;

    public Menu(MainProgram mainProgram){
        this.mainProgram = mainProgram;
        setBackground();
        addButtons();
    }


    public void setBackground(){
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
    }

    public void addButtons(){
        Button button1 = new Button("");
        Image btnStart = new Image("file:files/BtnStart.png", 250, 30, false, true);
        button1.setGraphic(new ImageView(btnStart));
        button1.setOnAction(e -> mainProgram.changeToLevel());
        button1.setTranslateX(250);
        button1.setTranslateY(125);
        Button button2 = new Button("");
        Image btnMapCreator = new Image("file:files/BtnMapCreator.png", 250, 30, false, true);
        button2.setGraphic(new ImageView(btnMapCreator));
        button2.setOnAction(e -> mainProgram.changeToMapCreator());
        button2.setTranslateX(250);
        button2.setTranslateY(150);
        this.getChildren().addAll(button1,button2);
    }

}
