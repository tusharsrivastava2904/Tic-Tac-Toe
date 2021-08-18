package com.sritushar.tic_tac_toe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final int o_tag = 0;
    private final int x_tag = 1;
    //O:0 X:1
    int active_player = 0;
    boolean game_active = true;
    int[] game_state = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winning_states = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private Button resetButton;
    private TextView messageBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetButton = findViewById(R.id.resetButton);
        messageBox = findViewById(R.id.winTextLabel);
    }


    public void drop(View view) {
        ImageView counter = (ImageView) view;
        int tapped_counter = Integer.parseInt(counter.getTag().toString());

        if (game_state[tapped_counter] == 2 && game_active) {
            game_state[tapped_counter] = active_player;
            counter.setTranslationY(-1500);

            if (active_player == o_tag) {
                counter.setImageResource(R.drawable.o);
                active_player = x_tag;
            } else {
                counter.setImageResource(R.drawable.x);
                active_player = o_tag;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(150);

            for (int[] winning_state : winning_states) {
                if (game_state[winning_state[0]] == game_state[winning_state[1]]
                        && game_state[winning_state[1]] == game_state[winning_state[2]]
                        && game_state[winning_state[0]] != 2) {

                    game_active = false;

                    //getting winner name for message!
                    String winner = "";
                    if (active_player == 1) {
                        winner = getString(R.string.x_tag);
                    } else {
                        winner = getString(R.string.o_tag);
                    }
                    String message = winner + " " + getString(R.string.has_won);

                    //setting message and preparing screen for re-match
                    messageBox.setText(message);
                    resetButton.setVisibility(View.VISIBLE);
                    messageBox.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void reset(View view) {
        resetButton.setVisibility(View.GONE);
        messageBox.setVisibility(View.GONE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i = 0; i < game_state.length; i++) {
            game_state[i] = 2;
        }

        active_player = 0;

        game_active = true;

    }
}