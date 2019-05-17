package com.example.bitsbytes;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

/**
 * Bits&Bytes
 * @version 1
 * @author Albert Hattingh
 * DiceActivity.java
 */
public class DiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ImageView leftDice = findViewById(R.id.leftDice);
        final ImageView rightDice = findViewById(R.id.rightDice);
        final int MAX = 3; // Change this to limit the maximum bound of the dice (6)

        final Button rollButton = findViewById(R.id.rollButton);
        final int[] diceFaces = {
                R.drawable.dice0,
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6
        };

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int first = rand.nextInt(MAX);
                int second = rand.nextInt(MAX);

                leftDice.setImageResource(diceFaces[first]);
                rightDice.setImageResource(diceFaces[second]);
            }
        });
    }
}
