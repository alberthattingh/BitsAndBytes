package com.example.bitsbytes;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Random;

public class CardsActivity extends AppCompatActivity {

    public int counter, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView title = findViewById(R.id.title);
        TextView scratch = findViewById(R.id.scratchText);
        TextView trivia1 = findViewById(R.id.triviaText1);
        TextView trivia2 = findViewById(R.id.triviaText2);
        TextView trivia3 = findViewById(R.id.triviaText3);

        Random rand = new Random();
        int choice = rand.nextInt(2);

        if (choice == 0)
        {
            time = 20;
            title.setText("Trivia");
            String[] trivias = getRandomCards();

            trivia1.setText(trivias[0]);
            trivia2.setText(trivias[1]);
            trivia3.setText(trivias[2]);
        }
        else if (choice == 1)
        {
            time = 30;
            title.setText("Scratch");
            String randomScratch = getRandomScratch();
            scratch.setText(randomScratch);
        }

        final TextView timeText = findViewById(R.id.timeText1);

        counter = time;
        new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText(String.valueOf(counter));
                counter--;
            }

            @Override
            public void onFinish() {
                timeText.setText("TIME'S UP!");
            }
        }.start();
    }

    /** The maximun amount of lines that can be read from the trivia input text file */
    private static final int MAX_T = 20;

    /** The maximum amount of lines that can be read from the scratch input text file */
    private static final int MAX_S = 3;

    /** The amount of lines that was read from the trivia text file */
    private int lines_t;

    /** The amount of lines that was read from the scratch text file */
    private int lines_s;

    /**
     * Reads from the trivia input text file
     * @return An array of Strings read from the input text file
     */
    public String[] readTrivia()
    {
        lines_t = 0;
        String[] outputLines = new String[MAX_T];
        BufferedReader file = null;

        try
        {

            file = new BufferedReader(new InputStreamReader(getAssets().open("trivia.txt")));
            String line = file.readLine();

            for (int i=0; i<MAX_T; i++)
            {
                if (line != null)
                {
                    outputLines[i] = line;
                    lines_t++;
                    line = file.readLine();
                }
            }
            file.close();
        }
        catch (Exception e)
        {
            Log.e("Bits&Bytes", "File not found");
        }
        return outputLines;
    }

    /**
     * Invokes readTrivia() to read from the trivia text file and then chooses 3 random trivias from the list
     * @return An array of 3 random trivia Strings
     */
    public String[] getRandomCards()
    {
        int r1, r2, r3;
        String[] allTrivia = readTrivia();
        String[] out = new String[3];
        Random rand = new Random();

        // Get random numbers to select corresponding trivias
        r1 = rand.nextInt(lines_t);
        r2 = rand.nextInt(lines_t);
        r3 = rand.nextInt(lines_t);

        // Check whether duplicate trivias will be selected and change it, if true
        while (r2 == r1 || r2 == r3)
        {
            r2 = rand.nextInt(lines_t);
        }

        // Check whether duplicate trivias will be selected and change it, if true
        while (r3 == r1)
        {
            r3 = rand.nextInt(lines_t);
        }

        out[0] = allTrivia[r1];
        out[1] = allTrivia[r2];
        out[2] = allTrivia[r3];

        return out;
    }

    /**
     * Reads from the scratch input text file and chooses a random line of text
     * @return A random scratch code snippet request
     */
    public String getRandomScratch()
    {
        lines_s = 0;
        String[] outputLines = new String[MAX_S];
        BufferedReader file = null;

        try
        {
            file = new BufferedReader(new InputStreamReader(getAssets().open("scratch.txt")));
            String line = file.readLine();

            for (int i=0; i<MAX_S; i++)
            {
                if (line != null)
                {
                    outputLines[i] = line;
                    lines_s++;
                    line = file.readLine();
                }
            }
            file.close();

            Random rand = new Random();
            int chosen1 = rand.nextInt(lines_s);
            return outputLines[chosen1];
        }
        catch (Exception e)
        {
            Log.e("Bits&Bytes", "File not found");
        }
        return "Error reading from file";
    }

}
