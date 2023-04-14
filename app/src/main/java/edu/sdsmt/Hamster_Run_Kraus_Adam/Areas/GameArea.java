package edu.sdsmt.Hamster_Run_Kraus_Adam.Areas;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;

/**
 * Game grid location interface
 */
public interface GameArea {
    void pickup(Game g);
    void enter(Game g);
}
