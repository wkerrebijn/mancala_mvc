package nl.sogyo.mancala;

/**
 * Created by wkerrebijn on 24-5-2017.
 */

import java.util.ArrayList;

public class Gamestate {

    private Player player;
    private ArrayList<Field> fields;

    Gamestate() {
        player = new Player();
        fields = new ArrayList<Field>();
        Hole h = new Hole(4, p);
        fields.add(h);
    }
}
