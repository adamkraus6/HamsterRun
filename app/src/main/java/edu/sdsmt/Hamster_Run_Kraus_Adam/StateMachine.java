package edu.sdsmt.Hamster_Run_Kraus_Adam;

import edu.sdsmt.Hamster_Run_Kraus_Adam.States.BaseHamster;
import edu.sdsmt.Hamster_Run_Kraus_Adam.States.EndedGame;
import edu.sdsmt.Hamster_Run_Kraus_Adam.States.HeavyHamster;
import edu.sdsmt.Hamster_Run_Kraus_Adam.States.State;
import edu.sdsmt.Hamster_Run_Kraus_Adam.States.ZoomingHamster;

public class StateMachine {
    public enum StateEnum {BaseHamster, HeavyHamster, ZoomingHamster, EndedGame}
    private StateEnum currentState = StateEnum.BaseHamster;
    private State[] states = null;

    public StateMachine(Game g) {
        states = new State[] {
                new BaseHamster(g, this),
                new HeavyHamster(g, this),
                new ZoomingHamster(g, this),
                new EndedGame(g, this)
        };
    }

    public void setState(StateEnum state) {
        states[this.currentState.ordinal()].endTask();

        currentState = state;

        states[this.currentState.ordinal()].startTask();
    }

    public void onUpdate() {
        states[currentState.ordinal()].doTask();
    }

    public StateEnum getState() {
        return currentState;
    }

    public String getCurrentStateName() {
        return states[currentState.ordinal()].getClass().getName();
    }
}
