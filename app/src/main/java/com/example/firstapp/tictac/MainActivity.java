package com.example.firstapp.tictac;

import android.os.Bundle;
import android.view.View;
//import android.widget.GridLayout; // this crashes app
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout; // this is important




import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int active_player = 0; // initial state on player
    boolean game_active =true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][]
            winningState =
            {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                    {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                    {0, 4, 8}, {2, 4, 6}}; // all the wining state


    public void appear(View view) {
        ImageView set_sign = (ImageView) view;
        int tapped_state = Integer.parseInt(set_sign.getTag().toString()); // here we are getting tag of tapped image

        if (gameState[tapped_state] == 2 && game_active==true) {

            gameState[tapped_state] = active_player; // here we are putting active_player that is 0 /1  to gameState of tapped_state
            // it means it will only fill sign we game state is 2

            set_sign.animate().translationY(-1000f);

            if (active_player == 0) {

                set_sign.setImageResource(R.drawable.triangle);
                active_player = 1;
            } else {

                set_sign.setImageResource(R.drawable.circle);

                active_player = 0;
            }
            set_sign.animate().translationYBy(0f);


        for (int[] winningState : winningState) {
            if (gameState[winningState[0]] == gameState[winningState[1]] &&
                    gameState[winningState[1]] == gameState[winningState[2]] &&
                    gameState[winningState[0]] != 2) {
                game_active=false;
                //checking game winner
                String winner = "Circle";
                if (gameState[winningState[0]] == 0) {
                    winner = "Triangle";
                }
                TextView message = (TextView) findViewById(R.id.winningMessage);
                message.setText(winner + " has won!!");

                // making linear layout appear
                final LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);
            }
            else
            {
                // Draw game logic
                boolean gameOver=true;
                for(int counterState:gameState) // checking each state of gameState
                {
                    if(counterState==2)
                    {
                        gameOver=false; // is gameOver is false it  means someone won
                    }


                }
                if(gameOver) // is gameOver true means its DRAW
                {
                    TextView message = (TextView) findViewById(R.id.winningMessage);
                    message.setText("Game Draw !");

                    // making linear layout appear
                    final LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }


            }
        }
    }

}
    public void playReset(View view){
        game_active=true;
        active_player=0;// reStart
         LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        for(int i=0; i<gameState.length;i++){
            gameState[i]=2; // restarting game state
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        //android.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i=0 ;i<9;i++)
         {
             ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

         }



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
