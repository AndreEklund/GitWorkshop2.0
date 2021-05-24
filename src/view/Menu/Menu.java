package view.Menu;

import control.MainProgram;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import view.AudioPlayer;

import java.io.FileNotFoundException;

/**
 * Author: Viktor Näslund
 */

public class Menu extends Pane {
    private MainProgram mainProgram;
    private Image campaign;
    private Image campaignResize;
    private Image randomize;
    private Image randomizeResize;
    private Image help;
    private Image helpResize;
    private Image mazegen;
    private AudioPlayer audioPlayer;

    public Menu(MainProgram mainProgram, AudioPlayer audioPlayer){
        this.mainProgram = mainProgram;
        this.audioPlayer = audioPlayer;
        setBackground();
        setupImages();
        addButtons();
    }

    public void setupImages(){
        mazegen = new Image("file:files/texts/MazegenTitel.png", 800, 600, false,false);
        campaign = new Image("file:files/texts/Campaign.png", 250, 30, false, false);
        campaignResize = new Image("file:files/texts/Campaign.png", 255, 33, false, false);
        randomize = new Image("file:files/texts/Randomize.png", 250, 30, false, false);
        randomizeResize = new Image("file:files/texts/Randomize.png", 255, 33, false, false);
        help = new Image("file:files/texts/Help.png", 250, 30, false, false);
        helpResize = new Image("file:files/texts/Help.png", 255, 33, false, false);
    }

    public void setBackground(){
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
    }

    public void addButtons(){
        ImageView mazegenView = new ImageView(mazegen);
        mazegenView.setStyle("fx-background-color: transparent;");


        ImageView campaignView = new ImageView(campaign);
        campaignView.setStyle("fx-background-color: transparent;");
        campaignView.setTranslateX(275);
        campaignView.setTranslateY(200);
        campaignView.toFront();
        campaignView.setOnMouseEntered(e -> {
            campaignView.setImage(campaignResize);
            campaignView.setTranslateX(273);
            campaignView.setTranslateY(197);
        });
        campaignView.setOnMouseExited(e -> {
            campaignView.setImage(campaign);
            campaignView.setTranslateX(275);
            campaignView.setTranslateY(200);
        });
        campaignView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToCampaign();
                audioPlayer.playForestMusic();
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView randomizeView = new ImageView(randomize);
        randomizeView.setStyle("fx-background-color: transparent;");
        randomizeView.setTranslateX(275);
        randomizeView.setTranslateY(250);
        randomizeView.toFront();
        randomizeView.setOnMouseEntered(e -> {
            randomizeView.setImage(randomizeResize);
            randomizeView.setTranslateX(273);
            randomizeView.setTranslateY(247);
        });
        randomizeView.setOnMouseExited(e -> {
            randomizeView.setImage(randomize);
            randomizeView.setTranslateX(275);
            randomizeView.setTranslateY(250);
        });
        randomizeView.setOnMouseClicked(e -> {
            mainProgram.chooseDimension();
            audioPlayer.playButtonSound();
        });

        ImageView helpView = new ImageView(help);
        helpView.setStyle("fx-background-color: transparent;");
        helpView.setTranslateX(275);
        helpView.setTranslateY(300);
        helpView.toFront();
        helpView.setOnMouseEntered(e -> {
            helpView.setImage(helpResize);
            helpView.setTranslateX(273);
            helpView.setTranslateY(297);
        });
        helpView.setOnMouseExited(e -> {
            helpView.setImage(help);
            helpView.setTranslateX(275);
            helpView.setTranslateY(300);
        });
        helpView.setOnMouseClicked(e -> {
            mainProgram.changeToHelp();
            audioPlayer.playButtonSound();
        });

        this.getChildren().addAll(campaignView,randomizeView,helpView,mazegenView);
    }

}
