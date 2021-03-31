package controller;

import com.company.StopWatch;
import model.*;

import javax.swing.*;
import java.util.*;

public class Controller {

    private StopWatch timer;
    private Player player;

    private List<Player> players = new ArrayList<>();// lista med spelare som vunnit
    private int numWinners = 0;// Antalet personer som vunnit.

    public Controller(){
        timer = new StopWatch();
        player = new Player("", "");
    }


    public void HighScoreList(String time, String winner) { // Klassen ska aktiveras när någon har vunnit spelet. länk från den listenern.

         // ta in namn och tid på vinnaren

        players.add(new Player(winner, time)); // addera spelarens namn med string och dens antal gissningar(poäng) till array av vinnare.
        numWinners++;// incrementera antalet vinnare.
        System.out.println(numWinners);

        Collections.sort(players, new Comparator<Player>() {// Sortera vinnarna med varandra för att se vem som har bäst score. Sätt den längst upp.
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.parseInt(o2.getScore()) > Integer.parseInt(o1.getScore()) ? -1 : 1;
            }
        });

        if (numWinners > 10) {// Om fler än 10 spelare efter sortering. Ta bort sista spelaren ur listan.
            players.remove(10);
        }
        for (int i=0; i<players.size(); i++){
            System.out.println(Arrays.toString(players.toArray()));
        }
        //view.updateArray();// updatera listan som syns på vinnare i JList.

        //Spelet ska starta om eller avslutas.
    }
}


