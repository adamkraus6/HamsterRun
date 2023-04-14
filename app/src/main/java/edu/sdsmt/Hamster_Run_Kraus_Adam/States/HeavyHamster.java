package edu.sdsmt.Hamster_Run_Kraus_Adam.States;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;
import edu.sdsmt.Hamster_Run_Kraus_Adam.StateMachine;

public class HeavyHamster extends State {
    public HeavyHamster(Game g, StateMachine sm) {
        super(g, sm);
    }

    @Override
    public void endTask() {

    }

    @Override
    public void startTask() {
        g.setEnergyToMove(2);
    }

    @Override
    public void doTask() {
        g.pickup();

        if(g.isWon() || g.isLost()) {
            sm.setState(StateMachine.StateEnum.EndedGame);
            return;
        }

//        if(g.getZoomMove() > 1) {
//            sm.setState(StateMachine.StateEnum.ZoomingHamster);
//        }

        if(g.getFood() < 15) {
            sm.setState(StateMachine.StateEnum.BaseHamster);
            return;
        }
    }
}
