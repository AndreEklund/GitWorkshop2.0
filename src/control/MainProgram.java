package control;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.GenerateNextLevel;
import model.MapCreator;
import view.MapTemplate;
import model.MazeGenerator;
import outdatedClasses.ForestLvlTemplate;
import outdatedClasses.LavaLvlTemplate;
import view.*;


import java.io.FileNotFoundException;

public class MainProgram extends Application {

    /**
     * Author André Eklund
     * Edit Filip Örnling, Viktor Näslund
     */
    private ForestLvlTemplate forestLvlTemplate;
    private Stage mainWindow;
    private BorderPane mainPane;
    private BorderPane rootMapCreator;
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


    @Override

    public void start(Stage primaryStage) throws Exception{

        //Menu och Intro scenes
        menu = new Menu(this);
        intro = new Intro(this);
        introScene = new Scene(intro, 800, 600);
        menuScene = new Scene(menu, 800, 600);

        //BorderPane för levels
        mainPane = new BorderPane();
        rootMapCreator = new BorderPane();

        mainWindow = primaryStage;

        mainWindow.setTitle("Mazegen");
        mainWindow.initStyle(StageStyle.UTILITY);
        mainWindow.setResizable(false);
        mainWindow.setOnCloseRequest(windowEvent -> System.exit(0));
        
        mazeGenerator = new MazeGenerator(10, true);
        generateNextLevel = new GenerateNextLevel(this, mainPane, mazeGenerator);


        mapTemplate = new MapTemplate(mazeGenerator.getMaze(),this, generateNextLevel);
        MapCreator mapCreator = new MapCreator();

        obp = new OptionButtonPane(mapCreator,this);
        rootMapCreator.setCenter(mapCreator);
        rootMapCreator.setRight(obp);
        obp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


        obp2 = new OptionButtonPane(mapCreator,this);
        obp2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        mainPane.setCenter(mapTemplate);
        mainPane.setRight(obp2);

        mapCreatorScene = new Scene(rootMapCreator, 800, 600);
        levelScene = new Scene(mainPane, 800, 600);


        mainWindow.setScene(introScene);
        mainWindow.show();
    }


    public void generateMobMaze() throws FileNotFoundException, InterruptedException {
        Mobmazelevel mobmazelevel = new Mobmazelevel();
        mainPane.setCenter(mobmazelevel);
    }

    public void changeToMapTemplate(){
        mainPane.setCenter(mapTemplate);
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
}
