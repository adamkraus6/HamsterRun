package edu.sdsmt.Hamster_Run_Kraus_Adam.States;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;
import edu.sdsmt.Hamster_Run_Kraus_Adam.StateMachine;

public class BaseHamster extends State {
    public BaseHamster(Game g, StateMachine sm) {
        super(g, sm);
    }

    @Override
    public void endTask() {

    }

    @Override
    public void startTask() {
        g.setEnergyToMove(1);
    }

    @Override
    public void doTask() {
        g.pickup();

        if(g.isWon() || g.isLost()) {
            sm.setState(StateMachine.StateEnum.EndedGame);
            return;
        }

        if(g.getFood() >= 15) {
            sm.setState(StateMachine.StateEnum.HeavyHamster);
            return;
        }
    }
}
