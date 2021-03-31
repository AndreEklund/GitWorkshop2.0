package model;

public class Player {

    String Name;
    String Score;

    public  Player(String name, String score){
        this.Name=name;
        this.Score=score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        this.Score = score;
    }

    @Override
    public String toString() {
        return String.format("%s %s", Name,Score );
    }
}


//<<Allt nedanför ska vara i en controllerklass>>

 //