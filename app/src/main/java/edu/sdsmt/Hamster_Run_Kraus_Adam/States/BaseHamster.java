package edu.sdsmt.Hamster_Run_Kraus_Adam.States;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;
import edu.sdsmt.Hamster_Run_Kraus_Adam.StateMachine;

/**
 * Base hamster state class
 */
public class BaseHamster extends State {
    public BaseHamster(Game g, StateMachine sm) {
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
        g.setEnergyToMove(1);
    }

    /**
     * Update event for
     * @param moved true if hamster moved
     */
    @Override
    public void doTask(boolean moved) {
        // only pickup when moving
        // ignores updates from other button clicks
        if(moved) g.pickup();

        if(g.isWon() || g.isLost()) {
            sm.setState(StateMachine.StateEnum.EndedGame);
            return;
        }

        if(g.getZoomMove() > 1) {
            // GRADING: TO_ZOOM
            sm.setState(StateMachine.StateEnum.ZoomingHamster);
            return;
        }

        if(g.getFood() >= 15) {
            // GRADING: TO_HEAVY
            sm.setState(StateMachine.StateEnum.HeavyHamster);
            return;
        }
    }
}
