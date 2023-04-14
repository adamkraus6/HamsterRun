package edu.sdsmt.Hamster_Run_Kraus_Adam.Areas;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;

/**
 * Person grid location class
 */
public class Person implements GameArea {
    /**
     * Pickup event for grid location
     * @param g game
     */
    @Override
    public void pickup(Game g) {
        g.loseGame();
    }

    /**
     * Enter event for grid location
     * @param g game
     */
    @Override
    public void enter(Game g) {

    }
}
