package edu.sdsmt.Hamster_Run_Kraus_Adam.Areas;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;

public class Bars implements GameArea {
    @Override
    public void pickup(Game g) {}

    @Override
    public void enter(Game g) {
        if(g.getFood() > 5) {
            // set to 5 food
            g.addFood(5-g.getFood());
        }
    }
}
