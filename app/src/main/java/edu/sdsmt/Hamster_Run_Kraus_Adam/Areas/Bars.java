package edu.sdsmt.Hamster_Run_Kraus_Adam.Areas;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;

/**
 * Bar grid location class
 */
public class Bars implements GameArea {
    /**
     * Pickup event for grid location
     * @param g game
     */
    @Override
    public void pickup(Game g) {}

    /**
     * Enter event for grid location
     * @param g game
     */
    @Override
    public void enter(Game g) {
        if(g.getFood() > 5) {
            // set to 5 food
            g.addFood(5-g.getFood());
        }
    }
}
