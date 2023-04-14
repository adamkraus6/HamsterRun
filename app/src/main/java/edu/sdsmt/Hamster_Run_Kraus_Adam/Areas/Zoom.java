package edu.sdsmt.Hamster_Run_Kraus_Adam.Areas;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;

/**
 * Zoom powerup grid location class
 */
public class Zoom implements GameArea {
    private boolean left;
    public Zoom() {
        left = true;
    }

    /**
     * Pickup event for grid location
     * @param g game
     */
    @Override
    public void pickup(Game g) {
        if(left) {
            g.addZoom();
            left = false;
        }
    }

    /**
     * Enter event for grid location
     * @param g game
     */
    @Override
    public void enter(Game g) {

    }
}
