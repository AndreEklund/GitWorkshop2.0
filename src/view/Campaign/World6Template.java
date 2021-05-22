package view.Campaign;

import control.MainProgram;
import view.Menu.RightPanel;

import java.io.FileNotFoundException;

public class World6Template extends World1Template {
    public World6Template(int[][] level, int currentLevel, int heartCrystals, MainProgram mainProgram, RightPanel rightPanel, int worldImage) throws FileNotFoundException {
        super(level, currentLevel, heartCrystals, mainProgram, rightPanel, worldImage);
    }
}
