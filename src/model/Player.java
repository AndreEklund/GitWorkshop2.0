package model;

public class Player {

    String Name;
    double Score;

    public  Player(String name, double score){
        this.Name=name;
        this.Score=score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(int score) {
        this.Score = score;
    }

    @Override
    public String toString() {
        return String.format("%s. &f2 points" ,Name,Score );
    }
}


//<<Allt nedanför ska vara i en controllerklass>>

 //