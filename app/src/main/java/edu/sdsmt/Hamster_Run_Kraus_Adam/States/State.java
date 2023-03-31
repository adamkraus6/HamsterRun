package edu.sdsmt.Hamster_Run_Kraus_Adam.States;

public interface State {
    public void endTask();
    public void startTask();
    public String getStateName();
}
