package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.DigitalClock;
import model.MazeGenerator;



import java.awt.*;
import java.text.DecimalFormat;

public class Main extends Application {

    /**
     * Author André Eklund
     * Edit Filip Örnling
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


    @Override

    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        //Menu och Intro scenes
        menu = new Menu(this);
        intro = new Intro(this);
        introScene = new Scene(intro, 800, 600);
        menuScene = new Scene(menu, 800, 600);

        rootTemplate = new BorderPane();
        rootTemplate.setPrefSize(600,600);

        rootMapCreator = new BorderPane();
        rootMapCreator.setPrefSize(600,600);

        mainWindow = primaryStage;
        mainWindow.setTitle("Mazegen");

        MazeGenerator maze = new MazeGenerator(20);
        MazeGenerator maze1 = new MazeGenerator(20);
        MazeGenerator maze2 = new MazeGenerator(20);

        mapTemplate = new MapTemplate(maze.getMaze(),this);
        MapCreator mapCreator = new MapCreator();

        lavaLvlTemplate = new LavaLvlTemplate(maze1.getMaze(),this);
        forestLvlTemplate = new ForestLvlTemplate(maze2.getMaze(),this);



        obp = new OptionButtonPane(mapCreator,this);
        rootMapCreator.setCenter(mapCreator);
        rootMapCreator.setRight(obp);
        obp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


        obp2 = new OptionButtonPane(mapCreator,this);
        obp2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        rootTemplate.setCenter(mapTemplate);
        rootTemplate.setRight(obp2);

        mapCreatorScene = new Scene(rootMapCreator);
        levelScene = new Scene(rootTemplate);
        //introScene = new Scene(intro);

        //Image goal = new Image(new FileInputStream("files/red.jpg"));
        //ImageCursor cursor = new ImageCursor(goal);
        //mapTemplate.setCursor(cursor);



        mainWindow.setScene(introScene);
        mainWindow.show();
    }

    public void changeToLava() {
        rootTemplate.setCenter(lavaLvlTemplate);
        obp2.changeLvl3();
    }
    public void changeToForest(){
        rootTemplate.setCenter(forestLvlTemplate);
        obp2.changeLvl2();
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
