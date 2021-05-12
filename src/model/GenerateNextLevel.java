package model;

import control.MainProgram;
import javafx.scene.layout.BorderPane;
import view.MapTemplate;

import java.io.FileNotFoundException;
import java.util.Random;

public class GenerateNextLevel {

    private MazeGenerator mazeGenerator;
    private BorderPane mainPane;
    private MainProgram mainProgram;

    public GenerateNextLevel(MainProgram mainProgram, BorderPane mainPane){
        mazeGenerator = new MazeGenerator(10, true);
        this.mainProgram = mainProgram;
        this.mainPane = mainPane;
    }


    public void generateNewMaze() throws FileNotFoundException {
        int currentMaze[][] = mazeGenerator.getMaze();
        MazeGenerator newMazegenerator = new MazeGenerator(10, false);
        int nextMaze[][] = newMazegenerator.getMaze();
        int row = 0;
        int col = 0;

        //Ändra goal till start i nästa labyrint
        for (int i = 0; i < currentMaze.length; i++) {
            for (int j = 0; j < currentMaze[i].length; j++) {
                if (currentMaze[i][j] == 3) {
                    nextMaze[i][j] = 2;
                }
                else if (currentMaze[i][j] == 2) {
                    col = j;
                }
            }
        }
        nextMaze[new Random().nextBoolean() ? 0 : nextMaze.length - 1][col] = 3;
        mainPane.setCenter(new MapTemplate(nextMaze, mainProgram, this));
        this.mazeGenerator = newMazegenerator;
    }
}
