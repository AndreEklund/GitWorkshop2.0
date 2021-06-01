package outdatedClasses;

import java.io.*;
import java.util.*;

public class Controller {

    /**
     * Author Benny Petersson
     */


    private List<Player> players = new ArrayList<>();// lista med spelare som vunnit
    private int numWinners = 0;// Antalet personer som vunnit.

    public Controller() {

    }


    public void HighScoreList(String time, String winner) { // Klassen ska aktiveras när någon har vunnit spelet. länk från den listenern.
        // ta in namn och tid på vinnaren

// Behövde splitta time först för att strängen som skickades vid vinst innnehöll : tecken. Tog bort tecknet i källan istället och har därför kommenterat ut.
        //String[] hourMin = time.split(":");
        // int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(time);
        //int hoursInMins = hour * 60;

//Testdata för att testa sortering.... Den fungerar.
        players.add(new Player("kent", 5));
        players.add(new Player("Hercules", 25));
        players.add(new Player("Brago", 1));
        players.add(new Player("Tussen", 2));
        players.add(new Player(winner, mins)); // addera spelarens namn med string och dens antal gissningar(poäng) till array av vinnare.
        numWinners++;// incrementera antalet vinnare.
        System.out.println(numWinners);

        Collections.sort(players, new Comparator<Player>() {// Sortera vinnarna med varandra för att se vem som har bäst score. Sätt den längst upp.
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getScore() > o1.getScore() ? -1 : 1; // Kan inte parseint här då det finns :-tecken i stringen.
            }
        });

        if (numWinners > 10) {// Om fler än 10 spelare efter sortering. Ta bort sista spelaren ur listan.
            players.remove(10);
        }

        System.out.println(Arrays.toString(players.toArray()));

        //view.updateArray();// updatera listan som syns på vinnare i JList.

        //Spelet ska starta om eller avslutas.
    }

    public void writePlayersToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename))))
        {oos.writeInt(players.size());
            for (Player p : players)
            {oos.writeObject(p);}
            oos.flush();}
    }

    public void getPlayersFromFile(String filename) throws IOException {
        players.clear();
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename))) ) {
            int n = ois.readInt();
            for (int i = 0; i < n; i++) {
                try {
                    players.add((Player)
                            ois.readObject());
                } catch(ClassNotFoundException e) {}
            }
        }
    }
    }





