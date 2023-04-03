package edu.sdsmt.Hamster_Run_Kraus_Adam.Areas;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;

public class Food implements GameArea {
    private int units;
    public Food(int units) {
        this.units = units;
    }

    @Override
    public void pickup(Game g) {
        // add 5 food if possible
        if(units > 0)
            g.addFood(5);
        units--;
    }

    @Override
    public void enter(Game g) {

    }
}
