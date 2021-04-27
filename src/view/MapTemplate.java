package view;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MapTemplate extends GridPane {


    /**
     * Author André Eklund
     */
    private Main main;
    private int[][] level;
    private JLabel[] collectibles;
    private Image wall = new Image(new FileInputStream("files/wall.jpg"));
    private Image path = new Image(new FileInputStream("files/floor.jpg"));
    private Image border = new Image(new FileInputStream("files/floor.png"));
    private Image goal = new Image(new FileInputStream("files/red.jpg"));
    private boolean startButtonPressed;

    //Konstruktorn ska kunna ta emot int-arrayer och representera dem i GUIt
    public MapTemplate(int[][] level,Main main) throws FileNotFoundException {
        this.main = main;
        this.level = level;
        setupBorders();
        setupLevel();
        //Label[][] labels = new Label[20][10];
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
        wallView.setFitHeight(30);
        wallView.setFitWidth(30);
        label.setGraphic(wallView);
        label.setStyle("-fx-border-color: grey;");
        label.setOnMouseEntered(e -> enteredWall());
        return label;
    }
    private Label getPath() {
        Label label = new Label();
        ImageView pathView = new ImageView(path);
        pathView.setFitHeight(30);
        pathView.setFitWidth(30);
        label.setGraphic(pathView);
        label.setStyle("-fx-border-color: grey;");
        return label;
    }
    private Label getBorders() {
        Label label = new Label();
        ImageView borderView = new ImageView(border);
        borderView.setFitHeight(30);
        borderView.setFitWidth(30);
        label.setGraphic(borderView);
        label.setStyle("-fx-border-color: grey;");
        label.setOnMouseEntered(e -> enteredWall());
        return label;
    }
    private Label getGoal() {
        Label label = new Label();
        ImageView borderView = new ImageView(goal);
        borderView.setFitHeight(30);
        borderView.setFitWidth(30);
        label.setGraphic(borderView);
        label.setStyle("-fx-border-color: grey;");
        label.setOnMouseEntered(e -> enteredGoal());
        return label;
    }
    private Label getStart() {
        Label label = new Label();
        ImageView borderView = new ImageView();
        borderView.setFitHeight(30);
        borderView.setFitWidth(30);
        label.setGraphic(borderView);
        label.setStyle("-fx-border-color: grey;");
        label.setOnMouseClicked(e -> startButtonPressed());
        return label;
    }
    public void enteredWall() {
        if (startButtonPressed) {
            System.out.println("wall");
        }
    }
    public void enteredGoal() {
        if (startButtonPressed) {
            System.out.println("goal");
            main.changeToForest();
        }

    }
    public void startButtonPressed() {
        System.out.println("Start pressed");
        startButtonPressed = true;
    }

}
