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

 // private List<Player> players = new ArrayList<>();// lista med spelare som vunnit
 //     private int numWinners=0;// Antalet personer som vunnit.

 // players.add(new Player(winner, countTotalGuess)); // addera spelarens namn med string och dens antal gissningar(poäng) till array av vinnare.
 //                 numWinners++;// incrementera antalet vinnare.
 //
 //
 //                 Collections.sort(players, new Comparator<Player>() {// Sortera vinnarna med varandra för att se vem som har bäst score. Sätt den längst upp.
 //                     @Override
 //                     public int compare(Player o1, Player o2) {
 //                         return o2.getScore() > o1.getScore() ? -1 : 1;
 //                     }
 //                 });
 //
 //                if (numWinners>10) {// Om fler än 10 spelare efter sortering. Ta bort sista spelaren ur listan.
 //                    players.remove(10);
 //                }
 //                 view.updateArray();// updatera listan som syns på vinnare i JList.
 //             }
 //
 //         }
