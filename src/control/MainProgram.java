package control;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Maps.World2Maps;
import model.MazeGeneration.GenerateNextLevel;
import model.MapCreator;
import model.Maps.World1Maps;
import view.Campaign.World2Template;
import view.Campaign.World1Template;
import view.MapTemplate;
import model.MazeGeneration.MazeGenerator;
import outdatedClasses.ForestLvlTemplate;
import outdatedClasses.LavaLvlTemplate;
import view.Menu.Intro;
import view.Menu.Menu;
import view.Menu.OptionButtonPane;


import java.io.FileNotFoundException;

public class MainProgram extends Application {

    /**
     * Author André Eklund
     * Edit Filip Örnling, Viktor Näslund
     */
    private ForestLvlTemplate forestLvlTemplate;
    private Stage mainWindow;
    private BorderPane mainPaneRandomMaze;
    private BorderPane mainPaneCampaign;
    private LavaLvlTemplate lavaLvlTemplate;
    private MapTemplate mapTemplate;
    private Scene menuScene;
    private Scene introScene;
    private Intro intro;
    private Menu menu;
    private Scene levelScene;
    private Scene mapCreatorScene;
    private OptionButtonPane obp;
    private OptionButtonPane obp2;
    private MazeGenerator mazeGenerator;
    private GenerateNextLevel generateNextLevel;
    private World1Template world1Template;
    private World1Maps world1Maps;


    @Override

    public void start(Stage primaryStage) throws Exception{

        //Menu och Intro scenes
        menu = new Menu(this);
        intro = new Intro(this);
        introScene = new Scene(intro, 800, 600);
        menuScene = new Scene(menu, 800, 600);

        //BorderPane för levels
        mainPaneRandomMaze = new BorderPane();
        mainPaneCampaign = new BorderPane();

        mainWindow = primaryStage;

        mainWindow.setTitle("Mazegen");
        mainWindow.initStyle(StageStyle.UTILITY);
        mainWindow.setResizable(false);
        mainWindow.setOnCloseRequest(windowEvent -> System.exit(0));
        
        mazeGenerator = new MazeGenerator(10, true);
        world1Maps = new World1Maps();
        World2Maps world2Maps = new World2Maps();

        generateNextLevel = new GenerateNextLevel(this, mainPaneRandomMaze, mazeGenerator);
        world1Template = new World1Template(world1Maps.getLevel11(), 1, 3, this);


        mapTemplate = new MapTemplate(mazeGenerator.getMaze(),this, generateNextLevel);
        MapCreator mapCreator = new MapCreator();

        //Mobmazelevel mobmazelevel = new Mobmazelevel();
        //mainPaneCampaign.setCenter(mobmazelevel);

        obp = new OptionButtonPane(mapCreator,this);
        mainPaneCampaign.setCenter(world1Template);

        mainPaneCampaign.setRight(obp);
        obp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


        obp2 = new OptionButtonPane(mapCreator,this);
        obp2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        mainPaneRandomMaze.setCenter(mapTemplate);
        mainPaneRandomMaze.setRight(obp2);

        mapCreatorScene = new Scene(mainPaneCampaign, 800, 600);
        levelScene = new Scene(mainPaneRandomMaze, 800, 600);


        mainWindow.setScene(introScene);
        mainWindow.show();
    }

    public void changeToMapTemplate(){
        mainPaneRandomMaze.setCenter(mapTemplate);
    }
    public void changeToMenu(){
        mainWindow.setScene(menuScene);
        System.out.println("MENU");
    }
    public void changeToLevel(){
        mainWindow.setScene(levelScene);
    }
    public void changeToMapCreator(){
        mainWindow.setScene(mapCreatorScene);
    }




    public static void main(String[] args) {
        launch(args);
    }

    public void nextWorld1Level(int level, int heartCrystals) throws FileNotFoundException, InterruptedException {

        if (level == 1) {
            System.out.println("hello");
            mainPaneCampaign.setCenter(new World1Template(world1Maps.getLevel12(), 2, heartCrystals, this));
        }
        else if (level == 2) {
            mainPaneCampaign.setCenter(new World1Template(world1Maps.getLevel13(), 3, heartCrystals, this));
        }
        else if (level == 3) {
            mainPaneCampaign.setCenter(new World1Template(world1Maps.getLevel14(), 4, heartCrystals, this));
        }
        else if (level == 4) {
            mainPaneCampaign.setCenter(new World1Template(world1Maps.getLevel15(), 5, heartCrystals, this));
        }
        else if (level == 5) {
            nextWorld2Level(1, heartCrystals);
        }

    }
    public void nextWorld2Level(int level, int heartCrystals) throws FileNotFoundException, InterruptedException {

        World2Maps world2Maps = new World2Maps();

        if (level == 1) {
            mainPaneCampaign.setCenter(new World2Template(world2Maps.getLevel21(), 2, heartCrystals, this, false));
        }
        else if (level == 2) {
            mainPaneCampaign.setCenter(new World2Template(world2Maps.getLevel22(), 3, heartCrystals, this, false));
        }
        else if (level == 3) {
            mainPaneCampaign.setCenter(new World2Template(world2Maps.getLevel23(), 4, heartCrystals, this, false));
        }
        else if (level == 4) {
            mainPaneCampaign.setCenter(new World2Template(world2Maps.getLevel24(), 5, heartCrystals, this, false));
        }
        else if (level == 5) {
            mainPaneCampaign.setCenter(new World2Template(world2Maps.getLevel25(), 5, heartCrystals, this, true));
        }
    }
}
