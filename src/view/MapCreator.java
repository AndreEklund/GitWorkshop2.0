package view;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class MapCreator extends GridPane {


    private MouseListener mouseListener = new MouseListener();
    private StartButtonPressed startButtonPressed = new StartButtonPressed();
    private Label[][] labels = new Label[15][15];
    private Image wall = new Image(new FileInputStream("files/wall.jpg"));
    private Image path = new Image(new FileInputStream("files/floor.png"));
    private Image goal = new Image(new FileInputStream("files/red.jpg"));
    private Label lastLabelPressed;
    private Stage mainWindow;

    public MapCreator(Stage mainWindow) throws FileNotFoundException {
        this.mainWindow = mainWindow;
        initializeButtons();
        Button button = new Button("Start");
        button.setOnAction(startButtonPressed);
        add(button,15,0);
    }
    public void initializeButtons() {
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels.length; j++) {
                labels[i][j] = new Label();
                ImageView wallView = new ImageView(wall);
                wallView.setFitHeight(30);
                wallView.setFitWidth(30);
                labels[i][j].setGraphic(wallView);
                labels[i][j].addEventHandler(MouseEvent.MOUSE_PRESSED, mouseListener);
                labels[i][j].setOnMouseEntered(e -> enteredLabel(e));
                labels[i][j].setOnMouseExited(e -> exitedLabel(e));
                labels[i][j].setStyle("-fx-border-color: grey;");
                labels[i][j].setId("WALL");
                add(labels[i][j],i,j);
            }
        }
    }
    private class MouseListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

            if (e.getSource() == lastLabelPressed) {
                ImageView goalView = new ImageView(goal);
                goalView.setFitHeight(30);
                goalView.setFitWidth(30);
                lastLabelPressed.setGraphic(goalView);
                lastLabelPressed.setId("GOAL");
            }
            else {
                for (int i = 0; i < labels.length; i++) {
                    for (int j = 0; j < labels.length; j++) {
                        if (e.getSource() == labels[i][j]) {
                            ImageView pathView = new ImageView(path);
                            pathView.setFitHeight(30);
                            pathView.setFitWidth(30);
                            labels[i][j].setGraphic(pathView);
                            labels[i][j].setId("PATH");
                        }
                    }
                }
            }
            lastLabelPressed = (Label) e.getSource();
        }
    }
    private class StartButtonPressed implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            for (int i = 0; i < labels.length; i++) {
                for (int j = 0; j < labels.length; j++) {
                    if (labels[i][j].getId().equals("WALL")) {
                        labels[i][j].setOnMouseEntered(e -> enteredWall(e));
                    }
                    else if (labels[i][j].getId().equals("PATH")) {
                        labels[i][j].setOnMouseExited(null);
                        labels[i][j].setOnMouseEntered(null);
                    }
                    else if (labels[i][j].getId().equals("GOAL")) {
                        labels[i][j].setOnMouseEntered(e -> enteredGoal(e));
                    }
                    labels[i][j].removeEventHandler(MouseEvent.MOUSE_PRESSED, mouseListener);
                }
            }
        }
    }
    //Lägger till en highlight när håller cursorn över en ruta.
    public void enteredLabel(MouseEvent e) {
        Label label = (Label)e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(label);
        fade.setDuration(Duration.seconds(0.3));
        fade.setFromValue(10);
        fade.setToValue(0.6);
        fade.play();
    }
    //Får highlighten att försvinna när man tar bort cursorn från en ruta.
    private void exitedLabel(MouseEvent e) {
        Label label = (Label)e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(label);
        fade.setDuration(Duration.seconds(0.3));
        fade.setFromValue(0.6);
        fade.setToValue(10);
        fade.play();
    }
    public void enteredWall(MouseEvent e) {
        System.out.println("WALL");
    }
    public void enteredGoal(MouseEvent e) {
        System.out.println("GOAL");

    }

}
