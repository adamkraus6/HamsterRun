/**
 * Hamster Run - Android
 * Adam Kraus
 * GUI - CSC468
 * Due: 4/27/2023
 * 
 * Complete the following checklist. If you partially completed an item, put a note how it can be
 * checked for what is working for partial credit.
 *
 * DONE Followed the class OOP diagram
 * DONE *Grading tags completed
 *
 *
 * Tier 1: Model		50
 * 	Move test 			DONE
 * 	Food test			DONE
 * 	Eat test 			DONE
 * 	Home test 			DONE
 * 	Zoom pickup test	DONE
 * 	Bar test 			DONE
 * 	Caught test 		DONE
 * 	No energy test		DONE
 * 	Win test 			DONE
 *
 * Tier 2: Connect Views		22
 * 	All views present test	 	DONE
 * 	Starting values test pass	DONE
 * 	Move test 	 				DONE
 * 	Food test	 				DONE
 * 	Eat test  					DONE
 * 	Bar test	 				DONE
 * 	Home test	 				DONE
 * 	Reset test	 				DONE
 *
 * Tier 3a: State Machine/Event Rules	34
 * 	Framework there	 			DONE
 * 	Base to heavy*	 			DONE
 * 	Heavy to zoom* 				DONE
 * 	Base to zoom* 				DONE
 * 	Caught*	 					DONE
 * 	No energy*	 				DONE
 * 	Win*	 					DONE
 * 	Reset on close ***	 		DONE
 *
 * Tier 3b: Floating Action	 		DONE
 * 	All buttons there 		 		DONE
 * 	Icons set and distinguishable	DONE
 * 	Opens/closes properly 	 		DONE
 * 	Tribble color updated.	 		DONE
 *
 * Tier 3c: Layout **	26
 * 	Custom’s View’s aspect ratio constant			DONE
 * 	Relative size of objects in view maintained 	DONE
 * 	Works in required screen sizes 	 				DONE
 *
 *
 * Tier 3d: Rotation		20
 * 	Required state saved on rotation 	 		DONE
 *
 * Tier 4: Extensions		30
 * Extension 1: 2i 20pts Barrier areas:
 * There are three barrier areas that cannot be entered. They are located at (2, 0), (2, 4),
 * and (4, 3)
 * Extension 2: 5a 5pts Disabled zoom button:
 * The zoom button is disabled if there are no zoom powers available.
 * Extension 3: 5b 5pts Saved Floating Action Button State:
 * The state of the floating action buttons to tint the hamster is saved on rotation.
 *
 * The grade you compute is the starting point for course staff, who reserve the right to change
 * the grade if they disagree with your assessment and to deduct points for other issues they may
 * encounter, such as errors in the submission process, naming issues, etc.
 */
package edu.sdsmt.Hamster_Run_Kraus_Adam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Main entry class
 */
public class MainActivity extends AppCompatActivity {
    private static final String ENERGY_VAL = "energy";
    private static final String FOOD_VAL = "food";
    private static final String ZOOM_VAL = "zoom";
    private static final String MOVE_VAL = "moves";
    private static final String STORES_VAL = "stores";
    private static final String POS_X_VAL = "pos_x";
    private static final String POS_Y_VAL = "pos_y";
    private static final String HAM_TINT_VAL = "tint";
    private static final String FLOAT_VAL = "float";
    private final Game g;
    private final StateMachine sm;
    private GameView gv;
    private FloatingActionButton redT, greenT, blueT;
    private boolean showTints = false;

    public MainActivity() {
        g = new Game();
        sm = new StateMachine(g, this);
        sm.setState(StateMachine.StateEnum.BaseHamster);
    }

    /**
     * Activity creation
     * @param savedInstanceState bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv = findViewById(R.id.gameView);

        redT = findViewById(R.id.tintRed);
        greenT = findViewById(R.id.tintGreen);
        blueT = findViewById(R.id.tintBlue);

        updateUI(false);
    }

    /**
     * Updates view with current game information
     */
    public void updateUI(boolean moved) {
        // notify state machine
        sm.onUpdate(moved);

        // update views
        TextView food = findViewById(R.id.food);
        food.setText(String.format("%s", g.getFood()));

        TextView zoom = findViewById(R.id.zoom);
        zoom.setText(String.format("%s", g.getZoomsLeft()));

        Button zoomBtn = findViewById(R.id.zoomBtn);
        zoomBtn.setEnabled(g.getZoomsLeft() > 0);

        TextView energy = findViewById(R.id.energy);
        energy.setText(String.format("%s", g.getEnergy()));

        TextView moves = findViewById(R.id.moves);
        moves.setText(String.format("%s", g.getMoves()));

        TextView stores = findViewById(R.id.stores);
        stores.setText(String.format("%s", g.getHomeStores()));

        if(showTints) {
            redT.show();
            greenT.show();
            blueT.show();
        } else {
            redT.hide();
            greenT.hide();
            blueT.hide();
        }

        gv.invalidate();
    }

    /**
     * Activity saving
     * @param savedInstanceState bundle
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // save state
        savedInstanceState.putInt(ENERGY_VAL, g.getEnergy());
        savedInstanceState.putInt(FOOD_VAL, g.getFood());
        savedInstanceState.putInt(ZOOM_VAL, g.getZoomsLeft());
        savedInstanceState.putInt(MOVE_VAL, g.getMoves());
        savedInstanceState.putInt(STORES_VAL, g.getHomeStores());
        Position pos = g.getPlayerLocation();
        savedInstanceState.putInt(POS_X_VAL, pos.x);
        savedInstanceState.putInt(POS_Y_VAL, pos.y);
        savedInstanceState.putInt(HAM_TINT_VAL, gv.getHamsterTint());
        savedInstanceState.putBoolean(FLOAT_VAL, showTints);
    }

    /**
     * Activity restoration
     * @param savedInstanceState bundle
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // restore state
        int energy = savedInstanceState.getInt(ENERGY_VAL);
        g.setEnergy(energy);
        int food = savedInstanceState.getInt(FOOD_VAL);
        g.setFood(food);
        int zoom = savedInstanceState.getInt(ZOOM_VAL);
        if(zoom == 0) g.addZoom();
        int moves = savedInstanceState.getInt(MOVE_VAL);
        g.setMoves(moves);
        int stores = savedInstanceState.getInt(STORES_VAL);
        g.setHomeStores(stores);

        Position pos = new Position();
        pos.x = savedInstanceState.getInt(POS_X_VAL);
        pos.y = savedInstanceState.getInt(POS_Y_VAL);
        g.setPosition(pos);

        int tint = savedInstanceState.getInt(HAM_TINT_VAL);
        gv.tintHamster(tint);

        showTints = savedInstanceState.getBoolean(FLOAT_VAL);

        updateUI(false);
    }

    /**
     * Gets game object
     * @return game
     */
    public Game getGame() {
        return g;
    }

    /**
     * Gets state machine object
     * @return state machine
     */
    public StateMachine getStateMachine() {
        return sm;
    }

    /**
     * Button click to move up
     * @param v view
     */
    public void moveUp(View v) {
        g.move(0, -1);
        g.setZoomMove(g.getZoomMove()-1);
        updateUI(true);
    }

    /**
     * Button click to move down
     * @param v view
     */
    public void moveDown(View v) {
        g.move(0, 1);
        g.setZoomMove(g.getZoomMove()-1);
        updateUI(true);
    }

    /**
     * Button click to move left
     * @param v view
     */
    public void moveLeft(View v) {
        g.move(-1, 0);
        g.setZoomMove(g.getZoomMove()-1);
        updateUI(true);
    }

    /**
     * Button click to move right
     * @param v view
     */
    public void moveRight(View v) {
        g.move(1, 0);
        g.setZoomMove(g.getZoomMove()-1);
        updateUI(true);
    }

    /**
     * Button click to eat food
     * @param v view
     */
    public void eat(View v) {
        g.eat();
        updateUI(false);
    }

    /**
     * Button click to reset game
     * @param v view
     */
    public void reset(View v) {
        g.reset();
        gv.tintHamster(Color.WHITE);
        updateUI(false);
    }

    /**
     * Button click to activate zoom powerup
     * @param v view
     */
    public void activateZoom(View v) {
        g.setZoomMove(2);
        g.addFood(-2);
        g.removeZoom();
        updateUI(false);
    }

    /**
     * Button click to open tints floating action button
     * @param v view
     */
    public void openTints(View v) {
        showTints = !showTints;
        updateUI(false);
    }

    /**
     * Button click to tint hamster red
     * @param v view
     */
    public void tintRed(View v) {
        gv.tintHamster(Color.RED);
        updateUI(false);
    }

    /**
     * Button click to tint hamster green
     * @param v view
     */
    public void tintGreen(View v) {
        gv.tintHamster(Color.GREEN);
        updateUI(false);
    }

    /**
     * Button click to tint hamster blue
     * @param v view
     */
    public void tintBlue(View v) {
        gv.tintHamster(Color.BLUE);
        updateUI(false);
    }
}