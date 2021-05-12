package outdatedClasses;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import control.MainProgram;

import javax.swing.*;
import java.io.FileNotFoundException;

public class LavaLvlTemplate extends GridPane {


    /**
     * Edit Filip Örnling
     */
    private MainProgram mainProgram;
    private int[][] level;
    private JLabel[] collectibles;
    private Image wall;// = new Image(new FileInputStream("files/lavavägg.png"));
    private Image path;// = new Image(new FileInputStream("files/Obsidian.png"));
    private Image border;// = new Image(new FileInputStream("files/floor.png"));
    private Image goal;// = new Image(new FileInputStream("files/red.jpg"));
    private boolean startButtonPressed;
    private int squareSize;

    //Konstruktorn ska kunna ta emot int-arrayer och representera dem i GUIt
    public LavaLvlTemplate(int[][] level, MainProgram mainProgram) throws FileNotFoundException {
        this.mainProgram = mainProgram;
        this.level = level;
        squareSize = 568/(level.length+2);
        setBackground();
        setupImages();
        setupBorders();
        setupLevel();
        //Label[][] labels = new Label[20][10];
    }
    public void setBackground(){
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
    }

    public void setupImages(){
        wall = new Image("file:files/lavavägg.png", squareSize, squareSize, false, false);
        path = new Image("file:files/Obsidian.png", squareSize, squareSize, false, false);
        border = new Image("file:files/floor.png", squareSize, squareSize, false, false);
        goal = new Image("file:files/red.jpg", squareSize, squareSize, false, false);
    }
    public void setupBorders() {
        for (int i = 0; i < level.length + 1; i++) {
            add(getBorders(), i, 0);
        }
        for (int i = 0; i < level.length + 1; i++) {
            add(getBorders(), 0, i);
        }
        for (int i = 0; i < level.length + 2; i++) {
            add(getBorders(), i, level.length + 1);
        }
        for (int i = 0; i < level.length + 2; i++) {
            add(getBorders(), level.length + 1, i);
        }
    }
    public void setupLevel() {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level.length; j++) {

                if (level[i][j] == 1) {
                    add(getPath(),j + 1,i + 1);

                }
                else if (level[i][j] == 0){
                    add(getWall(),j + 1,i + 1);
                }
                else if (level[i][j] == 2){
                    add(getStart(),j + 1,i + 1);
                }
                else if (level[i][j] == 3){
                    add(getGoal(),j + 1,i + 1);
                }
            }
        }
    }

    public Label getWall() {
        Label label = new Label();
        ImageView wallView = new ImageView(wall);
        wallView.setFitHeight(squareSize);
        wallView.setFitWidth(squareSize);
        label.setGraphic(wallView);
        label.setStyle("-fx-border-color: grey;");
        label.setOnMouseEntered(e -> enteredWall());
        return label;
    }
    private Label getPath() {
        Label label = new Label();
        ImageView pathView = new ImageView(path);
        pathView.setFitHeight(squareSize);
        pathView.setFitWidth(squareSize);
        label.setGraphic(pathView);
        label.setStyle("-fx-border-color: grey;");
        return label;
    }
    private Label getBorders() {
        Label label = new Label();
        ImageView borderView = new ImageView(border);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setStyle("-fx-border-color: grey;");
        label.setOnMouseEntered(e -> enteredWall());
        return label;
    }
    private Label getGoal() {
        Label label = new Label();
        ImageView borderView = new ImageView(goal);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setStyle("-fx-border-color: grey;");
        label.setOnMouseEntered(e -> enteredGoal());
        return label;
    }
    private Label getStart() {
        Label label = new Label();
        ImageView borderView = new ImageView();
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setStyle("-fx-border-color: grey;");
        label.setOnMouseClicked(e -> startButtonPressed());
        return label;
    }
    public void enteredWall() {
        if (startButtonPressed) {
            //System.out.println("wall");
            //main.changeToForest();
        }
    }
    public void enteredGoal() {
        if (startButtonPressed) {
            System.out.println("goal");
        }

    }
    public void startButtonPressed() {
        System.out.println("Start pressed");
        startButtonPressed = true;
    }
}
