package edu.sdsmt.Hamster_Run_Kraus_Adam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Game g;
    private StateMachine sm;

    public MainActivity() {
        g = new Game();
        sm = new StateMachine(g);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // save state
        // private static final STRING TEST = "test";
        // savedInstanceState.putInt(TEST, testVal);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // restore state
        // testVal = savedInstanceState.getInt(TEST);
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

        GameView gv = findViewById(R.id.gameView);
        gv.invalidate();
    }

    public void eat(View v) {
        g.eat();
        updateUI();
    }

    public void reset(View v) {
        g.reset();
        updateUI();
    }
}