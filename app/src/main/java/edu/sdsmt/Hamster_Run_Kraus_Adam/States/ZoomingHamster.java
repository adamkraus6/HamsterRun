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
        g.setEnergyToMove(2);
        g.setZoomMove(2);
    }

    @Override
    public void doTask() {
        // no pickup

        if(g.isWon() || g.isLost()) {
            sm.setState(StateMachine.StateEnum.EndedGame);
            return;
        }

        if(g.getZoomMove() < 0) {
            if(g.getFood() >= 15) {
                sm.setState(StateMachine.StateEnum.HeavyHamster);
            } else {
                sm.setState(StateMachine.StateEnum.BaseHamster);
            }
        }
    }
}
