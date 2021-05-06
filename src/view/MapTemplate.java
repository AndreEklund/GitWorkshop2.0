package view;


import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import javafx.scene.media.Media;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    private Image wall;// = new Image(new FileInputStream("files/wall.jpg"));
    private Image path;// = new Image(new FileInputStream("files/floor.jpg"));
    private Image border;// = new Image(new FileInputStream("files/floor.png"));
    private Image goal;// = new Image(new FileInputStream("files/red.jpg"));
    private Image diamond;// = new Image(new FileInputStream("files/diamond.png"));
    private Image start;
    private Image ghost;
    private boolean startButtonPressed;
    private boolean allCollectiblesObtained;
    private int collectiblesObtained = 0;
    private int squareSize;

    private File audioFile = new File("files/sounds/Diamond1.mp3");
    private Media audio = new Media(audioFile.toURI().toString());
    private MediaPlayer audioPlayer = new MediaPlayer(audio);
    private ImageView imageView = new ImageView();

    //Konstruktorn ska kunna ta emot int-arrayer och representera dem i GUIt
    public MapTemplate(int[][] level, Main main) throws FileNotFoundException {
        this.main = main;
        this.level = level;
        squareSize = 600/(level.length+2);
        setBackground();
        setupImages(new Random().nextInt(4));
        setupBorders();
        setupLevel();
        //setupGhost();
    }
    public void setupGhost() throws FileNotFoundException {
        ghost = new Image("file:files/ghost.png", squareSize, squareSize, false, false);

        imageView.setImage(ghost);

        imageView.setX(1);
        imageView.setY(1);
        imageView.setFitHeight(squareSize);
        imageView.setFitWidth(squareSize);

        imageView.setOnMouseEntered(e -> enteredWall(e));


        add(imageView, 10, 10);
        initialize();
    }
    public void initialize() {

        Polyline line = new Polyline();
        line.getPoints().addAll(
                -100.0, -50.0,
                -50.0, 100.0,
                100.0, 200.0,
                200.0, -150.0);

        PathTransition path = new PathTransition();
        path.setNode(imageView);
        path.setDuration(Duration.seconds(10));
        path.setPath(line);
        path.setCycleCount(PathTransition.INDEFINITE);
        path.play();
    }
    public void setBackground(){
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
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
    public void setupImages(int value){

        String folder = "";

        if (value == 0) {
            folder = "forest";
        }
        else if (value == 1) {
            folder = "lava";
        }
        else if (value == 2) {
            folder = "underground";
        }
        else if(value == 3) {
            folder = "cloud";
        }
        wall = new Image("file:files/" + folder + "/wall.png", squareSize, squareSize, false, false);
        path = new Image("file:files/" + folder + "/path.png", squareSize, squareSize, false, false);
        border = new Image("file:files/" + folder + "/border.png", squareSize, squareSize, false, false);
        goal = new Image("file:files/" + folder + "/goal.png", squareSize, squareSize, false, false);
        diamond = new Image("file:files/" + folder + "/collectible.png", squareSize, squareSize, false, false);
        start = new Image("file:files/" + folder + "/start.png", squareSize, squareSize, false, false);
    }

    public Label getWall() {
        Label label = new Label();
        ImageView wallView = new ImageView(wall);
        wallView.setFitHeight(squareSize);
        wallView.setFitWidth(squareSize);
        label.setGraphic(wallView);
        //label.setStyle("-fx-border-color: grey; ");
        label.setOnMouseEntered(e -> enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        return label;
    }
    private Label getPath() {
        Label label = new Label();
        ImageView pathView = new ImageView(path);
        pathView.setFitHeight(squareSize);
        pathView.setFitWidth(squareSize);
        label.setGraphic(pathView);
        //label.setStyle("-fx-border-color: grey;");
        return label;
    }
    private Label getBorders() {
        Label label = new Label();
        ImageView borderView = new ImageView(border);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        //label.setStyle("-fx-border-color: grey;");
        label.setOnMouseEntered(e -> enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        return label;
    }
    private Label getGoal() {
        Label label = new Label();
        ImageView borderView = new ImageView(goal);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        //label.setStyle("-fx-border-color: grey;");
        label.setOnMouseEntered(e -> {
            try {
                enteredGoal();
            } catch (FileNotFoundException | InterruptedException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        return label;
    }
    private Label getStart() {
        Label label = new Label();
        ImageView borderView = new ImageView(start);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        //label.setStyle("-fx-border-color: grey;");
        label.setOnMouseClicked(e -> startButtonPressed());
        return label;
    }
    public Label addCollectible() {
        Label collectible = new Label();
        ImageView borderView = new ImageView(diamond);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.7);
        borderView.setEffect(glow);
        collectible.setStyle("fx-background-color: transparent;");
        collectible.setGraphic(borderView);
        collectible.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseListener);
        collectibles.add(collectible);
        return collectible;
    }
    public void enteredWall(MouseEvent e) {
        Label label = (Label)e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(label);
        fade.setDuration(Duration.seconds(0.3));
        fade.setFromValue(10);
        fade.setToValue(0.6);
        fade.play();

        if (startButtonPressed) {
            startButtonPressed = false;
        }
    }
    public void enteredGoal() throws FileNotFoundException, InterruptedException {
        if (startButtonPressed && allCollectiblesObtained) {
            //main.generateNewMaze();
            main.generateMobMaze();
        }
    }
    public void startButtonPressed() {
        startButtonPressed = true;
    }
    private void exitedLabel(MouseEvent e) {
        Label label = (Label)e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(label);
        fade.setDuration(Duration.seconds(0.3));
        fade.setFromValue(0.6);
        fade.setToValue(10);
        fade.play();
    }

    private class MouseListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            if (startButtonPressed) {

               audioPlayer.play();
               audioPlayer.seek(Duration.ZERO);

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
