package edu.sdsmt.Hamster_Run_Kraus_Adam.Areas;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;

public class Home implements GameArea {
    @Override
    public void pickup(Game g) {}

    @Override
    public void enter(Game g) {
        // deposit all food into stores
        int food = g.getFood();
        g.storeFood(food);
        // remove from pouches
        g.addFood(-1*food);
    }
}
