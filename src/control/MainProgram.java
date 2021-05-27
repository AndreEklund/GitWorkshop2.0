package control;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Camera;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import model.Maps.*;

import model.MazeGeneration.GenerateNextLevel;
import model.MapCreator;
import view.AudioPlayer;
import view.Campaign.*;
import view.GameOverScreen;
import view.Randomize.MapTemplate;
import model.MazeGeneration.MazeGenerator;
import view.Menu.*;
import view.WorldIntroAnimation;


import java.io.FileNotFoundException;

public class MainProgram extends Application {

    /**
     * Author André Eklund
     * Edit Filip Örnling, Viktor Näslund
     */
    private Stage mainWindow;
    private BorderPane mainPaneRandomMaze;
    private BorderPane mainPaneCampaign;
    private MapTemplate mapTemplate;
    private Scene menuScene;
    private Scene introScene;
    private Scene helpScene;
    private Scene chooseDimensionScene;
    private Intro intro;
    private Menu menu;
    private Help help;
    private ChooseDimension chooseDimension;
    private Scene randomScene;
    private Scene campaignScene;
    private RightPanel rightPanel;
    private RightPanel rightPnlRndm;
    private MazeGenerator mazeGenerator;
    private GenerateNextLevel generateNextLevel;
    private World1Template world1Template;
    private World1Maps world1Maps;
    private World3Maps world3Maps;
    private World4Maps world4Maps;
    private WorldIntroAnimation introAnimation;
    private AudioPlayer audioPlayer;
    private GameOverScreen gameOverScreen;
    private Image cursorImage;


    @Override

    public void start(Stage primaryStage) throws Exception{



        //En klass som spelar alla ljud/musik
        audioPlayer = new AudioPlayer();
        audioPlayer.playIntroMusic();

        //
        rightPanel = new RightPanel(this, "11", audioPlayer, null);

        rightPanel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        //Menu och Intro scenes
        menu = new Menu(this, audioPlayer);
        intro = new Intro(this, audioPlayer);
        help = new Help(this, audioPlayer);
        chooseDimension = new ChooseDimension(this, audioPlayer);
        introScene = new Scene(intro, 800, 600);
        menuScene = new Scene(menu, 800, 600);
        helpScene = new Scene(help, 800, 600);
        chooseDimensionScene = new Scene(chooseDimension,800,600);

        //BorderPane för levels
        mainPaneRandomMaze = new BorderPane();
        mainPaneCampaign = new BorderPane();
        introAnimation = new WorldIntroAnimation();
        //mainPaneCampaign.getChildren().add(introAnimation);

        mainWindow = primaryStage;


      //  mazeGenerator = new MazeGenerator(5, true);
        cursorImage = new Image("file:files/imagecursor.png");



        mainWindow.setTitle("Mazegen");
        //mainWindow.initStyle(StageStyle.UTILITY);
        mainWindow.setResizable(false);
        mainWindow.setOnCloseRequest(windowEvent -> System.exit(0));


        world3Maps = new World3Maps();
        world1Maps = new World1Maps();
        world4Maps = new World4Maps();

        //generateNextLevel = new GenerateNextLevel(this, mainPaneRandomMaze, mazeGenerator, rightPanel);

        mainPaneCampaign.setRight(rightPanel);


        rightPnlRndm = new RightPanel(this, "Random", audioPlayer, null);
        rightPnlRndm.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        //mapTemplate = new MapTemplate(mazeGenerator.getMaze(),this, generateNextLevel);
        MapCreator mapCreator = new MapCreator();

        //Mobmazelevel mobmazelevel = new Mobmazelevel();
        //mainPaneCampaign.setCenter(mobmazelevel);



        //mainPaneRandomMaze.setCenter(mapTemplate);
        mainPaneRandomMaze.setRight(rightPnlRndm);

        campaignScene = new Scene(mainPaneCampaign, 800, 600);
        randomScene = new Scene(mainPaneRandomMaze, 800, 600);


        mainWindow.setScene(introScene);
        mainWindow.show();

        introScene.setCursor(new ImageCursor(cursorImage));
        menuScene.setCursor(new ImageCursor(cursorImage));
        campaignScene.setCursor(new ImageCursor(cursorImage));
        chooseDimensionScene.setCursor(new ImageCursor(cursorImage));
        helpScene.setCursor(new ImageCursor(cursorImage));
        randomScene.setCursor(new ImageCursor(cursorImage));
    }

    public void changeToMapTemplate(){
        mainPaneRandomMaze.setCenter(mapTemplate);
    }
    public void changeToMenu(){
        mainWindow.setScene(menuScene);
        System.out.println("MENU");
    }
    public void changeToRandomize(int dimension) throws FileNotFoundException {
        mazeGenerator = new MazeGenerator(dimension, true);
        generateNextLevel = new GenerateNextLevel(this, mainPaneRandomMaze, mazeGenerator, rightPanel, dimension);
        mapTemplate = new MapTemplate(mazeGenerator.getMaze(),this, generateNextLevel);
        mainPaneRandomMaze.setCenter(mapTemplate);
       // mapTemplate = new MapTemplate(mazeGenerator.getMaze(),this, generateNextLevel);
        mainWindow.setScene(randomScene);
    }
    public void changeToCampaign() throws FileNotFoundException {
        world1Template = new World4Template(world4Maps.getLevel43(), 4, 3, this, rightPanel, 3, audioPlayer);
        mainPaneCampaign.setCenter(world1Template);
        mainWindow.setScene(campaignScene);
        introAnimation = new WorldIntroAnimation("1");
        mainPaneCampaign.getChildren().add(introAnimation);
        introAnimation.setDisable(true);
    }
    public void chooseDimension(){
        mainWindow.setScene(chooseDimensionScene);
    }

    public void changeToHelp(){
        mainWindow.setScene(helpScene);
    }
    public void gameOver() {
        gameOverScreen = new GameOverScreen(this);
        mainPaneCampaign.getChildren().add(gameOverScreen);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void nextWorld1Level(int level, int heartCrystals) throws FileNotFoundException, InterruptedException {

        if (level == 1) {
            System.out.println("hello");
            rightPanel.changeLevelCounter("12");
            mainPaneCampaign.setCenter(new World1Template(world1Maps.getLevel12(), 2, heartCrystals, this, rightPanel, 0, audioPlayer, 15));

        }
        else if (level == 2) {
            rightPanel.changeLevelCounter("13");
            mainPaneCampaign.setCenter(new World1Template(world1Maps.getLevel13(), 3, heartCrystals, this, rightPanel, 0, audioPlayer, 15));
        }
        else if (level == 3) {
            rightPanel.changeLevelCounter("14");
            mainPaneCampaign.setCenter(new World1Template(world1Maps.getLevel14(), 4, heartCrystals, this, rightPanel, 0, audioPlayer, 15));
        }
        else if (level == 4) {
            rightPanel.changeLevelCounter("15");
            mainPaneCampaign.setCenter(new World1Template(world1Maps.getLevel15(), 5, heartCrystals, this, rightPanel, 0, audioPlayer, 15));
        }
        else if (level == 5) {
            nextWorld2Level(1, heartCrystals);
        }

    }
    public void nextWorld2Level(int level, int heartCrystals) throws FileNotFoundException, InterruptedException {

        World2Maps world2Maps = new World2Maps();

        if (level == 1) {
            rightPanel.changeLevelCounter("21");
            mainPaneCampaign.setCenter(new World2Template(world2Maps.getLevel21(), 2, heartCrystals, this, rightPanel, 1, audioPlayer, false, rightPanel));
            introAnimation = new WorldIntroAnimation("2");
            mainPaneCampaign.getChildren().add(introAnimation);
            introAnimation.setDisable(true);
            audioPlayer.playWorldIntroSound();
        }
        else if (level == 2) {
            rightPanel.changeLevelCounter("22");
            mainPaneCampaign.setCenter(new World2Template(world2Maps.getLevel22(), 3, heartCrystals, this, rightPanel, 1, audioPlayer, false, rightPanel));
        }
        else if (level == 3) {
            rightPanel.changeLevelCounter("23");
            mainPaneCampaign.setCenter(new World2Template(world2Maps.getLevel23(), 4, heartCrystals, this, rightPanel, 1, audioPlayer, false, rightPanel));
        }
        else if (level == 4) {
            rightPanel.changeLevelCounter("24");
            mainPaneCampaign.setCenter(new World2Template(world2Maps.getLevel24(), 5, heartCrystals, this, rightPanel, 1, audioPlayer, false, rightPanel));
        }
        else if (level == 5) {
            rightPanel.changeLevelCounter("25");
            mainPaneCampaign.setCenter(new World2Template(world2Maps.getLevel25(), 6, heartCrystals, this, rightPanel, 1, audioPlayer, true, rightPanel));
        }
        else if (level == 6) {
            nextWorld3Level(1, heartCrystals);
        }
    }
    public void nextWorld3Level(int level, int heartCrystals) throws FileNotFoundException, InterruptedException {

        World3Maps world3Maps = new World3Maps();

        if (level == 1) {
            rightPanel.changeLevelCounter("31");
            mainPaneCampaign.setCenter(new World3Template(world3Maps.getLevel31(), 2, heartCrystals, this, rightPanel, 2, audioPlayer));
            introAnimation = new WorldIntroAnimation("3");
            mainPaneCampaign.getChildren().add(introAnimation);
            introAnimation.setDisable(true);
            audioPlayer.playWorldIntroSound();
            audioPlayer.stopMusic();
            audioPlayer.playLevelMusic("lava");
        }
        else if (level == 2) {
            rightPanel.changeLevelCounter("32");
            mainPaneCampaign.setCenter(new World3Template(world3Maps.getLevel32(), 3, heartCrystals, this, rightPanel, 2, audioPlayer));
        }
        else if (level == 3) {
            rightPanel.changeLevelCounter("33");
            mainPaneCampaign.setCenter(new World3Template(world3Maps.getLevel33(), 4, heartCrystals, this, rightPanel, 2, audioPlayer));
        }
        else if (level == 4) {
            rightPanel.changeLevelCounter("34");
            mainPaneCampaign.setCenter(new World3Template(world3Maps.getLevel34(), 5, heartCrystals, this, rightPanel, 2, audioPlayer));
        }
        else if (level == 5) {
            rightPanel.changeLevelCounter("35");
            mainPaneCampaign.setCenter(new World3Template(world3Maps.getLevel35(), 6, heartCrystals, this, rightPanel, 2, audioPlayer));
        }
        else if (level == 6) {
            nextWorld4Level(1, heartCrystals);
        }
    }
    public void nextWorld4Level(int level, int heartCrystals) throws FileNotFoundException, InterruptedException {

        World4Maps world4Maps = new World4Maps();

        if (level == 1) {
            rightPanel.changeLevelCounter("41");
            mainPaneCampaign.setCenter(new World4Template(world4Maps.getLevel41(), 2, heartCrystals, this, rightPanel, 3, audioPlayer));
            introAnimation = new WorldIntroAnimation("4");
            mainPaneCampaign.getChildren().add(introAnimation);
            introAnimation.setDisable(true);
            audioPlayer.playWorldIntroSound();
            audioPlayer.stopMusic();
            audioPlayer.playLevelMusic("heaven");
        }
        else if (level == 2) {
            rightPanel.changeLevelCounter("42");
            mainPaneCampaign.setCenter(new World4Template(world4Maps.getLevel42(), 3, heartCrystals, this, rightPanel, 3, audioPlayer));
        }
        else if (level == 3) {
            rightPanel.changeLevelCounter("43");
            mainPaneCampaign.setCenter(new World4Template(world4Maps.getLevel43(), 4, heartCrystals, this, rightPanel, 3, audioPlayer));
        }
        else if (level == 4) {
            rightPanel.changeLevelCounter("44");
            mainPaneCampaign.setCenter(new World4Template(world4Maps.getLevel44(), 5, heartCrystals, this, rightPanel, 3, audioPlayer));
        }
        else if (level == 5) {
            rightPanel.changeLevelCounter("45");
            mainPaneCampaign.setCenter(new World4Template(world4Maps.getLevel45(), 6, heartCrystals, this, rightPanel, 3, audioPlayer));
        }
        else if (level == 6) {
            nextWorld5Level(1, heartCrystals);
        }
    }
    public void nextWorld5Level(int level, int heartCrystals) throws FileNotFoundException, InterruptedException {

        World5Maps world5Maps = new World5Maps();

        if (level == 1) {
            rightPanel.changeLevelCounter("51");
            mainPaneCampaign.setCenter(new World5Template(world5Maps.getLevel51(), 2, heartCrystals, this, rightPanel, 4, audioPlayer));
            introAnimation = new WorldIntroAnimation("5");
            mainPaneCampaign.getChildren().add(introAnimation);
            introAnimation.setDisable(true);
            audioPlayer.playWorldIntroSound();
            audioPlayer.stopMusic();
            audioPlayer.playLevelMusic("egypt");
        }
        else if (level == 2) {
            rightPanel.changeLevelCounter("52");
            mainPaneCampaign.setCenter(new World5Template(world5Maps.getLevel52(), 3, heartCrystals, this, rightPanel, 4, audioPlayer));
        }
        else if (level == 3) {
            rightPanel.changeLevelCounter("53");
            mainPaneCampaign.setCenter(new World5Template(world5Maps.getLevel53(), 4, heartCrystals, this, rightPanel, 4, audioPlayer));
        }
        else if (level == 4) {
            rightPanel.changeLevelCounter("54");
            mainPaneCampaign.setCenter(new World5Template(world5Maps.getLevel54(), 5, heartCrystals, this, rightPanel, 4, audioPlayer));
        }
        else if (level == 5) {
            rightPanel.changeLevelCounter("55");
            mainPaneCampaign.setCenter(new World5Template(world5Maps.getLevel55(), 6, heartCrystals, this, rightPanel, 4, audioPlayer));
        }
        else if (level == 6) {
            nextWorld6Level(1, heartCrystals);
        }
    }
    public void nextWorld6Level(int level, int heartCrystals) throws FileNotFoundException, InterruptedException {

        World6Maps world6Maps = new World6Maps();

        if (level == 1) {
            rightPanel.changeLevelCounter("61");
            mainPaneCampaign.setCenter(new World6Template(world6Maps.getLevel61(), 2, heartCrystals, this, rightPanel, 4, audioPlayer));
            introAnimation = new WorldIntroAnimation("6");
            mainPaneCampaign.getChildren().add(introAnimation);
            introAnimation.setDisable(true);
            audioPlayer.playWorldIntroSound();
        }
        else if (level == 2) {
            rightPanel.changeLevelCounter("62");
            mainPaneCampaign.setCenter(new World6Template(world6Maps.getLevel62(), 3, heartCrystals, this, rightPanel, 4, audioPlayer));
        }
        else if (level == 3) {
            rightPanel.changeLevelCounter("63");
            mainPaneCampaign.setCenter(new World6Template(world6Maps.getLevel63(), 4, heartCrystals, this, rightPanel, 4, audioPlayer));
        }
        else if (level == 4) {
            rightPanel.changeLevelCounter("64");
            mainPaneCampaign.setCenter(new World6Template(world6Maps.getLevel64(), 5, heartCrystals, this, rightPanel, 4, audioPlayer));
        }
        else if (level == 5) {
            rightPanel.changeLevelCounter("65");
            mainPaneCampaign.setCenter(new World6Template(world6Maps.getLevel65(), 5, heartCrystals, this, rightPanel, 4, audioPlayer));
        }
    }
}
