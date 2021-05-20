package model.MazeGeneration;

import control.MainProgram;
import javafx.scene.layout.BorderPane;
import view.MapTemplate;
import view.Menu.RightPanel;

import java.io.FileNotFoundException;
import java.util.Random;

public class GenerateNextLevel {

    private MazeGenerator mazeGenerator;
    private BorderPane mainPane;
    private MainProgram mainProgram;
    private RightPanel rightPanel;

    public GenerateNextLevel(MainProgram mainProgram, BorderPane mainPane, MazeGenerator mazeGenerator, RightPanel rightPanel){
        this.mazeGenerator = mazeGenerator;
        this.mainProgram = mainProgram;
        this.mainPane = mainPane;
        this.rightPanel = rightPanel;
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
        mainPane.setCenter(new MapTemplate(checkStartAndGoalNeighbors(nextMaze), mainProgram, this));
        this.mazeGenerator = newMazegenerator;
    }
    public int[][] checkStartAndGoalNeighbors(int[][] maze) {

        int wallCounterStart = 0;
        int wallCounterGoal = 0;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {

                if (maze[i][j] == 2) {
                    for (int offsetRow = i - 1; offsetRow <= i + 1; offsetRow++){
                        for (int offsetCol = j - 1; offsetCol <= j + 1; offsetCol++){

                            if ((offsetRow >= 0) && (offsetRow < maze.length)) {

                                if ((offsetCol >= 0) && (offsetCol < maze[0].length)) {
                                    if (maze[offsetRow][offsetCol] == 0) {
                                        if((i == 0) && (j == 0)){
                                            if((offsetRow!=1 && offsetCol!=1) || (offsetRow!=1 && offsetCol != maze.length-1) ||(offsetRow!=maze.length-1 && offsetCol!=1) || (offsetRow!=maze.length-1 && offsetCol!=maze.length-1)){
                                                wallCounterStart++;
                                            }
                                        }
                                    }
                                    else if (wallCounterStart >= 2) {
                                        for (int offsetRow2 = offsetRow - 1; offsetRow2 <= offsetRow + 1; offsetRow2++){
                                            for (int offsetCol2 = offsetCol - 1; offsetCol2 <= offsetCol + 1; offsetCol2++){
                                                if ((offsetRow2 >= 0) && (offsetRow2 < maze.length)) {
                                                    if ((offsetCol2 >= 0) && (offsetCol2 < maze[0].length)) {
                                                        if (maze[offsetRow2][offsetCol2] == 0) {
                                                            maze[offsetRow2][offsetCol2] = 1;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else if (maze[i][j] == 3) {
                    for (int offsetRow = i - 1; offsetRow <= i + 1; offsetRow++){
                        for (int offsetCol = j - 1; offsetCol <= j + 1; offsetCol++){

                            if ((offsetRow >= 0) && (offsetRow < maze.length)) {
                                if ((offsetCol >= 0) && (offsetCol < maze[0].length)) {
                                    if (maze[offsetRow][offsetCol] == 0) {
                                        if((offsetRow!=1 && offsetCol!=1) || (offsetRow!=1 && offsetCol != maze.length-1) ||(offsetRow!=maze.length-1 && offsetCol!=1) || (offsetRow!=maze.length-1 && offsetCol!=maze.length-1)){
                                            wallCounterGoal++;
                                        }
                                    }
                                    else if (wallCounterGoal >= 2) {
                                        for (int offsetRow2 = offsetRow - 1; offsetRow2 <= offsetRow + 1; offsetRow2++){
                                            for (int offsetCol2 = offsetCol - 1; offsetCol2 <= offsetCol + 1; offsetCol2++){
                                                if ((offsetRow2 >= 0) && (offsetRow2 < maze.length)) {
                                                    if ((offsetCol2 >= 0) && (offsetCol2 < maze[0].length)) {
                                                        if (maze[offsetRow2][offsetCol2] == 0) {
                                                            maze[offsetRow2][offsetCol2] = 1;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return maze;
    }
}
