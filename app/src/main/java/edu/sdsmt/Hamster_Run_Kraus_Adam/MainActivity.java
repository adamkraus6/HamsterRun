/**
 * Hamster Run - Android
 * Adam Kraus
 * GUI - CSC468
 * Due: 4/27/2023
 *
 * Tier 1: Model
 * DONE a. Move test
 * DONE b. Food test
 * DONE c. Eat test
 * DONE d. Home test
 * DONE e. Zoom pickup test
 * DONE f. Bar test
 * DONE g. Caught test
 * DONE h. No energy test
 * DONE i. Win test
 *
 * Tier 2: Connect Views
 * DONE a. All views present test
 * DONE b. Starting values test pass
 * DONE c. Move test
 * DONE d. Food test
 * DONE e. Eat test
 * DONE f. Bar test
 * DONE g. Home test
 * DONE h. Reset test
 *
 * Tier 3a: State Machine/Event Rules
 * ____ a. Framework there
 * ____ b. Base to heavy*
 * ____ c. Heavy to zoom*
 * ____ d. Base to zoom*
 * ____ e. Caught*
 * ____ f. No energy*
 * ____ g. Win*
 * ____ h. Reset on close***
 *
 * Tier 3b: Floating Action
 * DONE a. All buttons there
 * DONE b. Icons set and distinguishable
 * DONE c. Opens/closes properly
 * DONE d. Hamster color updated
 *
 * Tier 3c: Layout
 * DONE a. Custom view's aspect ratio constant
 * DONE b. Relative size of objects in view maintained
 * DONE c. Works in required screen sizes
 *
 * Tier 3d: Rotation
 * DONE a. Required state saved on rotation
 *
 * Extensions:
 * Extension 1: 2i 20 points Barrier areas
 * There are three barrier areas at (2, 0), (2, 4), and (4, 3) that cannot be entered
 * Extension 2: 5a 5 points Disable zoom button
 * The zoom button is disabled if the hamster does not have any zoom power ups
 */
package edu.sdsmt.Hamster_Run_Kraus_Adam;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private Game g;
    private GameView gv;
    private FloatingActionButton redT, greenT, blueT;
    private StateMachine sm;
    private boolean showTints = false;
    private static final String ENERGY_VAL = "energy";
    private static final String FOOD_VAL = "food";
    private static final String ZOOM_VAL = "zoom";
    private static final String MOVE_VAL = "moves";
    private static final String STORES_VAL = "stores";
    private static final String POS_X_VAL = "pos_x";
    private static final String POS_Y_VAL = "pos_y";
    private static final String HAM_TINT_VAL = "tint";

    public MainActivity() {
        g = new Game();
        sm = new StateMachine(g);
    }

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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
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
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // restore state
        int energy = savedInstanceState.getInt(ENERGY_VAL);
        g.setEnergy(energy);
        int food = savedInstanceState.getInt(FOOD_VAL);
        g.setFood(food);
        int zoom = savedInstanceState.getInt(ZOOM_VAL);
        g.setZoom(zoom);
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

        updateUI();
    }

    public Game getGame() {
        return g;
    }

    public StateMachine getStateMachine() {
        return sm;
    }

    public void moveUp(View v) {
        g.move(0, -1);
        updateUI();
    }

    public void moveDown(View v) {
        g.move(0, 1);
        updateUI();
    }

    public void moveLeft(View v) {
        g.move(-1, 0);
        updateUI();
    }

    public void moveRight(View v) {
        g.move(1, 0);
        updateUI();
    }

    public void updateUI() {
        TextView food = findViewById(R.id.food);
        food.setText(Integer.toString(g.getFood()));

        TextView zoom = findViewById(R.id.zoom);
        zoom.setText(Integer.toString(g.getZoomsLeft()));

        Button zoomBtn = findViewById(R.id.zoomBtn);
        zoomBtn.setEnabled(g.getZoomsLeft() > 0);

        TextView energy = findViewById(R.id.energy);
        energy.setText(Integer.toString(g.getEnergy()));

        TextView moves = findViewById(R.id.moves);
        moves.setText(Integer.toString(g.getMoves()));

        TextView stores = findViewById(R.id.stores);
        stores.setText(Integer.toString(g.getHomeStores()));

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

    public void eat(View v) {
        g.eat();
        updateUI();
    }

    public void reset(View v) {
        g.reset();
        gv.tintHamster(Color.WHITE);
        updateUI();
    }

    public void openTints(View v) {
        showTints = !showTints;
        updateUI();
    }

    public void tintRed(View v) {
        gv.tintHamster(Color.RED);
        updateUI();
    }

    public void tintGreen(View v) {
        gv.tintHamster(Color.GREEN);
        updateUI();
    }

    public void tintBlue(View v) {
        gv.tintHamster(Color.BLUE);
        updateUI();
    }
}