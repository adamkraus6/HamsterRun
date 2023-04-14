package edu.sdsmt.Hamster_Run_Kraus_Adam.States;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;
import edu.sdsmt.Hamster_Run_Kraus_Adam.StateMachine;

/**
 * Zooming hamster state class
 */
public class ZoomingHamster extends State {
    public ZoomingHamster(Game g, StateMachine sm) {
        super(g, sm);
    }

    /**
     * End of current state
     */
    @Override
    public void endTask() {

    }

    /**
     * Beginning of current state
     */
    @Override
    public void startTask() {
        // GRADING: ENERGY
        g.setEnergyToMove(2);

        g.setZoomMove(2);
    }

    /**
     * Update event for state
     */
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
