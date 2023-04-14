package edu.sdsmt.Hamster_Run_Kraus_Adam.States;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;
import edu.sdsmt.Hamster_Run_Kraus_Adam.StateMachine;

public class ZoomingHamster extends State {
    public ZoomingHamster(Game g, StateMachine sm) {
        super(g, sm);
    }

    @Override
    public void endTask() {

    }

    @Override
    public void startTask() {
        // set zoomMove = 2
    }

    @Override
    public void doTask() {
        // no pickup

        // if win or lose
            // change to ended

        // if 0 zoomMove
            // if food >= 15
                // change to heavy
            // else
                // change to base
    }
}
