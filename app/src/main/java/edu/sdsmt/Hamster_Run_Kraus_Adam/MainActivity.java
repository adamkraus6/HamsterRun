/**
 * Hamster Run - Android
 * Adam Kraus
 * GUI - CSC468
 * Due: 4/27/2023
 * 
 * TODO:
 * Email about
 * 1. checklist
 * 2. test_win fails with dialog
 * 3. base_to_heavy_and_back fails unless eat click added
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

        updateUI();
    }

    /**
     * Updates view with current game information
     */
    public void updateUI() {
        // notify state machine
        sm.onUpdate();

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

        updateUI();
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
        updateUI();
    }

    /**
     * Button click to move down
     * @param v view
     */
    public void moveDown(View v) {
        g.move(0, 1);
        g.setZoomMove(g.getZoomMove()-1);
        updateUI();
    }

    /**
     * Button click to move left
     * @param v view
     */
    public void moveLeft(View v) {
        g.move(-1, 0);
        g.setZoomMove(g.getZoomMove()-1);
        updateUI();
    }

    /**
     * Button click to move right
     * @param v view
     */
    public void moveRight(View v) {
        g.move(1, 0);
        g.setZoomMove(g.getZoomMove()-1);
        updateUI();
    }

    /**
     * Button click to eat food
     * @param v view
     */
    public void eat(View v) {
        g.eat();
        updateUI();
    }

    /**
     * Button click to reset game
     * @param v view
     */
    public void reset(View v) {
        g.reset();
        gv.tintHamster(Color.WHITE);
        updateUI();
    }

    /**
     * Button click to activate zoom powerup
     * @param v view
     */
    public void activateZoom(View v) {
        g.setZoomMove(2);
        g.addFood(-2);
        g.removeZoom();
        updateUI();
    }

    /**
     * Button click to open tints floating action button
     * @param v view
     */
    public void openTints(View v) {
        showTints = !showTints;
        updateUI();
    }

    /**
     * Button click to tint hamster red
     * @param v view
     */
    public void tintRed(View v) {
        gv.tintHamster(Color.RED);
        updateUI();
    }

    /**
     * Button click to tint hamster green
     * @param v view
     */
    public void tintGreen(View v) {
        gv.tintHamster(Color.GREEN);
        updateUI();
    }

    /**
     * Button click to tint hamster blue
     * @param v view
     */
    public void tintBlue(View v) {
        gv.tintHamster(Color.BLUE);
        updateUI();
    }
}