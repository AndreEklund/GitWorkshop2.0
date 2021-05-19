package view.Campaign;


import control.MainProgram;
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
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class World1Template extends GridPane {


    /**
     * Author André Eklund
     */
    private MainProgram mainProgram;
    private int[][] level;
    private ArrayList<Label> collectibles = new ArrayList<>();
    private ArrayList<Label> pickaxes = new ArrayList<>();
    private MouseListener mouseListener = new MouseListener();
    private Image wall;
    private Image path;
    private Image border;
    private Image goal;
    private Image diamond;
    private Image start;
    private Image ghost;
    private Image heart;
    private boolean startButtonPressed;
    private boolean allCollectiblesObtained;
    private boolean wallDestroyed;
    private int collectiblesObtained = 0;
    private int squareSize;
    private int currentLevel = 0;
    private int heartCrystals = 0;
    private Image pickAxeImage;
    private boolean pickaxeObtained;
    private ImageView imageView = new ImageView();


    private File diamondSound = new File("files/sounds/Diamond1.mp3");
    private Media diamondMedia = new Media(diamondSound.toURI().toString());
    private MediaPlayer diamondPlayer = new MediaPlayer(diamondMedia);

    private File deathSound = new File("files/sounds/MazegenDeath.mp3");
    private Media deathMedia = new Media(deathSound.toURI().toString());
    private MediaPlayer deathPlayer = new MediaPlayer(deathMedia);


    private File startSound = new File("files/sounds/MazegenStart.mp3");
    private Media startMedia = new Media(startSound.toURI().toString());
    private MediaPlayer startPlayer = new MediaPlayer(startMedia);

    private File goalSound = new File("files/sounds/MazegenGoal.mp3");
    private Media goalMedia = new Media(goalSound.toURI().toString());
    private MediaPlayer goalPlayer = new MediaPlayer(goalMedia);

    //Konstruktorn ska kunna ta emot int-arrayer och representera dem i GUIt
    public World1Template(int[][] level, int currentLevel, int heartCrystals, MainProgram mainProgram) throws FileNotFoundException {
        this.mainProgram = mainProgram;
        this.currentLevel = currentLevel;
        this.level = level;
        this.heartCrystals = heartCrystals;
        squareSize = 600/(level.length+2);
        setBackground();
        setupImages();
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
                else if (level[i][j] == 4){
                    add(getPath(),j + 1,i + 1);
                    add(addCollectible(),j + 1,i + 1);
                }
                else if (level[i][j] == 5){
                    add(getPath(),j + 1,i + 1);
                    add(addPickAxe(),j + 1,i + 1);
                }
                else if (level[i][j] == 6){
                    add(getBreakableWall(),j + 1,i + 1);
                }
                else if (level[i][j] == 7){
                    add(getPath(),j + 1,i + 1);
                    add(addHeartCrystal(),j + 1,i + 1);
                }
            }
        }
    }
    public void setupImages(){
        wall = new Image("file:files/forest/wall.png", squareSize, squareSize, false, false);
        path = new Image("file:files/forest/path.png", squareSize, squareSize, false, false);
        border = new Image("file:files/forest/border.png", squareSize, squareSize, false, false);
        goal = new Image("file:files/forest/goal.png", squareSize, squareSize, false, false);
        diamond = new Image("file:files/forest/collectible.png", squareSize, squareSize, false, false);
        start = new Image("file:files/forest/start.png", squareSize, squareSize, false, false);
        pickAxeImage = new Image("file:files/items/pickaxe.png", squareSize, squareSize, false, false);
        heart = new Image("file:files/items/heart.png", squareSize, squareSize, false, false);
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
    private Label getBreakableWall() {
        Label label = new Label();
        ImageView borderView = new ImageView(border);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setOnMouseEntered(e -> enteredBreakableWall(e));
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
    public Label addHeartCrystal() {
        Label heartCrystal = new Label();
        ImageView borderView = new ImageView(heart);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.5);
        borderView.setEffect(glow);
        heartCrystal.setStyle("fx-background-color: transparent;");
        heartCrystal.setGraphic(borderView);
        heartCrystal.setOnMouseEntered(e -> heartCrystalObtained(e));
        return heartCrystal;
    }

    private void heartCrystalObtained(MouseEvent e) {

        Label label = (Label)e.getSource();
        ImageView pathView = new ImageView(path);

        if (startButtonPressed) {
            label.setGraphic(pathView);
            heartCrystals++;
        }
    }

    public Label addPickAxe() {
        Label pickAxe = new Label();
        ImageView borderView = new ImageView(pickAxeImage);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.7);
        borderView.setEffect(glow);
        pickAxe.setStyle("fx-background-color: transparent;");
        pickAxe.setGraphic(borderView);
        pickAxe.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseListener);
        pickaxes.add(pickAxe);
        return pickAxe;
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

            heartCrystals--;
            System.out.println("Hearts left: " + heartCrystals);

            if (heartCrystals == 0) {
                gameOver();
            }
            deathPlayer.play();
            deathPlayer.seek(Duration.ZERO);
            startButtonPressed = false;
        }
    }

    private void gameOver() {
        System.out.println("Game over!!!!");
    }

    public void enteredGoal() throws FileNotFoundException, InterruptedException {
        if (startButtonPressed && allCollectiblesObtained) {
            goalPlayer.play();
            goalPlayer.seek(Duration.ZERO);
            mainProgram.nextWorld1Level(currentLevel, heartCrystals);
        }
    }
    public void startButtonPressed() {

        startPlayer.play();
        startPlayer.seek(Duration.ZERO);
        startButtonPressed = true;
    }
    public void exitedLabel(MouseEvent e) {
        Label label = (Label)e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(label);
        fade.setDuration(Duration.seconds(0.3));
        fade.setFromValue(0.6);
        fade.setToValue(10);
        fade.play();
    }
    public void enteredBreakableWall(MouseEvent e) {

        Label label = (Label)e.getSource();
        ImageView pathView = new ImageView(path);

        if (startButtonPressed) {

            if (pickaxeObtained) {
                label.setGraphic(pathView);
                pickaxeObtained = false;
                wallDestroyed = true;
            }
            else if (!wallDestroyed) {
                enteredWall(e);
            }
        }
    }

    private class MouseListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            if (startButtonPressed) {

                diamondPlayer.play();
                diamondPlayer.seek(Duration.ZERO);

                for (Label label : pickaxes){
                    if (e.getSource()== label){
                        label.setVisible(false);
                        pickaxeObtained = true;
                    }
                }

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
