package controller;

import model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Controller {

    private List<Player> players = new ArrayList<>();// lista med spelare som vunnit
    private int numWinners = 0;// Antalet personer som vunnit.


    public void HighScoreList() { // Klassen ska aktiveras när någon har vunnit spelet. länk från den listenern.

        String winner = JOptionPane.showInputDialog("You won!!! Print your name"); // ta in namn på vinnaren

        players.add(new Player(winner, countTotalGuess)); // addera spelarens namn med string och dens antal gissningar(poäng) till array av vinnare.
        numWinners++;// incrementera antalet vinnare.


        Collections.sort(players, new Comparator<Player>() {// Sortera vinnarna med varandra för att se vem som har bäst score. Sätt den längst upp.
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getScore() > o1.getScore() ? -1 : 1;
            }
        });

        if (numWinners > 10) {// Om fler än 10 spelare efter sortering. Ta bort sista spelaren ur listan.
            players.remove(10);
        }
        view.updateArray();// updatera listan som syns på vinnare i JList.
    }
}


