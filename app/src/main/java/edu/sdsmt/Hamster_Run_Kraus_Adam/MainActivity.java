package edu.sdsmt.Hamster_Run_Kraus_Adam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public Game getGame() {
        return null;
    }

    public StateMachine getStateMachine() {
        return null;
    }
}