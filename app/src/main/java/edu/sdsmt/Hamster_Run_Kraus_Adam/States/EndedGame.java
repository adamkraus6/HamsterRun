package edu.sdsmt.Hamster_Run_Kraus_Adam.States;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;
import edu.sdsmt.Hamster_Run_Kraus_Adam.StateMachine;

public class EndedGame extends State {
    public EndedGame(Game g, StateMachine sm) {
        super(g, sm);
    }

    @Override
    public void endTask() {

    }

    @Override
    public void startTask() {
        // determine if win or lose
        // open dialog
        // g.reset();
    }

    @Override
    public void doTask() {
        // change to base
    }
}
