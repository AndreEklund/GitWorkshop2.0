package view;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MazeGenerator;

public class Main extends Application {

    private Stage mainWindow;
    private BorderPane rootTemplate;
    private BorderPane rootMapCreator;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        rootTemplate = new BorderPane();
        rootTemplate.setPrefSize(600,600);

        rootMapCreator = new BorderPane();
        rootMapCreator.setPrefSize(600,600);

        mainWindow = primaryStage;
        mainWindow.setTitle("Mazegen");
        String[][] level = {{"x","x","x","x","x"},
                            {"o","o","x","x","x"},
                            {"x","o","x","x","x"},
                            {"x","o","o","o","o"},
                            {"x","x","x","x","x"}};
        //Image goal = new Image(new FileInputStream("files/red.jpg"));
        //ImageCursor cursor = new ImageCursor(goal);
        MazeGenerator maze = new MazeGenerator(10);


        MapTemplate mapTemplate = new MapTemplate(maze.getMaze());





        MapCreator mapCreator = new MapCreator();
        OptionButtonPane obp = new OptionButtonPane(mapCreator);
        rootMapCreator.setCenter(mapCreator);
        rootMapCreator.setRight(obp);



        OptionButtonPane obp2 = new OptionButtonPane(mapCreator);
        rootTemplate.setCenter(mapTemplate);
        rootTemplate.setRight(obp2);

        Scene mapCreatorScene = new Scene(rootMapCreator);
        Scene levelScene = new Scene(rootTemplate);
        //mapTemplate.setCursor(cursor);

        VBox layout = new VBox();
        Button button1 = new Button("Level 1");
        button1.setOnAction(e -> mainWindow.setScene(levelScene));
        Button button2 = new Button("MapCreator");
        button2.setOnAction(e -> mainWindow.setScene(mapCreatorScene));
        layout.getChildren().addAll(button1,button2);
        Scene scene1 = new Scene(layout, 600, 500);

        mainWindow.setScene(scene1);
        mainWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
