package edu.sdsmt.Hamster_Run_Kraus_Adam;

import edu.sdsmt.Hamster_Run_Kraus_Adam.States.State;

public class StateMachine {
    private State currentState;
    public String getCurrentStateName() {
        return currentState.getStateName();
    }
}
