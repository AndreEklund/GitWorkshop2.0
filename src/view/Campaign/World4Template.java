package view.Campaign;

import control.MainProgram;
import view.AudioPlayer;
import view.Menu.RightPanel;

import java.io.FileNotFoundException;

public class World4Template extends World1Template {
    public World4Template(int[][] level, int currentLevel, int heartCrystals, MainProgram mainProgram, RightPanel rightPanel, int worldImage, AudioPlayer audioPlayer) throws FileNotFoundException {
        super(level, currentLevel, heartCrystals, mainProgram, rightPanel, worldImage, audioPlayer);
    }
}
