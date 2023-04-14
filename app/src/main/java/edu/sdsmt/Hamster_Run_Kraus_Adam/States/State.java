package edu.sdsmt.Hamster_Run_Kraus_Adam.States;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;
import edu.sdsmt.Hamster_Run_Kraus_Adam.StateMachine;

public abstract class State {
    protected Game g;
    protected StateMachine sm;

    public State(Game g, StateMachine sm) {
        this.g = g;
        this.sm = sm;
    }
    public abstract void endTask();
    public abstract void startTask();
    public abstract void doTask();
}
