package edu.sdsmt.Hamster_Run_Kraus_Adam;

import edu.sdsmt.Hamster_Run_Kraus_Adam.States.BaseHamster;
import edu.sdsmt.Hamster_Run_Kraus_Adam.States.EndedGame;
import edu.sdsmt.Hamster_Run_Kraus_Adam.States.HeavyHamster;
import edu.sdsmt.Hamster_Run_Kraus_Adam.States.State;
import edu.sdsmt.Hamster_Run_Kraus_Adam.States.ZoomingHamster;

/**
 * State machine class
 */
public class StateMachine {
    private StateEnum currentState = StateEnum.BaseHamster;
    private State[] states = null;
    public StateMachine(Game g, MainActivity context) {
        states = new State[] {
                new BaseHamster(g, this),
                new HeavyHamster(g, this),
                new ZoomingHamster(g, this),
                // give EndedGame context for dialog box
                new EndedGame(g, this, context)
        };
    }

    /**
     * Update event for current state
     */
    public void onUpdate(boolean moved) {
        states[currentState.ordinal()].doTask(moved);
    }

    /**
     * Gets current state
     * @return state enum
     */
    public StateEnum getState() {
        return currentState;
    }

    /**
     * Sets new state
     * @param state new state enum
     */
    public void setState(StateEnum state) {
        states[this.currentState.ordinal()].endTask();

        currentState = state;

        states[this.currentState.ordinal()].startTask();
    }

    /**
     * Gets current state name
     * @return state name
     */
    public String getCurrentStateName() {
        return states[currentState.ordinal()].getClass().getName();
    }

    public enum StateEnum {BaseHamster, HeavyHamster, ZoomingHamster, EndedGame}
}
