package edu.sdsmt.Hamster_Run_Kraus_Adam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Game g;
    private StateMachine sm;

    public MainActivity() {
        g = new Game();
        sm = new StateMachine();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public Game getGame() {
        return g;
    }

    public StateMachine getStateMachine() {
        return sm;
    }

    public void moveUp(View v) {
        g.move(0, -1);
    }

    public void moveDown(View v) {
        g.move(0, 1);
    }

    public void moveLeft(View v) {
        g.move(-1, 0);
    }

    public void moveRight(View v) {
        g.move(1, 0);
    }

    public void eat(View v) {
        g.eat();
    }
}