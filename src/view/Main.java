package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MazeGenerator;
import outdatedClasses.ForestLvlTemplate;
import outdatedClasses.LavaLvlTemplate;


import java.io.FileNotFoundException;
import java.util.Random;

public class Main extends Application {

    /**
     * Author André Eklund
     * Edit Filip Örnling, Viktor Näslund
     */
    private ForestLvlTemplate forestLvlTemplate;
    private Stage mainWindow;
    private BorderPane rootTemplate;
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


    @Override

    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        //Menu och Intro scenes
        menu = new Menu(this);
        intro = new Intro(this);
        introScene = new Scene(intro, 800, 600);
        menuScene = new Scene(menu, 800, 600);

        rootTemplate = new BorderPane();
        //rootTemplate.setPrefSize(600,600);

        rootMapCreator = new BorderPane();
        //rootMapCreator.setPrefSize(600,600);

        mainWindow = primaryStage;

        mainWindow.setTitle("Mazegen");
        mainWindow.initStyle(StageStyle.UTILITY);
        mainWindow.setResizable(false);
        mainWindow.setOnCloseRequest(windowEvent -> System.exit(0));
        
        mazeGenerator = new MazeGenerator(6, true);



        mapTemplate = new MapTemplate(mazeGenerator.getMaze(),this);
        MapCreator mapCreator = new MapCreator();

        obp = new OptionButtonPane(mapCreator,this);
        rootMapCreator.setCenter(mapCreator);
        rootMapCreator.setRight(obp);
        obp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


        obp2 = new OptionButtonPane(mapCreator,this);
        obp2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        rootTemplate.setCenter(mapTemplate);
        rootTemplate.setRight(obp2);

        mapCreatorScene = new Scene(rootMapCreator, 800, 600);
        levelScene = new Scene(rootTemplate, 800, 600);
        //introScene = new Scene(intro);

        //Image goal = new Image(new FileInputStream("files/red.jpg"));
        //ImageCursor cursor = new ImageCursor(goal);
        //mapTemplate.setCursor(cursor);



        mainWindow.setScene(introScene);
        mainWindow.show();
    }

    public void generateNewMaze() throws FileNotFoundException {
        int currentMaze[][] = mazeGenerator.getMaze();
        MazeGenerator newMazegenerator = new MazeGenerator(6, false);
        int nextMaze[][] = newMazegenerator.getMaze();
        int row = 0;
        int col = 0;

        //Ändra goal till start i nästa labyrint
        for (int i = 0; i < currentMaze.length; i++) {
            for (int j = 0; j < currentMaze[i].length; j++) {
                if (currentMaze[i][j] == 3) {
                    nextMaze[i][j] = 2;
                }
                else if (currentMaze[i][j] == 2) {
                    col = j;
                }
            }
        }
        nextMaze[new Random().nextBoolean() ? 0 : nextMaze.length - 1][col] = 3;
        rootTemplate.setCenter(new MapTemplate(nextMaze, this));
        this.mazeGenerator = newMazegenerator;
    }
    public void generateMobMaze() throws FileNotFoundException, InterruptedException {
        Mobmazelevel mobmazelevel = new Mobmazelevel();
        rootTemplate.setCenter(mobmazelevel);
    }

   /* public void setStartScreen(){
        mainWindow.setScene(menuScene);
    }*/
    public void changeToMapTemplate(){
        rootTemplate.setCenter(mapTemplate);
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
