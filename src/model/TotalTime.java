package model;

public class TotalTime extends Thread{

    private boolean gameOver;
    private int time;

    public TotalTime(boolean gameOver){
        this.gameOver = gameOver;
    }

    public void run(){

        while(!gameOver){
            try {
                Thread.sleep(1000);
                time++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(time/60 + "" + time);
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
