package outdatedClasses;

import control.MainProgram;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import view.Menu.RightPanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author Sebastian Helin & Filip Örnling
 */


public class World2TemplateTest extends  GridPane  {

    private MainProgram mainProgram;
    private RightPanel rightPanel;
    private int[][] level;
    private ArrayList<Label> collectibles = new ArrayList<>();
    private MouseListener mouseListener = new MouseListener();
    private Image wall;
    private Image path;
    private Image border;
    private Image goal;
    private Image diamond;
    private Image start;
    private Image ghost;
    private Image Obs;
    private Image bridge;
    private Image bridge2;
    private Image heart;
    private boolean startButtonPressed;
    private boolean allCollectiblesObtained;
    private int collectiblesObtained = 0;
    private int squareSize;
    private int duration=10;
    private PathTransition animation;
    private PathTransition animation2;
    private PathTransition animation3;
    private PathTransition animation4;
    private PathTransition animation5;
    private PathTransition animation6;
    private ArrayList<Label>  obslist = new ArrayList();
    private Thread timer;
    private int currentLevel;
    private int heartCrystals;
    private boolean bossMap;
    private boolean gameStarted = false;
    private boolean startNotClickedOnce = true;



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

    private ImageView imageView = new ImageView();
    private ImageView bridgeView = new ImageView();
    private ImageView bridgeView2 = new ImageView();

    public World2TemplateTest(int[][] level, int currentLevel, int heartCrystals, MainProgram mainProgram, boolean bossMap, RightPanel rightPanel) throws FileNotFoundException, InterruptedException {
        this.mainProgram = mainProgram;
        this.currentLevel = currentLevel;
        this.level = level;
        this.heartCrystals = heartCrystals;
        rightPanel.changeHeartCounter(String.valueOf(heartCrystals));
        this.bossMap = bossMap;
        this.rightPanel = rightPanel;
        squareSize = 600/(level.length+2);
        setBackground();
        setupImages();
        setupBorders();
        setupLevel();
        if (bossMap) {
            setupGhost();
            initialize();
            buildBridge();
        }
        rightPanel.setSTARTTIME(15);
    }

    public void buildBridge() throws InterruptedException {
        timer = new Thread(task);

    }
    public void setupGhost() throws FileNotFoundException {
        ghost = new Image("file:files/ghost.png", squareSize, squareSize, false, false);

        imageView.setImage(ghost);

        imageView.setX(1);
        imageView.setY(1);
        imageView.setFitHeight(squareSize);
        imageView.setFitWidth(squareSize);

        imageView.setOnMouseEntered(e -> enteredWall(e));


        //add(imageView, 10, 10);

    }
    public void initialize() {

        ImageView ghost1V = new ImageView(ghost);
        ImageView ghost2V = new ImageView(ghost);
        ImageView ghost3V = new ImageView(ghost);
        ImageView ghost4V = new ImageView(ghost);
        ImageView ghost5V = new ImageView(ghost);
        ImageView ghost6V = new ImageView(ghost);

        Label ghost1VLabel = new Label();
        Label ghost2VLabel = new Label();
        Label ghost3VLabel = new Label();
        Label ghost4VLabel = new Label();
        Label ghost5VLabel = new Label();
        Label ghost6VLabel = new Label();

        ghost1VLabel.setGraphic(ghost1V);
        ghost2VLabel.setGraphic(ghost2V);
        ghost3VLabel.setGraphic(ghost3V);
        ghost4VLabel.setGraphic(ghost4V);
        ghost5VLabel.setGraphic(ghost5V);
        ghost6VLabel.setGraphic(ghost6V);

        ghost1V.setOnMouseEntered(e -> enteredGhost(e));
        ghost2V.setOnMouseEntered(e -> enteredGhost(e));
        ghost3V.setOnMouseEntered(e -> enteredGhost(e));
        ghost4V.setOnMouseEntered(e -> enteredGhost(e));
        ghost5V.setOnMouseEntered(e -> enteredGhost(e));
        ghost6V.setOnMouseEntered(e -> enteredGhost(e));

        add(ghost1V,10,0);
        add(ghost2V,9,0);
        add(ghost3V,8,0);
        add(ghost4V, 1, 0);
        add(ghost5V, 2, 0);
        add(ghost6V, 3, 0);

        Polyline line1 = new Polyline();
        Polyline line2 = new Polyline();
        Polyline line3 = new Polyline();
        Polyline line4 = new Polyline();
        Polyline line5 = new Polyline();
        Polyline line6 = new Polyline();

        line1.getPoints().addAll(
                16.0, -10.5,
                10.5, 650.0);
        line2.getPoints().addAll(
                15.0,-10.5,
                10.5,650.0);
        line3.getPoints().addAll(
                14.0,-10.5,
                10.5,650.0);
        line4.getPoints().addAll(
                16.0, -10.5,
                10.5, 650.0);
        line5.getPoints().addAll(
                15.0,-10.5,
                10.5,650.0);
        line6.getPoints().addAll(
                14.0,-10.5,
                10.5,650.0);



        animation3 = new PathTransition();
        animation3.setNode(ghost3V);
        animation3.setDuration(Duration.seconds(4));
        animation3.setCycleCount(Animation.INDEFINITE);
        animation3.setPath(line3);
        animation3.play();

        animation2 = new PathTransition();
        animation2.setNode(ghost2V);
        animation2.setDuration(Duration.seconds(3));
        animation2.setCycleCount(Animation.INDEFINITE);
        animation2.setPath(line2);
        animation2.play();

        animation = new PathTransition();
        animation.setNode(ghost1V);
        animation.setDuration(Duration.seconds(3.5));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.setPath(line1);
        animation.play();

        animation4 = new PathTransition();
        animation4.setNode(ghost4V);
        animation4.setDuration(Duration.seconds(3.5));
        animation4.setCycleCount(Animation.INDEFINITE);
        animation4.setPath(line4);
        animation4.play();

        animation5 = new PathTransition();
        animation5.setNode(ghost5V);
        animation5.setDuration(Duration.seconds(3));
        animation5.setCycleCount(Animation.INDEFINITE);
        animation5.setPath(line5);
        animation5.play();

        animation6 = new PathTransition();
        animation6.setNode(ghost6V);
        animation6.setDuration(Duration.seconds(4));
        animation6.setCycleCount(Animation.INDEFINITE);
        animation6.setPath(line6);
        animation6.play();

        Polyline line = new Polyline();
        line.getPoints().addAll(
                -100.0, -50.0,
                -50.0, 100.0,
                100.0, 200.0,
                200.0, -150.0);
        animation = new PathTransition();
        animation.setNode(imageView);
        animation.setDuration(Duration.seconds(duration));
        animation.setPath(line);
        animation.setCycleCount(PathTransition.INDEFINITE);
        animation.play();


        ghost1V.setOnMouseEntered(e -> enteredGhost(e));
        ghost2V.setOnMouseEntered(e -> enteredGhost(e));
        ghost3V.setOnMouseEntered(e -> enteredGhost(e));
        ghost4V.setOnMouseEntered(e -> enteredGhost(e));
        ghost5V.setOnMouseEntered(e -> enteredGhost(e));
        ghost6V.setOnMouseEntered(e -> enteredGhost(e));

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
                if (level[i][j] == 0){
                    add(getWall(),j + 1,i + 1);
                }
                else if (level[i][j] == 1) {
                    add(getPath(), j + 1, i + 1);
                }
                else if (level[i][j] == 2){
                    add(getStart(),j + 1,i + 1);
                }
                else if (level[i][j] == 3){
                    add(getGoal(),j + 1,i + 1);
                }
                else if(level[i][j] == 4){
                    add(getPath(), j + 1, i + 1);
                    add(addCollectible(), j + 1, i + 1);
                }
                else if (level[i][j] == 5) {
                    add(getObstacle(), j + 1, i + 1);
                }
                else if (level[i][j] == 7){
                    add(getPath(),j + 1,i + 1);
                    add(addHeartCrystal(),j + 1,i + 1);
                }
            }
        }
    }
    public void setupImages(){

        String folder = "underground";

        wall = new Image("file:files/" + folder + "/wall.png", squareSize, squareSize, false, false);
        path = new Image("file:files/" + folder + "/path.png", squareSize, squareSize, false, false);
        border = new Image("file:files/" + folder + "/border.png", squareSize, squareSize, false, false);
        goal = new Image("file:files/" + folder + "/goal.png", squareSize, squareSize, false, false);
        diamond = new Image("file:files/" + folder + "/collectible.png", squareSize, squareSize, false, false);
        start = new Image("file:files/" + folder + "/start.png", squareSize, squareSize, false, false);
        Obs = new Image("file:files/" + folder + "/border.png", squareSize, squareSize, false, false);
        bridge = new Image("file:files/floor.png", squareSize, squareSize, false, false);
        bridge2 = new Image("file:files/floor.png", squareSize, squareSize, false, false);
        heart = new Image("file:files/items/heart.png", squareSize, squareSize, false, false);
    }

    public Label getWall() {
        Label label = new Label();
        ImageView wallView = new ImageView(wall);
        wallView.setFitHeight(squareSize);
        wallView.setFitWidth(squareSize);
        label.setGraphic(wallView);
        //  label.setStyle("-fx-border-color: grey; ");
        label.setOnMouseEntered(e -> enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        return label;
    }
    private Label getObstacle(){
        Label label = new Label();
        ImageView obsView = new ImageView(Obs);
        obsView.setFitHeight(squareSize);
        obsView.setFitWidth(squareSize);
        label.setGraphic(obsView);
        //  label.setStyle("-fx-border-color: grey;");
        obslist.add(label);
        return label;
    }

    public void createBridge() throws InterruptedException {
        bridgeView.setImage(bridge);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                obslist.get(1).setGraphic(bridgeView);
            }
        });

    }
    public void createBridge1(){
        bridgeView2.setImage(bridge2);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                obslist.get(4).setGraphic(bridgeView2);
            }
        });

    }

    /*public void createBridge2(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                obslist.get(6).setGraphic(getBridge());
            }
        });

    }*/


    Task<Void> task = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            Thread.sleep(1000);
            /*createBridge2();
            Thread.sleep(500);*/
            if (allCollectiblesObtained = true) {
                createBridge1();
                Thread.sleep(1000);
                createBridge();
            }
            return null;
        }

    };

    private Label getBridge(){
        Label label = new Label();
        ImageView bridgeView = new ImageView(bridge);
        bridgeView.setFitHeight(squareSize);
        bridgeView.setFitWidth(squareSize);
        label.setGraphic(bridgeView);
        //   label.setStyle("-fx-border-color: grey;");
        return label;
    }

    private Label getPath() {
        Label label = new Label();
        ImageView pathView = new ImageView(path);
        pathView.setFitHeight(squareSize);
        pathView.setFitWidth(squareSize);
        label.setGraphic(pathView);
        //   label.setStyle("-fx-border-color: grey;");
        return label;
    }
    private Label getBorders() {
        Label label = new Label();
        ImageView borderView = new ImageView(border);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        //  label.setStyle("-fx-border-color: grey;");
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
        //  label.setStyle("-fx-border-color: grey;");
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
        //   label.setStyle("-fx-border-color: grey;");
        label.setOnMouseClicked(e -> startLevel());
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
        //  collectible.setStyle("-fx-border-color: grey; fx-background-color: transparent;");
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
        glow.setLevel(0.8);
        borderView.setEffect(glow);
        heartCrystal.setStyle("fx-background-color: transparent;");
        heartCrystal.setGraphic(borderView);
        heartCrystal.setOpacity(0.8);
        heartCrystal.setOnMouseEntered(e -> heartCrystalObtained(e));
        return heartCrystal;
    }
    private void heartCrystalObtained(MouseEvent e) {

        Label label = (Label)e.getSource();
        ImageView pathView = new ImageView(path);

        if (startButtonPressed) {
            label.setGraphic(pathView);
            heartCrystals++;
            rightPanel.changeHeartCounter(String.valueOf(heartCrystals));
        }
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
            rightPanel.changeHeartCounter(String.valueOf(heartCrystals));

            if (heartCrystals == 0) {
                gameOver();
            }
            deathPlayer.play();
            deathPlayer.seek(Duration.ZERO);
            startButtonPressed = false;
        }
    }

    public void enteredGhost(MouseEvent e){
        ImageView view = (ImageView) e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(view);
        fade.setDuration(Duration.seconds(1));
        fade.setFromValue(10);
        fade.setToValue(0.6);
        fade.setToValue(10);
        fade.play();

        if (startButtonPressed) {
            heartCrystals--;
            System.out.println("spöke träffat");
            rightPanel.changeHeartCounter(String.valueOf(heartCrystals));

            if (heartCrystals == 0) {
                gameOver();
            }
            deathPlayer.play();
            deathPlayer.seek(Duration.ZERO);
            startButtonPressed = false;
        }
    }

    private void gameOver() {
    }

    public void enteredGoal() throws FileNotFoundException, InterruptedException {
        if (startButtonPressed && allCollectiblesObtained) {
            goalPlayer.play();
            goalPlayer.seek(Duration.ZERO);
            mainProgram.nextWorld2Level(currentLevel, heartCrystals);

            rightPanel.pauseClock();

            gameStarted = true;
        }
    }

    public void startLevel() {

        if (!gameStarted){
            rightPanel.resumeClock();
            gameStarted = true;
        }else if (startNotClickedOnce){
            rightPanel.runClock();
        }

        startNotClickedOnce = false;
        startPlayer.play();
        startPlayer.seek(Duration.ZERO);
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



    public void rageMob(){
        Polyline line = new Polyline();
        line.getPoints().addAll(
                -100.0, -50.0,
                -50.0, 100.0,
                100.0, 200.0,
                200.0, -150.0);

        animation= new PathTransition();
        animation.setNode(imageView);
        animation.setDuration(Duration.seconds(duration/3));
        animation.setPath(line);
        animation.setCycleCount(PathTransition.INDEFINITE);
        animation.play();

    }

    private class MouseListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            if (startButtonPressed) {

                diamondPlayer.play();
                diamondPlayer.seek(Duration.ZERO);


                for (Label label: collectibles) {
                    if (e.getSource() == label) {
                        label.setVisible(false);
                        collectiblesObtained++;
                        if (collectiblesObtained == collectibles.size()) {
                            allCollectiblesObtained = true;
                            rageMob();
                        }
                    }
                }
                if (allCollectiblesObtained && bossMap){
                    timer.start();
                }
            }
        }
    }
}
