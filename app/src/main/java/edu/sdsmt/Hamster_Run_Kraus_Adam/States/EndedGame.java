package edu.sdsmt.Hamster_Run_Kraus_Adam.States;

import android.app.AlertDialog;
import android.content.DialogInterface;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;
import edu.sdsmt.Hamster_Run_Kraus_Adam.MainActivity;
import edu.sdsmt.Hamster_Run_Kraus_Adam.StateMachine;

/**
 * Ended game state class
 */
public class EndedGame extends State {
    private MainActivity context;
    public EndedGame(Game g, StateMachine sm, MainActivity context) {
        this(g, sm);
        this.context = context;
    }
    public EndedGame(Game g, StateMachine sm) {
        super(g, sm);
    }

    /**
     * End of current state
     */
    @Override
    public void endTask() {
        context.reset(null);
    }

    /**
     * Beginning of current state
     */
    @Override
    public void startTask() {
        boolean won = g.isWon();

        String msg = won ? "WON - Sufficient food in stores" :
                "LOST - " + (g.getEnergy() < 0 ? "No energy" : "Caught");

        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setMessage(msg);
        builder.setPositiveButton("Reset", new ResetClick());

        // GRADING: DIALOG
        builder.create().show();
        // this makes test_win fail, commented out works
    }

    /**
     * Update event for state
     */
    @Override
    public void doTask() {
    }

    /**
     * Click event listener for dialog
     */
    private class ResetClick implements DialogInterface.OnClickListener {

        /**
         * Handles the button click for dialog
         * @param dialogInterface dialog
         * @param i button id
         */
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            // GRADING: RESET
            sm.setState(StateMachine.StateEnum.BaseHamster);
        }
    }
}
