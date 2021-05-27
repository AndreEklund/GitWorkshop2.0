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

    private File currentSong;
    private Media currentMedia;
    private MediaPlayer currentSongPlayer;

    private File pickAxeSound;
    private Media pickAxeMedia;
    private MediaPlayer pickAxeMediaPlayer;

    private File gameOverSound;
    private Media gameOverMedia;
    private MediaPlayer gameOverMediaPlayer;

    private File buttonClickSound;
    private Media buttonClickMedia;
    private MediaPlayer buttonClickedMediaPlayer;

    private File introSound;
    private Media introMedia;
    private MediaPlayer introMediaPlayer;

    private File worldIntroSound;
    private Media worldIntroMedia;
    private MediaPlayer worldIntroMediaPlayer;

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

        pickAxeSound = new File("files/sounds/pickaxe.mp3");
        pickAxeMedia = new Media(pickAxeSound.toURI().toString());
        pickAxeMediaPlayer = new MediaPlayer(pickAxeMedia);

        gameOverSound = new File("files/sounds/gameover.mp3");
        gameOverMedia = new Media(gameOverSound.toURI().toString());
        gameOverMediaPlayer = new MediaPlayer(gameOverMedia);

        buttonClickSound = new File("files/sounds/menubuttons.mp3");
        buttonClickMedia = new Media(buttonClickSound.toURI().toString());
        buttonClickedMediaPlayer = new MediaPlayer(buttonClickMedia);

        introSound = new File("files/sounds/introsound.mp3");
        introMedia = new Media(introSound.toURI().toString());
        introMediaPlayer = new MediaPlayer(introMedia);

        worldIntroSound = new File("files/sounds/nextworld.mp3");
        worldIntroMedia = new Media(worldIntroSound.toURI().toString());
        worldIntroMediaPlayer = new MediaPlayer(worldIntroMedia);

        currentSong = new File("files/music/Forest_test1.mp3");
        currentMedia = new Media(currentSong.toURI().toString());
        currentSongPlayer = new MediaPlayer(currentMedia);

    }

    public void playLevelMusic(String songToPlay) {

        String songName = songToPlay;

        if (songName.equals("Forest.test1")) {
            currentSong = new File("files/music/" + songName + ".mp3");
        }
        else if (songName.equals("LavaSong")) {
            currentSong = new File("files/music/" + songName + ".mp3");
        }
        else if (songName.equals("HeavenSong")) {
            currentSong = new File("files/music/" + songName + ".mp3");
        }
        else if (songName.equals("SpaceSong")) {
            currentSong = new File("files/music/" + songName + ".mp3");
        }
        currentMedia = new Media(currentSong.toURI().toString());
        currentSongPlayer = new MediaPlayer(currentMedia);
        currentSongPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                currentSongPlayer.seek(Duration.ZERO);
            }
        });
        currentSongPlayer.play();
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
    public void playPickAxeSound() {
        pickAxeMediaPlayer.play();
        pickAxeMediaPlayer.seek(Duration.ZERO);
    }
    public void playGameOverSound() {
        gameOverMediaPlayer.play();
        gameOverMediaPlayer.seek(Duration.ZERO);
    }
    public void muteSound(boolean mute) {
        breakableWallPlayer.setMute(mute);
        deathPlayer.setMute(mute);
        heartPlayer.setMute(mute);
        startPlayer.setMute(mute);
        goalPlayer.setMute(mute);
        diamondPlayer.setMute(mute);
        worldIntroMediaPlayer.setMute(mute);
    }
    public void playIntroMusic() {
        introMediaPlayer.play();
        introMediaPlayer.seek(Duration.ZERO);
    }
    public void stopMusic() {
        currentSongPlayer.stop();
        introMediaPlayer.stop();
    }
    public void playButtonSound() {
        buttonClickedMediaPlayer.play();
        buttonClickedMediaPlayer.seek(Duration.ZERO);
    }
    public void playWorldIntroSound() {
        worldIntroMediaPlayer.play();
        worldIntroMediaPlayer.seek(Duration.ZERO);
    }

    public void muteMusic(boolean mute){
        currentSongPlayer.setMute(mute);
    }
}
