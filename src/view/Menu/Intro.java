package view.Menu;

import control.MainProgram;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

/**
 * Author: Viktor Näslund
 */

public class Intro extends VBox {

    private MainProgram mainProgram;

    public Intro(MainProgram mainProgram){
        this.mainProgram = mainProgram;
        setBackground();
        addListener();
    }


    public void setBackground(){
        BackgroundImage myBI= new BackgroundImage(new Image("file:files/IntroPic.jpg",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
    }


    public void addListener(){
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainProgram.changeToMenu();
            }
        });
    }

}
