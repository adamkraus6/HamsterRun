package edu.sdsmt.Hamster_Run_Kraus_Adam.States;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;
import edu.sdsmt.Hamster_Run_Kraus_Adam.StateMachine;

/**
 * Abstract state class
 */
public abstract class State {
    protected Game g;
    protected StateMachine sm;

    /**
     * Default constructor
     * @param g game object
     * @param sm state machine object
     */
    public State(Game g, StateMachine sm) {
        this.g = g;
        this.sm = sm;
    }

    /**
     * End of current state
     */
    public abstract void endTask();

    /**
     * Beginning of current state
     */
    public abstract void startTask();

    /**
     * Update event for state
     * @param moved true if hamster moved
     */
    public abstract void doTask(boolean moved);
}
