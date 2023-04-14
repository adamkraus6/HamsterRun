package edu.sdsmt.Hamster_Run_Kraus_Adam.Areas;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;

public class Zoom implements GameArea {
    private boolean left;
    public Zoom() {
        left = true;
    }
    @Override
    public void pickup(Game g) {
        if(left) {
            g.addZoom();
            left = false;
        }
    }

    @Override
    public void enter(Game g) {

    }
}
