package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.MazeGenerator;

import java.awt.*;

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

    @Override

    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        rootTemplate = new BorderPane();
        rootTemplate.setPrefSize(600,600);

        rootMapCreator = new BorderPane();
        rootMapCreator.setPrefSize(600,600);

        mainWindow = primaryStage;
        mainWindow.setTitle("Mazegen");

        MazeGenerator maze = new MazeGenerator(20);
        MazeGenerator maze1 = new MazeGenerator(20);
        MazeGenerator maze2 = new MazeGenerator(20);

        MapTemplate mapTemplate = new MapTemplate(maze.getMaze(),this);
        MapCreator mapCreator = new MapCreator();

        lavaLvlTemplate = new LavaLvlTemplate(maze1.getMaze(),this);
        forestLvlTemplate = new ForestLvlTemplate(maze2.getMaze(),this);



        OptionButtonPane obp = new OptionButtonPane(mapCreator);
        rootMapCreator.setCenter(mapCreator);
        rootMapCreator.setRight(obp);
        obp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


        OptionButtonPane obp2 = new OptionButtonPane(mapCreator);
        obp2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        rootTemplate.setCenter(mapTemplate);
        rootTemplate.setRight(obp2);

        Scene mapCreatorScene = new Scene(rootMapCreator);
        Scene levelScene = new Scene(rootTemplate);

        //Image goal = new Image(new FileInputStream("files/red.jpg"));
        //ImageCursor cursor = new ImageCursor(goal);
        //mapTemplate.setCursor(cursor);

        VBox layout = new VBox();
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Button button1 = new Button("Random Generated Maze");
        button1.setOnAction(e -> mainWindow.setScene(levelScene));
        Button button2 = new Button("MapCreator");
        button2.setOnAction(e -> mainWindow.setScene(mapCreatorScene));
        layout.getChildren().addAll(button1,button2);
        Scene scene1 = new Scene(layout, 800, 600);

        mainWindow.setScene(scene1);
        mainWindow.show();
    }

    public void changeToLava() {
        rootTemplate.setCenter(lavaLvlTemplate);
    }
    public void changeToForest(){
        rootTemplate.setCenter(forestLvlTemplate);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
