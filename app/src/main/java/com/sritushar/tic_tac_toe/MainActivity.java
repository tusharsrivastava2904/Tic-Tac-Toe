package com.sritushar.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0:0 ; 1:X ; 2:empty
    boolean gameWin = false;
    int winner = -1;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winninState = {{0, 1, 2}, {0, 3, 6}, {0, 4, 8}, {1, 4, 7}, {2, 5, 8}, {3, 4, 5}, {2, 4, 6}, {6, 7, 8}};
    int activePlayer = 0;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void drop(View view) {

        if (gameWin == false) {
            ImageView counter = (ImageView) view;
            int tappedCounter = Integer.parseInt(counter.getTag().toString());
            Log.i("TAG", "drop: " + tappedCounter);
            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.o);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.x);
                activePlayer = 0;
            }

            counter.setTranslationY(-2000);
            counter.animate().translationYBy(2000).rotation(3600).setDuration(300);

            for (int[] winningPosition : winninState) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {

                    //someone has won
                    winner = gameState[winningPosition[0]];
                    if (winner == 0) {
                        Log.i("TAG", "!!SOMEONE WON!! ");
                        Toast.makeText(getBaseContext(), "Player O WINS!!!", Toast.LENGTH_LONG).show();
                    } else if (winner == 1) {
                        Log.i("TAG", "!!SOMEONE WON!! ");
                        Toast.makeText(getBaseContext(), "Player X WINS!!!", Toast.LENGTH_LONG).show();
                    }

                    TextView winText = (TextView) findViewById(R.id.winTextLabel);
                    winText.setText("Player " + winner + " has WON!!");

                    resetButton = (Button) findViewById(R.id.resetButton);
                    resetButton.setVisibility(View.VISIBLE);
                    winText.setVisibility(View.VISIBLE);

                    gameWin = true;

                }
            }
        } else {
            Toast.makeText(this, "!!RESET TO BEGIN AGAIN!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void reset(View view) {

        resetButton = (Button) findViewById(R.id.resetButton);
        TextView winText = (TextView) findViewById(R.id.winTextLabel);
        resetButton.setVisibility(View.GONE);
        winText.setVisibility(View.GONE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
           ImageView counter = (ImageView) gridLayout.getChildAt(i);
           counter.setImageDrawable(null);
        }

        //0:0 ; 1:X ; 2:empty
        gameWin = false;
        winner = -1;
        for(int i: gameState) {
            gameState[i] = 2;
        }
        int activePlayer = 0;

    }
}