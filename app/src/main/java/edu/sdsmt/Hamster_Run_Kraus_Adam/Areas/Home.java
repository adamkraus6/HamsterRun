package edu.sdsmt.Hamster_Run_Kraus_Adam.Areas;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;

/**
 * Home grid location class
 */
public class Home implements GameArea {
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
        // deposit all food into stores
        int food = g.getFood();
        g.storeFood(food);
        // remove from pouches
        g.addFood(-1*food);
    }
}
