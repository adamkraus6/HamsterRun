package edu.sdsmt.Hamster_Run_Kraus_Adam.States;

public abstract class State {
    public abstract void endTask();
    public abstract void startTask();
    public abstract String getStateName();
}
