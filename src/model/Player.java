package model;

public class Player {


    /**
     * Author Benny Petersson
     */

    String Name;
    int Score;

    public  Player(String name, int score){
        this.Name=name;
        this.Score=score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(int score) {
        this.Score = score;
    }

    @Override
    public String toString() {
        return String.format("%s %d", Name,Score );
    }
}


//<<Allt nedanför ska vara i en controllerklass>>

 //