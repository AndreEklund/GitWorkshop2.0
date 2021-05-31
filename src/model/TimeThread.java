package model;

import view.Menu.RightPanel;

import java.sql.Time;

public class TimeThread extends Thread {

    private int seconds;
    private RightPanel panel;
    private boolean gameOver = false;

    public TimeThread(int seconds, RightPanel panel){
        this.seconds = seconds;
        this.panel = panel;
    }

    public void run() {
        while (!gameOver) {
            try {
                Thread.sleep(1000);
                if (seconds > 0) {
                    seconds--;
                }
                if (seconds == 5){
                    panel.fiveSecLeft();
                }
                if (seconds ==0) {
                    panel.startTask();
                    gameOver = true;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public int getSeconds() {
        return seconds;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
