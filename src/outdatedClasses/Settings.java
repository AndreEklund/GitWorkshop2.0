package outdatedClasses;

public class Settings {

    private int volume;
    private int difficulty;
    private boolean gameSoundsOn;

    public Settings(int volume, int difficulty, boolean gameSoundsOn){
        this.volume = volume;
        this.difficulty = difficulty;
        this.gameSoundsOn = gameSoundsOn;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isGameSoundsOn() {
        return gameSoundsOn;
    }

    public void setGameSoundsOn(boolean gameSoundsOn) {
        this.gameSoundsOn = gameSoundsOn;
    }
}
