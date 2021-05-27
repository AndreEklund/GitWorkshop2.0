package model;

public class TotalTime extends Thread{

    private boolean gameOver;
    private int seconds;
    private int minutes;

    public TotalTime(boolean gameOver){
        this.gameOver = gameOver;
    }

    public void run(){

        while(!gameOver){
            try {
                Thread.sleep(1000);
                seconds++;
                if (seconds==60){
                    minutes++;
                    seconds = 0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Total tid: " + minutes + ":" + seconds);
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
