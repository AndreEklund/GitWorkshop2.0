package view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class AudioPlayer {

    private File diamondSound;
    private Media diamondMedia;
    private MediaPlayer diamondPlayer;

    private File deathSound;
    private Media deathMedia;
    private MediaPlayer deathPlayer;


    private File startSound;
    private Media startMedia;
    private MediaPlayer startPlayer;

    private File goalSound;
    private Media goalMedia;
    private MediaPlayer goalPlayer;

    private File heartSound;
    private Media heartMedia;
    private MediaPlayer heartPlayer;

    private File breakableWallSound;
    private Media breakableWallMedia;
    private MediaPlayer breakableWallPlayer;

    public AudioPlayer() {
        setupAudioFiles();
    }
    public void setupAudioFiles() {

        diamondSound = new File("files/sounds/Diamond1.mp3");
        diamondMedia = new Media(diamondSound.toURI().toString());
        diamondPlayer = new MediaPlayer(diamondMedia);

        deathSound = new File("files/sounds/MazegenDeath.mp3");
        deathMedia = new Media(deathSound.toURI().toString());
        deathPlayer = new MediaPlayer(deathMedia);

        startSound = new File("files/sounds/MazegenStart.mp3");
        startMedia = new Media(startSound.toURI().toString());
        startPlayer = new MediaPlayer(startMedia);

        goalSound = new File("files/sounds/MazegenGoal.mp3");
        goalMedia = new Media(goalSound.toURI().toString());
        goalPlayer = new MediaPlayer(goalMedia);

        heartSound = new File("files/sounds/Heart.mp3");
        heartMedia = new Media(heartSound.toURI().toString());
        heartPlayer = new MediaPlayer(heartMedia);

        breakableWallSound = new File("files/sounds/AxeUsed.mp3");
        breakableWallMedia = new Media(breakableWallSound.toURI().toString());
        breakableWallPlayer = new MediaPlayer(breakableWallMedia);
    }
    public void playCollectibleSound() {
        diamondPlayer.play();
        diamondPlayer.seek(Duration.ZERO);
    }
    public void playDeathSound() {
        deathPlayer.play();
        deathPlayer.seek(Duration.ZERO);
    }
    public void playStartSound() {
        startPlayer.play();
        startPlayer.seek(Duration.ZERO);
    }
    public void playGoalSound() {
        goalPlayer.play();
        goalPlayer.seek(Duration.ZERO);
    }
    public void playHeartSound() {
        heartPlayer.play();
        heartPlayer.seek(Duration.ZERO);
    }
    public void playBreakableWallSound() {
        breakableWallPlayer.play();
        breakableWallPlayer.seek(Duration.ZERO);
    }
    public void muteSound(boolean mute) {
        breakableWallPlayer.setMute(mute);
        deathPlayer.setMute(mute);
        heartPlayer.setMute(mute);
        startPlayer.setMute(mute);
        goalPlayer.setMute(mute);
        diamondPlayer.setMute(mute);
    }
}
