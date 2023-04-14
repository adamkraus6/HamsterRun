package edu.sdsmt.Hamster_Run_Kraus_Adam.Areas;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;

/**
 * Food grid location class
 */
public class Food implements GameArea {
    private int units;
    public Food(int units) {
        this.units = units;
    }

    /**
     * Pickup event for grid location
     * @param g game
     */
    @Override
    public void pickup(Game g) {
        // add 5 food if possible
        if(units > 0)
            g.addFood(5);
        units--;
    }

    /**
     * Enter event for grid location
     * @param g game
     */
    @Override
    public void enter(Game g) {

    }
}
