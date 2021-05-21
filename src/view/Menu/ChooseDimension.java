package view.Menu;

import control.MainProgram;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileNotFoundException;

/**
 * Author: Viktor Näslund
 */

public class ChooseDimension extends Pane {
    private MainProgram mainProgram;
    private Image chooseDimension;
    private Image tenByTen;
    private Image tenByTenResize;
    private Image fourteen;
    private Image fourteenResize;
    private Image eighteen;
    private Image eighteenResize;
    private Image pain;
    private Image painResize;

    public ChooseDimension(MainProgram mainProgram){
        this.mainProgram = mainProgram;
        setBackground();
        setupImages();
        addButtons();
    }

    public void setupImages(){
        chooseDimension = new Image("file:files/texts/ChooseDimension.png", 800, 600, false,false);
        tenByTen = new Image("file:files/texts/10x10.png", 250, 30, false, false);
        tenByTenResize = new Image("file:files/texts/10x10.png", 275, 33, false, false);
        fourteen = new Image("file:files/texts/14x14.png", 250, 30, false, false);
        fourteenResize = new Image("file:files/texts/14x14.png", 275, 33, false, false);
        eighteen = new Image("file:files/texts/18x18.png", 250, 30, false, false);
        eighteenResize = new Image("file:files/texts/18x18.png", 275, 33, false, false);
        pain = new Image("file:files/texts/Pain.png", 250, 30, false, false);
        painResize = new Image("file:files/texts/Pain.png", 275, 33, false, false);
    }

    public void setBackground(){
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
    }

    public void addButtons(){
        ImageView dimensionView = new ImageView(chooseDimension);
        dimensionView.setStyle("fx-background-color: transparent;");


        ImageView tenByTenView = new ImageView(tenByTen);
        tenByTenView.setStyle("fx-background-color: transparent;");
        tenByTenView.setTranslateX(275);
        tenByTenView.setTranslateY(200);
        tenByTenView.toFront();
        tenByTenView.setOnMouseEntered(e -> {
            tenByTenView.setImage(tenByTenResize);
            tenByTenView.setTranslateX(263);
            tenByTenView.setTranslateY(197);
        });
        tenByTenView.setOnMouseExited(e -> {
            tenByTenView.setImage(tenByTen);
            tenByTenView.setTranslateX(275);
            tenByTenView.setTranslateY(200);
        });
        tenByTenView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(10);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView fourteenView = new ImageView(fourteen);
        fourteenView.setStyle("fx-background-color: transparent;");
        fourteenView.setTranslateX(275);
        fourteenView.setTranslateY(225);
        fourteenView.toFront();
        fourteenView.setOnMouseEntered(e -> {
            fourteenView.setImage(fourteenResize);
            fourteenView.setTranslateX(263);
            fourteenView.setTranslateY(222);
        });
        fourteenView.setOnMouseExited(e -> {
            fourteenView.setImage(fourteen);
            fourteenView.setTranslateX(275);
            fourteenView.setTranslateY(225);
        });
        fourteenView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(14);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView eighteenView = new ImageView(eighteen);
        eighteenView.setStyle("fx-background-color: transparent;");
        eighteenView.setTranslateX(275);
        eighteenView.setTranslateY(250);
        eighteenView.toFront();
        eighteenView.setOnMouseEntered(e -> {
            eighteenView.setImage(eighteenResize);
            eighteenView.setTranslateX(263);
            eighteenView.setTranslateY(247);
        });
        eighteenView.setOnMouseExited(e -> {
            eighteenView.setImage(eighteen);
            eighteenView.setTranslateX(275);
            eighteenView.setTranslateY(250);
        });
        eighteenView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(18);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView painView = new ImageView(pain);
        painView.setStyle("fx-background-color: transparent;");
        painView.setTranslateX(275);
        painView.setTranslateY(275);
        painView.toFront();
        painView.setOnMouseEntered(e -> {
            painView.setImage(painResize);
            painView.setTranslateX(263);
            painView.setTranslateY(272);
        });
        painView.setOnMouseExited(e -> {
            painView.setImage(pain);
            painView.setTranslateX(275);
            painView.setTranslateY(275);
        });
        painView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(28);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        getChildren().addAll(dimensionView,tenByTenView,fourteenView,eighteenView,painView);
    }

}
