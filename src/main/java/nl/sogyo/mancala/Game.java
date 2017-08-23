package nl.sogyo.mancala;

/**
 * Created by wkerrebijn on 24-5-2017.
 */

import java.util.ArrayList;

public class Game {

    private Player player;
    private ArrayList<Field> fields;

    public Game() {
        init();
    }

    private void init() {
        player = new Player();
        fields = new ArrayList<Field>();

        int size = 14;

        Field h = new Hole(4, player);
        ((Hole)h).initiateChain(size);
        fields.add(h);
        for(int i = 1 ; i <= size ; i++) {
            h = h.getNeighbor();
            fields.add(h);
        }
    }

    public void restart() {
        init();
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }
}
