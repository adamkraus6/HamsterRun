package edu.sdsmt.Hamster_Run_Kraus_Adam.States;

public class BaseHamster extends State {
    @Override
    public void endTask() {

    }

    @Override
    public void startTask() {

    }

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }
}
