package com.company;

public class Player {

    String Name;
    double Score;

    public  Player(String name, double score){
        this.Name=name;
        this.Score=score;
    }


    @Override
    public String toString() {
        return String.format("%s. &f2" ,Name,Score );
    }
}
