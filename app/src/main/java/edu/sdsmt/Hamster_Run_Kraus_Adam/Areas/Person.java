package edu.sdsmt.Hamster_Run_Kraus_Adam.Areas;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;

public class Person implements GameArea {
    @Override
    public void pickup(Game g) {
        g.loseGame();
    }

    @Override
    public void enter(Game g) {

    }
}
