package view;


import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class MapTemplate extends GridPane {


    /**
     * Author André Eklund
     */
    private Main main;
    private int[][] level;
    private ArrayList<Label> collectibles = new ArrayList<>();
    private MouseListener mouseListener = new MouseListener();
    private Image wall = new Image(new FileInputStream("files/wall.jpg"));
    private Image path = new Image(new FileInputStream("files/floor.jpg"));
    private Image border = new Image(new FileInputStream("files/floor.png"));
    private Image goal = new Image(new FileInputStream("files/red.jpg"));
    private Image diamond = new Image(new FileInputStream("files/diamond.png"));
    private boolean startButtonPressed;
    private boolean allCollectiblesObtained;
    private int collectiblesObtained = 0;
    private int width = 35;
    private int height = 35;

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
                    if (new Random().nextInt(5) == 4) {
                        add(addCollectible(),j + 1,i + 1);
                    }
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
        wallView.setFitHeight(width);
        wallView.setFitWidth(height);
        label.setGraphic(wallView);
        label.setStyle("-fx-border-color: grey; ");
        label.setOnMouseEntered(e -> enteredWall());
        return label;
    }
    private Label getPath() {
        Label label = new Label();
        ImageView pathView = new ImageView(path);
        pathView.setFitHeight(width);
        pathView.setFitWidth(height);
        label.setGraphic(pathView);
        label.setStyle("-fx-border-color: grey;");
        return label;
    }
    private Label getBorders() {
        Label label = new Label();
        ImageView borderView = new ImageView(border);
        borderView.setFitHeight(width);
        borderView.setFitWidth(height);
        label.setGraphic(borderView);
        label.setStyle("-fx-border-color: grey;");
        label.setOnMouseEntered(e -> enteredWall());
        return label;
    }
    private Label getGoal() {
        Label label = new Label();
        ImageView borderView = new ImageView(goal);
        borderView.setFitHeight(width);
        borderView.setFitWidth(height);
        label.setGraphic(borderView);
        label.setStyle("-fx-border-color: grey;");
        label.setOnMouseEntered(e -> enteredGoal());
        return label;
    }
    private Label getStart() {
        Label label = new Label();
        ImageView borderView = new ImageView();
        borderView.setFitHeight(width);
        borderView.setFitWidth(height);
        label.setGraphic(borderView);
        label.setStyle("-fx-border-color: grey;");
        label.setOnMouseClicked(e -> startButtonPressed());
        return label;
    }
    public Label addCollectible() {
        Label collectible = new Label();
        ImageView borderView = new ImageView(diamond);
        borderView.setFitHeight(width);
        borderView.setFitWidth(height);
        Glow glow = new Glow();
        glow.setLevel(0.7);
        borderView.setEffect(glow);
        collectible.setStyle("-fx-border-color: grey; fx-background-color: transparent;");
        collectible.setGraphic(borderView);
        collectible.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseListener);
        collectibles.add(collectible);
        return collectible;
    }
    public void enteredWall() {
        if (startButtonPressed) {
            System.out.println("wall");
        }
    }
    public void enteredGoal() {
        if (startButtonPressed && allCollectiblesObtained) {
            System.out.println("goal");
            main.changeToForest();
        }

    }
    public void startButtonPressed() {
        System.out.println("Start pressed");
        startButtonPressed = true;
    }

    private class MouseListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            if (startButtonPressed) {
                for (Label label: collectibles) {
                    if (e.getSource() == label) {
                        label.setVisible(false);
                        collectiblesObtained++;
                        if (collectiblesObtained == collectibles.size()) {
                            allCollectiblesObtained = true;
                        }
                    }
                }
            }
        }
    }
}
